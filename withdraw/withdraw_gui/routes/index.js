var express = require('express');
var router = express.Router();
var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');

router.get('/', function (req, res, next) {
  res.redirect('/withdraw');
});

router.post('/', function (req, res, next) {
  res.redirect('/withdraw');
});

router.get('/withdraw', function (req, res, next) {
  res.render('index', { title: 'E-banking Withdrawal Service', error: null });
});

router.post('/withdraw', function (req, res, next) {
  try {
    let { email, password, account, amount } = req.body

    var PROTO_PATH = __dirname + '/../../protos/withdraw.proto';

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

    var client = new withdraw_proto.Withdraw('localhost:50061', grpc.credentials.createInsecure());



    let call = client.withdrawFunds(function (error, response) {
      try {
        res.render('index', { title: 'E-banking Withdrawal Service', error: response.message });
      } catch (error) {
        res.render('index', { title: 'E-banking Withdrawal Service', error: "Withdrawal Service cannot be accessed" });
      }
    });

    call.write({ email, password, account, amount });


    call.end();
  } catch (error) {
    res.render('index', { title: 'E-banking Withdrawal Service', error: "Withdrawal Service cannot be accessed" });
  }
});

module.exports = router;
