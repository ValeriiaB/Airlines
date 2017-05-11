app.controller("ShowSearchResults",function ($scope,$http ,$rootScope) {
    $scope.flights=[];
    $scope.flights=$rootScope.data;

});