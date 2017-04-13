app.controller("FlightController",function ($scope,$http ) {
    $scope.flights=[];

    $http.get('http://localhost:8181/flights').success(function (data) {
        $scope.flights=data;
    })

});