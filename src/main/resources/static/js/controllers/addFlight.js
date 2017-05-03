app.controller('AddNewFlightController', ["$http", "$scope", "$location", "$rootScope",
    function ($http, $scope, $location) {

        var url = 'http://localhost:8181';

        $scope.idAirport = undefined;
        $scope.idCompany = undefined;
        $scope.directionFrom = undefined;
        $scope.directionTo = undefined;
        $scope.date = undefined;
        $scope.time = undefined;
        $scope.capacity = undefined;
        $scope.price = undefined;

        $scope.canProceed = true;

        $scope.addFlight = function () {
            return $http.post(url + '/user/addFlight' , {
                idAirport: $scope.idAirport,
                idCompany: $scope.idCompany,
                directionFrom: $scope.directionFrom,
                directionTo: $scope.directionTo,
                date: $scope.date,
                time: $scope.time,
                capacity: $scope.capacity,
                price: $scope.price
            }).then(function () {
                $location.path('/flights');
            }).catch(function (){
                $scope.canProceed = false;
            })
        };


    }]);
