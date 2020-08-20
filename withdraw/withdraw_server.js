var PROTO_PATH = __dirname + '/protos/withdraw.proto';
var sqlite3 = require('sqlite3').verbose();
var sha1 = require('sha1');

var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');
var packageDefinition = protoLoader.loadSync(
  PROTO_PATH,
  {
    keepCase: true,
    longs: String,
    enums: String,
    defaults: true,
    oneofs: true
  });
var withdraw_proto = grpc.loadPackageDefinition(packageDefinition).withdraw;
/**
 * Implements the withdrawFunds RPC method.
 */

function withdrawFunds(call, callback) {

  call.on('data', function (response) {
    try {
      var db = new sqlite3.Database(__dirname + "/../ebanking.db");
      let { email, account, password, amount } = response
      let sql = `SELECT accounts.account_id as account_id, accounts.balance as balance, users.password as password FROM users INNER JOIN accounts ON users.user_id = accounts.user_id where users.email = '${email}' and accounts.accountType = '${account}'`;

      if (!email) {
        callback(null, {
          message: `Please specify an email address`
        });
        return;
      }

      if (!account) {
        callback(null, {
          message: `Please Specify an Account Type`
        });
        return;
      }

      if (!password) {
        callback(null, {
          message: `Please Specify a Password`
        });
        return;
      }

      if (!email) {
        callback(null, {
          message: `Please Specify an Email Address`
        });
        return;
      }

      let validString = string => {
        for (let i = 0; i < string.length; i++) {
          let c = string.charCodeAt(i);
          if (c == 34 || c == 10 || c == 8 || c == 39 || c == 13 || c == 9 || c == 92) {
            return false;
          }
        }
        return true;
      }

      let regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}$".toLowerCase();
      if (!email.match(regex) || !validString(email)) {
        callback(null, {
          message: `Please specify a valid email address`
        });
        return;
      }


      if (!validString(account)) {
        callback(null, {
          message: `Please specify a valid account type`
        });
        return;
      }

      if (!validString(password)) {
        callback(null, {
          message: `Please specify a valid password`
        });
        return;
      }

      email = email.toLowerCase();
      account = account.toLowerCase();

      if (!amount || amount <= 0) {
        callback(null, {
          message: "Cannot withdraw no or a negative amount of money into the account"
        });
        return;
      }

      db.all(sql, [], (err, rows) => {
        if (err) {
          throw err;
        }
        let user

        for (let row in rows) {
          user = rows[row];
          break;
        }

        if (!user) {
          callback(null, { message: 'Account not found' });
        } else if (sha1(password) != user['password']) {
          callback(null, { message: 'Invalid password' });
        } else if (user['balance'] - (amount * 100) >= 0) {
          db.run(`UPDATE accounts set balance = balance - ${amount * 100} where account_id = ${user['account_id']}`, [], function (err) {
            if (err) {
              return console.error(err.message);
            }
            let updatedBalance = ((user['balance'] - (amount * 100))) / 100
            callback(null, {
              message: `The updated balance of the account is € ${updatedBalance}`,
              balance: updatedBalance,
              email: email,
              account: account
            });

          });

        } else {
          callback(null, { message: `Insuffient funds to withdraw € ${amount}` });
          return
        }

      });

      // close the database connection
      db.close();
    } catch (error) {
      console.log(error)
      callback(null, {
        message: "An error occured"
      });
    }
  });
  call.on('end', function () {

  });
  call.on('error', function (e) {
    callback(null, {
      message: "An error occured"
    });
  });



}

/**
 * Starts an RPC server that receives requests for the Withdraw service at the
 * sample server port
 */
var server = new grpc.Server();
server.addService(withdraw_proto.Withdraw.service, { withdrawFunds: withdrawFunds });
server.bind('0.0.0.0:50061', grpc.ServerCredentials.createInsecure());
server.start();

