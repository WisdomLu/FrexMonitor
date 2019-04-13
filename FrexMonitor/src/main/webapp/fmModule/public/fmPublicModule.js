/**
 * Created by dell on 2016/3/4.
 */
var fmPublicModule = angular.module('fm.publicModule', []);

fmPublicModule.controller('pubHeaderCtrl', ['$rootScope','$scope', '$state',function ($rootScope,$scope, $state) {
    $scope.logout = function () {
        $state.go("UserWelcome.Logout",{},{'reload': false});
    };
    //$scope.goMessage = function () {
    //    $state.go("Message",{},{'reload': true});
    //};
    $scope.createAd = function () {
        $scope.removeSessionStorage('ad');
        $rootScope.$broadcast('newAd');
        $state.go("Content.Spread.createAd.baseInfo",{id:''},{'reload': true});
    }

}]).controller('sideCtrl', ['$scope',function ($scope) {
    $scope.active = "home";
    $scope.$watch('toStateName', function (newValue) {
        if(newValue) {
            var stateName = newValue.split('.')[1];
            switch(stateName) {
                case 'Home' : $scope.active='home';break;
                case 'Spread' : $scope.active='spread';break;
                case 'Tool' : $scope.active='tool';break;
                case 'Report' : $scope.active='report';break;
                case 'Message' : $scope.active='message';break;
                case 'Account' : $scope.active='account';break;
            }
        }
    })
}]);
