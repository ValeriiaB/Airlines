app.controller("BonusController",function ($scope,$http ) {
    $scope.bonuses=0;

    $http.get('http://localhost:8181/bonuses').success(function (data) {
        $scope.bonuses=data;
    })

});