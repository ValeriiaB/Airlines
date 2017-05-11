app.controller('AirportController', ["$http", "$scope", "$location",
    function ($http, $scope, $location) {

        var url = 'http://localhost:8181';

        $scope.name = undefined;
        $scope.country = undefined;
        $scope.city = undefined;
        $scope.phone = undefined;
        $scope.adress = undefined;
        $scope.isActive = undefined;

        $scope.canProceed = true;

        $scope.addAirport = function () {
            return $http.post(url + '/user/addAirport' , {
                name: $scope.name,
                country: $scope.country,
                city: $scope.city,
                phone: $scope.phone,
                adress: $scope.adress,
                isActive: $scope.isActive
            }).then(function () {
                $location.path('/AdminPage');
            }).catch(function (){
                $scope.canProceed = false;
            })
        };
        $scope.idAirport = undefined;

        $scope.UpdateAirport = function () {
            return $http.patch(url + '/user/update/Airport' , {
                idAirport: $scope.idAirport,
                isActive: $scope.isActive
            }).then(function () {
                $location.path('/AdminPage');
            }).catch(function (){
                $scope.canProceed = false;
            })
        };


    }]);
