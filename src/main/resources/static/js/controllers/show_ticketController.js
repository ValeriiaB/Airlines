app.controller("Show_ticketController",function ($scope,$http ,$rootScope, $window) {
    $scope.tickets=[];
    $scope.tickets=$rootScope.data;
    $scope.tickets.length=1;

    $scope.removeTicket = function (id) {
        return $http.delete('/cancel/'+id, {idTicket: id}).then(function () {
            $window.location.reload();
        }).catch(function (){
            $scope.canProceed = false;
        })
    };
});