package io.ebanking.application.deposit;

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
 
public class DepositServer {

  private static class DepositListener implements ServiceListener {
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
    // port used by server
    int port = 50081;
    server = ServerBuilder.forPort(port)
        .addService(new DepositImpl())
        .build()
        .start();
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        
        System.err.println(" Shuting Down Server");
        try {
          DepositServer.this.stop();
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
        System.err.println("Shuting Down Server");
      }
    });
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  
      //Service Registration - Launch server
   
  public static void main(String[] args) throws IOException, InterruptedException {
    final DepositServer server = new DepositServer();
    // Create JmDNS instance
    JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

    // Register service -  Add service listener
    ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "deposit", 50051, "path=/");
    jmdns.registerService(serviceInfo);
    jmdns.addServiceListener("_http._tcp.local.", new DepositListener());

    server.start();
    server.blockUntilShutdown();
  }

  static class DepositImpl extends DepositGrpc.DepositImplBase {

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
    public void depositFunds(DepositRequest req, StreamObserver<DepositReply> responseObserver) {
      
      Connection conn = null;
      String email = req.getEmail();
      String accountType = req.getAccount();
      String password = req.getPassword();
      Integer amount = (int) (req.getAmount() * 100);

      if(email == null || email.equals("")) {
        DepositReply reply = DepositReply.newBuilder().setMessage("Please specify an email address").setEmail(email).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(accountType == null || accountType.equals("")) {
        DepositReply reply = DepositReply.newBuilder().setMessage("Please specify an account type").setAccountType(accountType).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(password == null || password.equals("")) {
        DepositReply reply = DepositReply.newBuilder().setMessage("Please specify a password").setPassword(password).build();
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
        DepositReply reply = DepositReply.newBuilder().setMessage("Please specify a valid email address").setEmail(email).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      
      if(!validString(accountType)) {
        DepositReply reply = DepositReply.newBuilder().setMessage("Please specify a valid account type").setAccountType(accountType).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }
      
      
      if(!validString(password)) {
        DepositReply reply = DepositReply.newBuilder().setMessage("Please specify a valid password").setPassword(password).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(amount <= 0) {
        DepositReply reply = DepositReply.newBuilder().setMessage("Cannot deposit no or a negative amount of money into the account").build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + Paths.get("..").toAbsolutePath().normalize().toString() + "/ebanking.db";
            conn = DriverManager.getConnection(url);

            PreparedStatement s = conn.prepareStatement("SELECT accounts.account_id as account_id, accounts.balance as balance, users.password as password FROM users INNER JOIN accounts ON users.user_id = accounts.user_id where users.email = ? and accounts.accountType = ?");
            s.setString(1, email);
            s.setString(2, accountType);
            ResultSet rs = s.executeQuery();
            
            int rowCount = 0;
            int balance = 0;
            int accountId = 0;
            String databasePassword = "";

            while(rs.next()) {
              balance = rs.getInt("balance");
              accountId = rs.getInt("account_id");
              databasePassword = rs.getString("password");
              rowCount++;
              break;
            }

            if(rowCount > 0 && password.equals(databasePassword)) {
              s = conn.prepareStatement("UPDATE accounts set balance = balance + ? where account_id = ?");
              s.setInt(1, amount);
              s.setInt(2, accountId);
              s.executeUpdate();
              double displayedBalance = (((double) balance+amount)/100);

              DepositReply reply = DepositReply.newBuilder()
                .setMessage("The updated balance of the account is €" + displayedBalance)
                .setEmail(email)
                .setAccountType(accountType)
                .setBalance(displayedBalance)
                .build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();

            } else if(rowCount <= 0) {
              DepositReply reply = DepositReply.newBuilder().setMessage("Account could not be found").build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();
            } else {
              DepositReply reply = DepositReply.newBuilder().setMessage("Invalid Login Credentials").build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();
            }

            conn.close();

        } catch (Exception e) {
          System.out.println(e);
          DepositReply reply = DepositReply.newBuilder().setMessage("An Error Occured").setEmail(email).setAccountType(accountType).setPassword(password).build();
          responseObserver.onNext(reply);
          responseObserver.onCompleted();
        }

      
    }
  }
}
