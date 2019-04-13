/**
 * Created by Administrator on 2016/3/14.
 */
var fmHomeModule = angular.module('fm.homeModule', ['ngRoute', 'ui.router']);


fmHomeModule.config([
    '$stateProvider',
    '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider.state("Content.Home", {
            url: "/Home",
            views: {
                'fm_content_window': {
                    templateUrl: "fmModule/home/views/index.html"
                }
            }
        }).state("Content.Home.view", {
            url: "/View",
            views: {
                'fm_home_window': {
                    templateUrl: "fmModule/report/views/view.html"
                }
            }
        });
    }
]);

fmHomeModule.controller('homeCtrl',['$scope','$state', function ($scope,$state) {

}]);