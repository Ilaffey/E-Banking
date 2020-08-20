package io.ebanking.application.balance;

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

/**
 * Server that manages startup/shutdown of a {@code Inquirer} server.
    Service Discovery
 */
public class BalanceServer {

  private static class BalanceListener implements ServiceListener {
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
    /* The port on which the server should run */
    int port = 50071;
    server = ServerBuilder.forPort(port)
        .addService(new InquirerImpl())
        .build()
        .start();
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        try {
          BalanceServer.this.stop();
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * Main launches the server from the command line.
      Service Registration
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    final BalanceServer server = new BalanceServer();
    // Create JmDNS instance
    JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

    // Register service -  Add service listener
    ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "balance", 50071, "path=/");
    jmdns.registerService(serviceInfo);
    jmdns.addServiceListener("_http._tcp.local.", new BalanceListener());

    server.start();
    server.blockUntilShutdown();
  }

  static class InquirerImpl extends InquirerGrpc.InquirerImplBase {

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
    public void getBalance(BalanceRequest req, StreamObserver<BalanceReply> responseObserver) {
      
      Connection conn = null;
      String email = req.getEmail();
      String accountType = req.getAccount();
      String password = req.getPassword();

      if(email == null || email.equals("")) {
        BalanceReply reply = BalanceReply.newBuilder().setMessage("Please Specify an Email Address").setEmail(email).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(accountType == null || accountType.equals("")) {
        BalanceReply reply = BalanceReply.newBuilder().setMessage("Please specify an Account Type").setAccountType(accountType).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      if(password == null || password.equals("")) {
        BalanceReply reply = BalanceReply.newBuilder().setMessage("Please Specify a Password").setPassword(password).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      email = email.toLowerCase();
      accountType = accountType.toLowerCase();
      password = DigestUtils.sha1Hex(password);

      String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}$".toLowerCase();
      if(!email.matches(regex) || !validString(email)) {
        BalanceReply reply = BalanceReply.newBuilder().setMessage("Please Specify a Valid Email Address").setEmail(email).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      
      if(!validString(accountType)) {
        BalanceReply reply = BalanceReply.newBuilder().setMessage("Please Specify a Valid Account Type").setAccountType(accountType).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }
      
      
      if(!validString(password)) {
        BalanceReply reply = BalanceReply.newBuilder().setMessage("Please Specify a Valid Password").setPassword(password).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        return;
      }

      

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println();
            String url = "jdbc:sqlite:" + Paths.get("..").toAbsolutePath().normalize().toString() + "/ebanking.db";
            conn = DriverManager.getConnection(url);

            PreparedStatement query = conn.prepareStatement("SELECT accounts.balance as balance, users.password as password FROM users INNER JOIN accounts ON users.user_id = accounts.user_id where users.email = ? and accounts.accountType = ?");
            query.setString(1, email);
            query.setString(2, accountType);
            ResultSet rs = query.executeQuery();
            
            int rowCount = 0;
            int balance = 0;
            String databasePassword = "";

            while(rs.next()) {
              balance = rs.getInt("balance");
              databasePassword = rs.getString("password");
              rowCount++;
              break;
            }

            if(rowCount > 0 && password.equals(databasePassword)) {
              double displayedBalance = (((double) balance)/100);

              BalanceReply reply = BalanceReply.newBuilder().setMessage("The Balance of the Account is €" + displayedBalance).setEmail(email).setAccountType(accountType).setBalance(displayedBalance).build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();

            } else if(rowCount <= 0) {
              BalanceReply reply = BalanceReply.newBuilder().setMessage("Account could not be found").build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();
            } else {
              BalanceReply reply = BalanceReply.newBuilder().setMessage("Invalid Login Credentials").build();
              responseObserver.onNext(reply);
              responseObserver.onCompleted();
            }

            conn.close();

        } catch (Exception e) {
          System.out.println(e);
          BalanceReply reply = BalanceReply.newBuilder().setMessage("An Error Occured").setEmail(email).setAccountType(accountType).setPassword(password).build();
          responseObserver.onNext(reply);
          responseObserver.onCompleted();
        }

      
    }
  }
}
