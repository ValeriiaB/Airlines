app.controller('BookingController', ["$http", "$scope", "$location",
    function ($http, $scope, $location) {

        var url = 'http://localhost:8181';

        $scope.id = undefined;

        $scope.canProceed = true;

        $scope.book = function () {
            return $http.post(url + '/user/bookTicket/'+ $scope.id).then(function () {
                $location.path('/mytickets');
            }).catch(function (){
                $scope.canProceed = false;
            })
        };

    }]);