package io.ebanking.application.account;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import org.apache.commons.codec.digest.DigestUtils;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceInfo;
import java.net.InetAddress;
import java.nio.file.Paths;

//https://github.com/grpc/grpc-java/blob/v1.31.1/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldServer.java
//Service Discovery 
public class AccountServer {

  private static class AccountListener implements ServiceListener {
    @Override
    public void serviceAdded(ServiceEvent event) {
    }

    @Override
    public void serviceRemoved(ServiceEvent event) {
    }

    @Override
    public void serviceResolved(ServiceEvent event) {
    }
}

  private Server server;
  private void start() throws IOException {
      /* port used by server*/
    int port = 50051;
    server = ServerBuilder.forPort(port)
        .addService(new CreaterImpl())
        .build()
        .start();
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        
        System.err.println("Shuting Down Server");
        try {
          AccountServer.this.stop();
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
        System.err.println("Shut Down Server");
      }
    });
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }
// Server Shutdown
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }
  /**
   
      Service Registration - Launch server
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    final AccountServer server = new AccountServer();
    // Create JmDNS instance
    JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

    // Service Registration -   Add a service listener
    ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "account", 50051, "path=/");
    jmdns.registerService(serviceInfo);
    jmdns.addServiceListener("_http._tcp.local.", new AccountListener());


    server.start();
    server.blockUntilShutdown();
  }

  static class CreaterImpl extends CreaterGrpc.CreaterImplBase {

    public boolean validString(String string) {
      for (int i = 0; i < string.length(); i++) {
        int c = (int) string.charAt(i);
        if(c == 34 || c == 10 || c == 8 || c == 39 || c == 13 || c == 9 || c == 92) {
          return false;
        }
      }
      return true;
    }

    @Override
    public void createAccount(AccountRequest req, StreamObserver<AccountReply> responseObserver) {
            
      Connection conn = null;
      String email = req.getEmail();
      String accountType = req.getAccount();
      String password = req.getPassword();


      if(email == null || email.equals("")) {
        AccountReply reply = AccountReply.newBuilder().setMessage("Please Specify an Email Address").setEmail(email).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(accountType == null || accountType.equals("")) {
        AccountReply reply = AccountReply.newBuilder().setMessage("Please Specify an Account Type").setAccountType(accountType).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(password == null || password.equals("")) {
        AccountReply reply = AccountReply.newBuilder().setMessage("Please Specify a Password").setPassword(password).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      email = email.toLowerCase();
      accountType = accountType.toLowerCase();
      password = DigestUtils.sha1Hex(password);
      // Needed for credentials
      String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}$".toLowerCase();
      if(!email.matches(regex) || !validString(email)) {
        AccountReply reply = AccountReply.newBuilder().setMessage("Please Specify a Valid Email Address").setEmail(email).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(!validString(accountType)) {
        AccountReply reply = AccountReply.newBuilder().setMessage("Please Specify a Valid Account Type").setAccountType(accountType).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }
      
      if(!validString(password)) {
        AccountReply reply = AccountReply.newBuilder().setMessage("Please Specify a Valid Password").setPassword(password).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + Paths.get("..").toAbsolutePath().normalize().toString() + "/ebanking.db";
            conn = DriverManager.getConnection(url);

            PreparedStatement query = conn.prepareStatement("SELECT accounts.accountType as accountType, users.user_id as user_id, users.password as password FROM users LEFT JOIN accounts ON users.user_id = accounts.user_id where email = ?");
            query.setString(1, email);
            ResultSet rs = query.executeQuery();
            boolean validAccount = true;
            int rowCount = 0;
            int user_id = 0;
            String databasePassword = "";
            if(rs != null) {
              while(rs.next()) {
                  
                  if(rs.getString("accountType") != null && rs.getString("accountType").equals(accountType)) {
                    validAccount = false;
                  }
                  user_id = rs.getInt("user_id");
                  databasePassword = rs.getString("password");
                  rowCount++;
              }
            }

            String responseMessage = "";

            if(rowCount <= 0) {

              query = conn.prepareStatement("INSERT INTO users (email, password) VALUES(?,?)");
              query.setString(1, email);
              query.setString(2, password);
              query.executeUpdate();

              query = conn.prepareStatement("SELECT users.user_id as user_id FROM users where email = ?");
              query.setString(1, email);
              rs = query.executeQuery();

              while(rs.next()) {
                  user_id = rs.getInt("user_id");
              }
              databasePassword = password;
              responseMessage = "A User with an Email Address of" + email + " has been created";
            }

            if (validAccount && user_id != 0) {

              if(!databasePassword.equals(password)) {
                AccountReply reply = AccountReply.newBuilder().setMessage("Invalid Credentials").setEmail(email).setAccountType(accountType).setPassword(password).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
                conn.close();
                return;
              }
              
              query = conn.prepareStatement("INSERT INTO accounts (accountType, user_id) VALUES(?,?)");
              query.setString(1, accountType);
              query.setInt(2, user_id);
              query.executeUpdate();
              if(!responseMessage.equals("")) {
                responseMessage = "A User with an Email Address of " + email + " with a " + accountType + " Account has been created";
              } else {
                responseMessage = "A " + accountType + " Account has been created for " + email;
              }
            }
            conn.close();

            if(!responseMessage.equals("")) {
              AccountReply reply = AccountReply.newBuilder().setMessage(responseMessage).setEmail(email).setAccountType(accountType).build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();
            } else {
              AccountReply reply = AccountReply.newBuilder().setMessage("A " + accountType + " Account already exists using the " + email + " Email Address").setEmail(email).setAccountType(accountType).build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();
            }

          } catch (Exception e) {
            e.printStackTrace();
            AccountReply reply = AccountReply.newBuilder().setMessage("An Error has Occured").setEmail(email).setAccountType(accountType).setPassword(password).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
  }
}
