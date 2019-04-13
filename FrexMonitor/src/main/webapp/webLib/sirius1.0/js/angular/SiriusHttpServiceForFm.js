/**
 * Created by zy13266660221 on 2016/3/1.
 */


var SiriusHttpService = angular.module('Sirius.HttpService', []);

SiriusHttpService.factory('JQueryAjaxService', ['$q', function ($q) {
    return {
        JAjax: function (options) {//执行一个promise
            var deferred = $q.defer();
            var promise = deferred.promise;
            var baseOptions = {
                type: "post",
                url: "",
                timeout: "120000",
                data: {},
                dataType: "json",
                cache: false,
                success: function (msg) {
                    deferred.resolve(msg);//ajax执行成功
                },
                error: function (msg) {
                    deferred.reject(msg);//ajax执行失败
                }
            };
            options = $.extend(baseOptions, options);
            $.ajax(options);
            return promise;
        },
        JAjaxAll: function (options) {//一次性执行多个promise
            var promiseList = [];
            var that = this;
            angular.forEach(options.ajaxParamsList, function (ajaxParams) {
                promiseList.push(that.JAjax(ajaxParams));
            });
            return $q.all(promiseList);
        }
    }
}]);


SiriusHttpService.factory('SiriusHttpService', ['JQueryAjaxService', function (JQueryAjaxService) {

    /*
     key: siriusHttpStateName,
     value: SIRIUS_READY -- 准备状态，没有通信过/
     SIRIUS_CONNECT -- 正在通信/
     SIRIUS_ERROR -- 业务逻辑失败 errorCode!=0/
     SIRIUS_FINISH -- 业务逻辑成功 errorCode ==0/
     SIRIUS_HTTP_ERROR -- ajxa通信错误

     * */
    var _siriusHttpState = {};

    return {
        SAjax: function (options) {

            var baseOptions = {
                successFun: function () {
                },
                errorMap: {},
                scope: {},
                errorFun: function () {
                },
                asyncF: true,
                ajaxErrorFun: function () {
                },
                siriusHttpType: "SIRIUS_HTTP_NO_LIMIT",
                siriusHttpStateName: "NOT_SET"
            };
            options = $.extend(true, baseOptions, options);

            var loadingModal = $("#loading_modal");
            if (options.loadingFlag) {
                loadingModal.css({'display': 'block'});
                setTimeout(function () {
                    loadingModal.css({'opacity': '1', 'pointer-events': 'auto'});
                }, 300);
            }

            //有限制， 并且正在通信
            if (options.siriusHttpType != "SIRIUS_HTTP_NO_LIMIT") {
                if (_siriusHttpState[options.siriusHttpStateName] == "SIRIUS_CONNECT") {
                    return;
                }
            }
            // 设置通信状态
            _siriusHttpState[options.siriusHttpStateName] = "SIRIUS_CONNECT";

            /* ajax 成功： 业务有可能成功，也有可能失败
             */
            function successF(msg) {
                //设置通信结束
                if (msg.errorCode == '0' || msg.succeed == '1' || msg.rCode == '0') {
                    _siriusHttpState[options.siriusHttpStateName] = "SIRIUS_FINISH";
                    options.successFun(msg);//业务执行成功
                    return false;
                } else {
                    if (msg.errorCode == undefined && msg.succeed == undefined && msg.rCode == undefined) {
                        _siriusHttpState[options.siriusHttpStateName] = "SIRIUS_FINISH";
                        options.successFun(msg);//业务执行成功
                        return false;
                    }
                    _siriusHttpState[options.siriusHttpStateName] = "SIRIUS_ERROR";
                    if (msg.rCode == '999') {//错误码999代表该用户session已失效或权限获取失败，此时，冒泡notLogin事件
                        options.scope.$emit('notLogin');
                        return false;
                    }
                    if (msg.rCode == '99') {
                        options.scope.$emit('noPermission', msg.msg);
                        return false;
                    }
                    options.errorFun(msg);//业务执行失败
                    return false;
                }
            }

            function errorF(msg) {
                _siriusHttpState[options.siriusHttpStateName] = "SIRIUS_HTTP_ERROR";
                options.ajaxErrorFun(msg);//ajax执行失败
                return false;
            }

            var ajaxFun = JQueryAjaxService.JAjax;
            if (options.allFlag) {//判断是否是合并的promise list
                ajaxFun = JQueryAjaxService.JAjaxAll;
            }

            ajaxFun.call(JQueryAjaxService, options).then(function (msg) {
                var phase = options.scope.$$phase;
                if (phase == '$apply' || phase == '$digest') {
                    successF(msg);
                } else {
                    options.scope.$apply(function () {
                        successF(msg);
                    });
                }
            }, function (msg) {
                var phase = options.scope.$$phase;
                if (phase == '$apply' || phase == '$digest') {
                    errorF(msg);
                } else {
                    options.scope.$apply(function () {
                        errorF(msg);
                    });
                }
            })["finally"](function () {
                if (options.loadingFlag) {
                    loadingModal.css({'opacity': '0', 'pointer-events': 'none'});
                    setTimeout(function () {
                        loadingModal.css({'display': 'none'});
                    }, 300);
                }
            });
        },
        getState: function () {
            return _siriusHttpState;
        },
        initState: function (siriusHttpStateName) {
            _siriusHttpState[siriusHttpStateName] = "SIRIUS_READY";
        },
        setStateError: function (siriusHttpStateName) {
            _siriusHttpState[siriusHttpStateName] = "SIRIUS_ERROR";
        }
    }
}]);


/*  ModelService Example
 SiriusServie.factory('SiriusModelService', function(SiriusHttpService) {
 var baseOptions = {
 scope:{},
 asyncF: true,
 successFun: function(){},
 errorFun: function(){},
 ajaxErrorFun: function(){},
 siriusHttpType: "SIRIUS_HTTP_NO_LIMIT",
 siriusHttpStateName: "NOT_SET"
 };

 return {
 saveExampleData: function (option) {
 option = $.extend(true, baseOptions, option);
 SiriusHttpService.SAjax({
 url: 'common/example.do',
 scope: option.scope,
 siriusHttpType: "SIRIUS_HTTP_LIMIT",
 data: {
 'from': option.start,
 'limit': option.length,
 'category': option.category,
 'keyword': option.keyword
 },
 successFun: option.successFun,
 errorFun: option.errorFun,
 ajaxErrorFun: option.ajaxErrorFun,
 })
 }
 }
 });

 // in $controller：
 $scope.init = function(){
 $scope.exampleDataState = "exampleDataState";
 SiriusHttpService.initState( $scope.exampleDataState);
 $scope.httpState = SiriusHttpService.getState();
 }

 SiriusModelService.saveExampleData({
 option: $scope,
 siriusHttpStateName: siriusHttpStateName,
 successFun: function(data){
 },
 errorFun: function(data){
 }
 })

 */