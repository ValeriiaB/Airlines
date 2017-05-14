app.controller("Show_ticketController",function ($scope,$http ,$rootScope, $window) {
    $scope.tickets=[];
    // var url = 'http://localhost:8181';
    $scope.tickets=$rootScope.current;
    // $http.get(url + '/show_ticket/'+idT, {idTicket: idT}).then(function (response) {
    //     $scope.tickets=response;
    // });
    $scope.removeTicket = function (id) {
        return $http.delete('/cancel/'+id, {idTicket: id}).then(function () {
            $window.location.reload();
        }).catch(function (){
            $scope.canProceed = false;
        })
    };
});