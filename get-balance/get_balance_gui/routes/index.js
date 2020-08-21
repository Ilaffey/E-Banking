var express = require('express');
var router = express.Router();
var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');


router.get('/', function (req, res, next) {
  res.redirect('/balance');
});

router.post('/', function (req, res, next) {
  res.redirect('/balance');
});

router.get('/balance', function (req, res, next) {
  res.render('index', { title: 'E-banking Balance Service', error: null });
});

router.post('/balance', function (req, res, next) {
  let { email, password, account, amount } = req.body
  try {
    var PROTO_PATH = __dirname + '/../../src/main/proto/balance.proto';

    var packageDefinition = protoLoader.loadSync(
      PROTO_PATH,
      {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
      });

    var balance_proto = grpc.loadPackageDefinition(packageDefinition).balance;

    var client = new balance_proto.Inquirer('localhost:50071', grpc.credentials.createInsecure());

    var call = client.getBalance({ email: email, password: password, account: account });
    call.on('data', function (response) {
      try {
        res.render('index', { title: 'E-banking Balance Service', error: response.message });
      } catch (error) {
        res.render('index', { title: 'E-banking Balance Service', error: "Balance Service cannot be accessed" });
      }
    });
    call.on('end', function () {

    });
    call.on('error', function (e) {
      res.render('index', { title: 'E-banking Balance  Service', error: "Balance Service cannot be accessed" });
    });
    call.on('status', function (status) {
      return
    });

  } catch (error) {
    res.render('index', { title: 'E-banking Balance Service', error: "Balance Service cannot be accessed" });
  }

});

module.exports = router;
