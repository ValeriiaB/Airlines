app.controller('BookingController', ["$http", "$scope", "$location","$rootScope",
    function ($http, $scope, $location, $rootScope) {

        var url = 'http://localhost:8181';

        $scope.id = undefined;

        $scope.canProceed = true;

        $scope.book = function () {
            return $http.post(url + '/user/bookTicket/'+ $scope.id).then(function () {
                $location.path('/user/mytickets');
            }).catch(function (){
                $scope.canProceed = false;
            })
        };
        $scope.idFlight = undefined;
        $scope.name = undefined;
        $scope.surname = undefined;

        $scope.canProceed = true;

        $scope.anonym_book = function () {
            return $http.patch(url + '/bookTicket' , {
                name: $scope.name,
                surname: $scope.surname,
                idFlight: $scope.idFlight
            }).then(function (response) {
                $rootScope.data=response;
                $location.path("/showTicket");
            }).catch(function (){
                $scope.canProceed = false;
            })
        };

    }]);