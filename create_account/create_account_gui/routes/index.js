var express = require('express');
var router = express.Router();
var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');

router.get('/', function (req, res, next) {
  res.render('index', { title: 'E-banking New Account Service', error: null });
});

router.post('/', function (req, res, next) {
  try {
    let { email, password, account } = req.body

    var PROTO_PATH = __dirname + '/../../src/main/proto/account.proto';

    var packageDefinition = protoLoader.loadSync(
      PROTO_PATH,
      {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
      });

    var account_proto = grpc.loadPackageDefinition(packageDefinition).account;

    var client = new account_proto.Creater('localhost:50051', grpc.credentials.createInsecure());

    client.createAccount({ email: email, password: password, account: account }, function (err, response) {
      try {
        res.render('index', { title: 'E-banking New Account Service', error: response.message });
      } catch (error) {
        res.render('index', { title: 'E-banking New Account Service', error: "New Account Service is not available at the moment please try again later" });
  
      }

    });
  } catch (error) {
    res.render('index', { title: 'E-banking New Account Service', error: "New Account Service is not available at the moment please try again later" });
  }

});


router.get('/create', function (req, res, next) {
  res.redirect('/');
});

router.post('/create', function (req, res, next) {
  res.redirect('/');
});


module.exports = router;
