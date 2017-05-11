app.controller("ShowSearchResults",function ($scope, $rootScope) {
    console.log($rootScope.res);
    $scope.flights=[];
    $scope.flights=$rootScope.res;
});