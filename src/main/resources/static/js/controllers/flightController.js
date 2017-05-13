app.controller("FlightController",function ($scope,$http, $location ) {
    $scope.flights=[];
    var url = 'http://localhost:8181';
    $http.get(url + '/flights').success(function (data) {
        $scope.flights=data;
    })
    $scope.search = function (direction_from, direction_to) {

        $http.get(url+'/find/'+direction_from + "/"+ direction_to).success(function (data) {
            $scope.flights=data;
        })

    };
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
            $location.path('/AdminPage');
        }).catch(function (){
            $scope.canProceed = false;
        })
    };

});