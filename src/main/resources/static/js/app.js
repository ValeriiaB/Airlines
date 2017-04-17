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
        .when('/bonuses', {
            templateUrl: 'view/bonus.html',
            controller: 'BonusController'
        })
        .when('/mytickets', {
            templateUrl: 'view/my_tickets.html',
            controller: 'TicketController'
            // controllerAs:'ctrl',
            // resolve: {
            //     users: function ($q, TicketService) {
            //         console.log('Load all ticket');
            //         var deferred = $q.defer();
            //         TicketService.loadAllUsers().then(deferred.resolve, deferred.resolve);
            //         return deferred.promise;
            //     }
            // }

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

    $scope.myFunction=function(){
        document.getElementById("myDropdown").classList.toggle("show");
    }

// Close the dropdown menu if the user clicks outside of it
    window.onclick = function(event) {
        if (!event.target.matches('.dropbtn')) {

            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
    $scope.logOut = function () {
        $http.post(url + '/logout').then(function (response) {
            $scope.showUser = false;
            $scope.user = undefined;
        })
    }


}]);