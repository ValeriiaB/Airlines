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
        .when("/addFlight", {
             templateUrl: 'view/AddNewFlight.html',
             controller: 'AddNewFlightController'
        })
        .when("/addAirport", {
            templateUrl: 'view/AddNewAirport.html',
            controller: 'AirportController'
        })
        .when("/updateAirport", {
            templateUrl: 'view/UpdateAirport.html',
            controller: 'AirportController'
        })
        .when('/flights', {
            templateUrl: 'view/flightstable.html',
            controller: 'FlightController'
        })
        .when('/AdminPage', {
            templateUrl: 'view/adminPage.html',
            // controller: 'FlightController'
        })
        .when('/user/bonuses', {
            templateUrl: 'view/bonus.html',
            controller: 'BonusController'
        })
        .when('/user/booking', {
            templateUrl: 'view/user_book.html',
            controller: 'BookingController'
        })
        .when('/bookTicket', {
            templateUrl: 'view/book_without_registration.html',
            controller: 'Anonym_bookingController'
        })
        .when('/showTicket', {
            templateUrl: 'view/my_tickets.html',
            controller: 'Show_ticketController'
        })
        .when('/user/mytickets', {
            templateUrl: 'view/my_tickets.html',
            controller: 'TicketController'
        })
        .otherwise(
            { redirectTo: '/'});

 })
    .controller("MainController", ['$scope','$http', function($scope, $http) {
    var url = 'http://localhost:8181';

    $scope.user = undefined;
    $scope.showUser = false;
    $scope.isAdmin = false;

    $http.get(url + '/currentUser').then(function (response) {
        console.log(response);
        $scope.showUser = true;
        $scope.user = response.data;
        if($scope.user.position.localeCompare('guest')!=0) {
            $scope.isAdmin = true;
        }
    });

    $scope.myFunction=function(){
        document.getElementById("myDropdown").classList.toggle("show");
    };

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
    };
    $scope.logOut = function () {
        $http.post(url + '/logout').then(function (response) {
            $scope.showUser = false;
            $scope.user = undefined;
        })
    }


}]);
