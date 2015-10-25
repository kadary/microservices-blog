// Login controller
blog.controller('LoginController', function ($scope, $rootScope, $http, $location) {
    $scope.header = "Login";
    $scope.username = "";
    $scope.password = "";
    $rootScope.credentials = {};

    $scope.login = function() {
      var data = {
          username: $scope.username,
          password: $scope.password
        };
        $http.post("http://localhost:3000/login", data)
        .then(function (response) {
          $rootScope.credentials = response.data;
          $location.path("/posts");
        }, function(response) {
          alert(JSON.stringify(response));
        });
    }

});

// post controller
blog.controller('PostController', function ($scope, $rootScope, $http) {
  $scope.header = "Latest Posts";
  $scope.posts = [];
  $scope.showComments = false;
  $scope.p = {};
  $scope.postTitle = "";
  $scope.postContent = "";
  $scope.postToCommentId = "";
  $scope.commentContent = "";
  $http.get("http://localhost:3000/posts", {
    headers: {
      authorization: $rootScope.credentials.token_type + " " + $rootScope.credentials.access_token
    }})
    .then(function (response) {
      $scope.posts = response.data;
    }, function(response) {
      alert(JSON.stringify(response));
    });

    $scope.setComments = function(post) {
      $scope.p = post;
      $scope.showComments = true;
      $scope.postToCommentId = $scope.p.postId;
    }

    $scope.return = function() {
      $scope.showComments = false;
    }

    $scope.addPost = function() {
      var data = {
          userId: 0,
          title: $scope.postTitle,
          content: $scope.postContent
        };
        $http.post("http://localhost:3000/post", data, {
          headers: {
            authorization: $rootScope.credentials.token_type + " " + $rootScope.credentials.access_token
          }})
          .then(function (response) {
            $scope.posts.push(response.data);
            $scope.postTitle = "";
            $scope.postContent = "";
          }, function(response) {
            alert(JSON.stringify(response));
          });
    }

    $scope.addComment = function() {
      var data = {
          userId: 8,
          postId: $scope.postToCommentId,
          content: $scope.commentContent
        };
        $http.post("http://localhost:3000/comment", data, {
          headers: {
            authorization: $rootScope.credentials.token_type + " " + $rootScope.credentials.access_token
          }})
          .then(function (response) {
            $scope.p.comments.push(response.data);
            $scope.postToCommentId = "";
            $scope.commentContent = "";
          }, function(response) {
            alert(JSON.stringify(response));
          });
    }
  });
