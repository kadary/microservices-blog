var express = require('express'),
    http = require('http'),
    httpProxy = require('http-proxy');

// init the gateway
var gateway = express();

gateway.get('/', function (req, res) {
  res.send('Hello World!');
});

// init the front-end server
var server = gateway.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Api Gateway listening at http://%s:%s', host, port);
});
