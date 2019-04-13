/**
 * Created by dell on 2015/11/25.
 */
var fmApp = angular.module('FrexMonitor', [ 'ngRoute',
    'ui.router',
    'fm.strategyModule',
    'Sirius.HttpService',
    'Sirius.View',
    'Sirius.Form',
    'Sirius.Common',
    'fm.daoService',
    'Sirius.Constants',
    'Sirius.Storage',
    'Sirius.Filter',
    'datatables',
    'datatables.bootstrap',
    'rzModule'
]);


//路由设置
fmApp.config([
    '$stateProvider',
    '$urlRouterProvider',
    '$httpProvider',
    function ($stateProvider, $urlRouterProvider,$httpProvider) {
        $urlRouterProvider.otherwise('/Content/Home/View');
        $httpProvider.interceptors.push('myInterceptor');
        $stateProvider.state("Content", {
            url: "/Content",
            views: {
                '': {
                    templateUrl: "fmModule/public/views/index.html"
                }
            }
        });
    }

]);

//run,类似main函数, 初始化和状态转换时控制权限
fmApp.run(['$state', '$rootScope', 'fmDaoService','sessionStorage','localStorage',
    function ($state, $rootScope, fmDaoService,sessionStorage,localStorage) {

    $rootScope.specialstatue = {
        'UserWelcome': 1,
        'UserWelcome.Login': 1,
        'UserWelcome.Register': 1,
        'UserWelcome.RegisterSuccess': 1,
        'UserWelcome.ForgetPwd': 1,
        'UserWelcome.ForgetPwdSuccess': 1
    }; // 不需要登录的状态

    $rootScope.$on('notLogin', function () {
        sessionStorage.clear();
        $state.go("UserWelcome.Login",{},{reload:true});
    });
    $rootScope.$on('noPermission', function (e,msg) {
        var goAccount = function () {
            if(!$state.current.name.startsWith('Content.Account.userInfo')) {
                $state.go('Content.Account.userInfo');
            }
        };
        $rootScope.showMessageTip(msg,goAccount,'',3000);
    });
    $rootScope.getSessionStorage = function (item) {
        return sessionStorage.get(item);
    };
    $rootScope.updateSessionStorage = function (item,obj) {
        sessionStorage.remove(item);
        sessionStorage.set(item,obj);
    };
    $rootScope.removeSessionStorage = function (item) {
        sessionStorage.remove(item);
    };
    $rootScope.getLocalStorage = function (item) {
        return localStorage.get(item);
    };
    $rootScope.updateLocalStorage = function (item,obj) {
        localStorage.remove(item);
        localStorage.set(item,obj);
    };
    $rootScope.removeLocalStorage = function (item) {
        localStorage.remove(item);
    };


    $rootScope.$on('$stateChangeStart', function (event, toState, toParams,
                                                  fromState) {
        $rootScope.toStateName = toState.name;
        $rootScope.fromStateName = fromState.name;
        if(toState.name.split('.')[0] == 'UserWelcome') {
            $('body').css({
                'background-color':'#333'
            })
        } else {
            $('body').css({
                'background-color':'#fafafa'
            })
        }
        console.log($rootScope.toStateName+"->"+$rootScope.fromStateName);
        // web初始化时（刷新,或者被http拦截的跳转），交给Http验证
        if (fromState.name == "" && fromState.abstract == true) {
            return;
        }

        if ($rootScope.specialstatue[toState.name] === 1)
            return;// 如果是进入登录界面则允许
        if(!$rootScope.Logined) {
            event.preventDefault();
            sessionStorage.clear();
            $state.go("UserWelcome.Login",{reload:true});
        }
    });

    $rootScope.userId = "";
    $rootScope.userName = "";
    $rootScope.realname = "";

    // 判断是否登陆
    fmDaoService.getUserInfo({
        scope: $rootScope,
        successFun: function(msg){
            $rootScope.Logined = true;
            $rootScope.userName = msg.userName;
            if (msg.isManager == 1) {
                $rootScope.isManager = true;
            }
            if ($rootScope.toStateName == "") {
                $state.go("Content.Home.view");
                return;
            }
            return;
        },
        errorFun: function(){
            $rootScope.Logined = false;
            if ($rootScope.specialstatue[$rootScope.toStateName] !== 1) {
                $state.go("UserWelcome.Login");
            }
        },
        ajaxErrorFun: function(){
            $state.go("UserWelcome.Login");
        }
    });

}]);

fmApp.factory('myInterceptor', ['$cacheFactory', '$injector', '$q', '$rootScope','appVersion',
    function($cacheFactory, $injector, $q, $rootScope,appVersion) {
    var myInterceptor = {
        request:function(config){
            if(/rzSliderTpl/.test(config.url)) {
                return config;
            }
            if (/\?/g.test(config.url)) {
                config.url += "&_dspuser=" + $rootScope.userName||"";
            } else {
                config.url += "?_dspuser=" + $rootScope.userName||"";
            }
            config.url+="&app_version=" + appVersion;
            return config;
        },
        response: function(resp){
            //invoke  同步调用
            $injector.invoke(['$http','$state',function($http, $state){
                //退出登录清除所有缓存
                /*if('UserWelcome.Logout' == $state.$current.toString()){
                 resp.config.cache.removeAll();
                 }*/
                //if(resp.data == '{"rCode":"NO_COOKIE_ERROR"}' || resp.data == '{"rCode":"NO_SESSION_ERROR"}'){
                if(resp.data.rCode == 'NO_COOKIE_ERROR' || resp.data.rCode == 'NO_SESSION_ERROR'){
                    resp.config.cache.remove(resp.config.url);
                    $state.go("UserWelcome.Login");
                }
            }]);
            return resp;
        }
    };
    return myInterceptor;
}]);

angular.element(document.getElementsByTagName('head')).append(angular.element('<base href="' + window.location.pathname + '" />'));