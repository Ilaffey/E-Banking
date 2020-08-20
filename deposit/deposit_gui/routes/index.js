var express = require('express');
var router = express.Router();
var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');


router.get('/', function (req, res, next) {
  res.redirect('/deposit');
});

router.post('/', function (req, res, next) {
  res.redirect('/deposit');
});

router.get('/deposit', function (req, res, next) {
  res.render('index', { title: 'E-banking Deposit Service', error: null });
});

router.post('/deposit', function (req, res, next) {
  try {
    let { email, password, account, amount } = req.body

    var PROTO_PATH = __dirname + '/../../src/main/proto/deposit.proto';

    var packageDefinition = protoLoader.loadSync(
      PROTO_PATH,
      {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
      });
    var deposit_proto = grpc.loadPackageDefinition(packageDefinition).deposit;


    var client = new deposit_proto.Deposit('localhost:50081',
      grpc.credentials.createInsecure());
    client.depositFunds({ email: email, password: password, account: account, amount: amount }, function (err, response) {
      try {
        res.render('index', { title: 'E-banking Deposit Service', error: response.message });
      } catch (error) {
        res.render('index', { title: 'E-banking Deposit Service', error: "Deposit Service is not available at the moment please try again later" });
      }
    });
  } catch (error) {
    res.render('index', { title: 'E-banking Deposit Service', error: "Deposit Service is not available at the moment please try again later" });
  }




});

module.exports = router;
