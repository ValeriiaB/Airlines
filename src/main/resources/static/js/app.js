var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: 'view/index.html',
            controller: 'MainController'
        })
        .when('/login',{
            templateUrl: 'view/login.html',
            controller: 'signController'
        })
        .when("/registration", {
            templateUrl: 'view/sign_up.html',
            controller: 'signController'
        })
        .when('/flights', {
            templateUrl: 'view/flightstable.html',
            controller: 'FlightController'
        })
        .otherwise(
            { redirectTo: '/'});

}).controller("MainController", ['$scope','$http', function($scope, $http) {
    var url = 'http://localhost:8181';

    $scope.user = undefined;
    $scope.showUser = false;

    $http.get(url + '/currentUser').then(function (response) {
        console.log(response);
        $scope.showUser = true;
        $scope.user = response.data;
    });

    $scope.logOut = function () {
        $http.post(url + '/logout').then(function (response) {
            $scope.showUser = false;
            $scope.user = undefined;
        })
    }


}]);