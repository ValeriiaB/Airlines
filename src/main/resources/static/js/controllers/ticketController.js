app.controller("TicketController",function ($scope,$http ) {
    $scope.tickets=[];

    $http.get('http://localhost:8181/mytickets').success(function (data) {
        $scope.tickets=data;
    });
    $scope.removeTicket = function (id) {
        return $http.delete('/cancel/'+id, {idTicket: id}).then(function () {
        }).catch(function (){
            $scope.canProceed = false;
        })
    };

});