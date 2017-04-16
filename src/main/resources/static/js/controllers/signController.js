
app.controller('signController', ["$http", "$scope", "$location",
    function ($http, $scope, $location) {

        var url = 'http://localhost:8181';

        $scope.email = undefined;
        $scope.password = undefined;
        $scope.name = undefined;
        $scope.surname = undefined;
        $scope.dob = undefined;

        $scope.canProceed = true;

        $scope.login = function () {
            return $http.post('/login', {email: $scope.email, password: $scope.password}).then(function () {
                $location.path('/flights');
            }).catch(function (){
                $scope.canProceed = false;
            })
        };

        $scope.signUp = function () {
            return $http.post(url + '/registration',
                {
                    email: $scope.email,
                    password: $scope.password,
                    name: $scope.name,
                    surname: $scope.surname,
                    dob: $scope.dob,
                    bonuses:0,
                    position: "guest"
                })
                .then(function (response) {
                    $location.path("/flights");
                }).catch(function (response){
                    $scope.canProceed = false;
                })
        };

    }]);