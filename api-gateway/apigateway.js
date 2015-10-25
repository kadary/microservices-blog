//include of libs
var express = require('express'),
    request = require('request'),
    bodyParser = require('body-parser'),
    merge = require('merge'),
    async = require('async'),
    httpProxy = require('http-proxy');

// init the gateway
var gateway = express();
gateway.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, authorization");
  next();
});
gateway.use(bodyParser.json());
// init the front-end server
var server = gateway.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Api Gateway listening at http://%s:%s', host, port);
});

gateway.get('/', function (req, res) {
  res.send('Welcome to microservices blog API Gateway');
});

//login API
gateway.post('/login', function (req, res) {
  var options = {
    method: 'POST',
    url: 'http://frontendserver:nodejs@localhost:7000/uaa/oauth/token',
    qs: {
      password: req.body.password,
      username: req.body.username,
      grant_type: 'password',
      scope: 'openid',
      client_secret: 'nodejs',
      client_id: 'frontendserver'
    },
    headers: {
      accept: 'application/json'
    }
  };
  request(options, function (error, response, body) {
    if (error) throw new Error(error);
    res.json(JSON.parse(body));
  });
});

//posts fetcher API
gateway.get('/posts', function (req, res) {
  var result = [];

  var postsFetcherRequest = {
    method: 'GET',
    url: 'http://localhost:7001/api/posts',
    headers:
    {
      authorization: req.headers.authorization
    }
  };
  var commentsFetcherRequest = {
    method: 'GET',
    url: 'http://localhost:7002/api/comments',
    headers: {
      authorization: req.headers.authorization
    }
  };

  request(commentsFetcherRequest, function (error, response, body) {
    if (error) throw new Error(error);
    var comments = JSON.parse(body);

    request(postsFetcherRequest, function (error, response, body) {
      if (error) throw new Error(error);
      try {
      var posts = JSON.parse(body);
      async.eachSeries(posts, function (post, callback) {
        var postComments = [];
          comments.forEach(function (comment, index, array) {
            if (comment.postId == post.postId) {
              postComments.push(comment);
            }
          });
          result.push(merge.recursive(true, post, { comments: JSON.parse(JSON.stringify(postComments))}));
          callback();
      }, function (err) {
        if (err) { throw err; }
        res.json(result);
      });
    } catch (e) {
        console.log('Caught exception: ' + e);
        res.send(e);
    }
    });
  });
});

//Add Post
gateway.post('/post', function (req, res) {
  var options = {
    method: 'POST',
    url: 'http://localhost:7001/api/posts',
    headers: {
      accept: 'application/json',
      authorization: req.headers.authorization
    },
    body: req.body,
    json: true
  };
  request(options, function (error, response, body) {
    if (error) throw new Error(error);
    try {
      res.send(body);
    } catch (e) {
      console.log('Caught exception: ' + e);
      res.send(e);
    }
  });
});

//Add Post
gateway.post('/comment', function (req, res) {
  var options = {
    method: 'POST',
    url: 'http://localhost:7002/api/comments',
    headers: {
      accept: 'application/json',
      authorization: req.headers.authorization
    },
    body: req.body,
    json: true
  };
  request(options, function (error, response, body) {
    if (error) throw new Error(error);
    try {
      res.send(body);
    } catch (e) {
      console.log('Caught exception: ' + e);
      res.send(e);
    }
  });
});
