// app module
var blog = angular.module('blog', []);

// routes
blog.config(function ($routeProvider) {

   $routeProvider
    .when('/posts',
        {
            controller: 'PostController',
            templateUrl: 'views/posts.html'
        })
    .when('/login',
        {
            controller: 'LoginController',
            templateUrl: 'views/login.html'
        })
    .otherwise({ redirectTo: '/login' });

});
