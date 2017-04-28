app.controller('Anonym_bookingController', ["$http", "$scope", "$location", "$rootScope",
    function ($http, $scope, $location, $rootScope) {

        var url = 'http://localhost:8181';

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