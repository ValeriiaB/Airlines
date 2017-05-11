
app.controller('SearchController', ["$http", "$scope", "$location",
    function ($http, $scope, $location) {

        var url = 'http://localhost:8181';

        $scope.st_date = undefined;
        $scope.end_date = undefined;
        $scope.direction_from = undefined;
        $scope.direction_to = undefined;

        $scope.canProceed = true;


        $scope.findFlight = function () {

            return $http.get(url + '/find/'+$scope.direction_from+"/"+$scope.direction_to,
                {
                    direction_from : $scope.direction_from,
                    direction_to : $scope.direction_to
                })
                .then(function (response) {
                    $rootScope.data=response;
                    $location.path("/search_results");
                }).catch(function (response){
                    $scope.canProceed = false;
                })
        };

    }]);