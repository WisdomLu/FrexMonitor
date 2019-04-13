/**
 * Created by Alex on 2018/12/29.
 */
var fmStrategyModule = angular.module('fm.strategyModule', ['ngRoute', 'ui.router']);


fmStrategyModule.config([
    '$stateProvider',
    '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider.state("Content.Strategy", {
            url: "/Strategy",
            views: {
                'fm_content_window': {
                    templateUrl: "fmModule/strategy/views/index.html"
                }
            }
        }).state("Content.Strategy.stgyInfo", {
            url: "/StgyInfo",
            views: {
                'fm_strategy_window': {
                    templateUrl: "fmModule/strategy/views/stgyInfo.html"
                }
            }
        }).state("Content.Strategy.stgyUpdate", {
            url: "/StgyUpdate",
            views: {
                'fm_strategy_window': {
                    templateUrl: "fmModule/strategy/views/stgyUpdate.html"
                }
            }
        });;
    }
]);

fmStrategyModule.controller('strategyCtrl', ['$scope', 'SiriusHttpService', '$state',
    function ($scope, SiriusHttpService, $state) {
        $scope.$watch('toStateName', function (newValue) {
            var stateName = newValue.split('.')[2];
            if (stateName == 'stgyInfo') {
                $scope.activeType = 1;
            } else if (stateName == 'stgyUpdate') {
                $scope.activeType = 2;
            }
        });
        $scope.currentForm = {
            name: '',
            scope: null,
            submitFun: null,
            editFlag: true
        };
        $scope.initForm = function (formName, scope, submitFun) {
            $scope.currentForm.name = formName;
            $scope.currentForm.scope = scope;
            $scope.currentForm.submitFun = submitFun;
            SiriusHttpService.initState(formName);
            scope.siriusFormState = SiriusHttpService.getState();
        };
        $scope.cancel = function () {
            if ($state.current.name != 'Content.Strategy.stgyUpdate') {
                $scope.currentForm.editFlag = false;
            } else {
                $state.go('Content.Home.view');
            }
        };
        $scope.submit = function () {
            var formName = $scope.currentForm.name;
            if (!$scope.currentForm.scope[formName].$valid) {
                SiriusHttpService.setStateError(formName);
                $scope.currentForm.scope[formName].siriusAutoFormError();
                return;
            }
            if ($scope.currentForm.submitFun) {
                $scope.currentForm.submitFun();
            }
        };

    }]).controller('stgyInfoCtrl', ['$rootScope','$scope', '$state', 'SiriusHttpService', 'commonMethods', 'fmDaoService',
    function ($rootScope,$scope, $state, SiriusHttpService, commonMethods, fmDaoService) {
        $scope.stgyInfo = {email:$rootScope.userName};
        var getUserInfo = function () {
            var ajaxParamsList = [];
            var ajaxParamsForOpenUser = {url: '/fm/strategy/getStgy.action', data: {}};
            ajaxParamsList.push(ajaxParamsForOpenUser);
            SiriusHttpService.SAjax({
                scope: $scope,
                ajaxParamsList: ajaxParamsList,
                allFlag: true,
                successFun: function (msgList) {
                    if (msgList && msgList.length > 0) {
                        angular.extend($scope.userInfo, msgList[0].data);
                        var imgs = msgList[0].imgs;
                        if ($scope.userInfo && !commonMethods.isEmptyObj($scope.userInfo)) {
                            if ($scope.userInfo.id) {
                                $scope.currentForm.editFlag = false;
                                $scope.accountFixedUserType = $scope.userInfo.userType;
                            }
                            if($scope.userInfo.userType == '2') {
                                $state.go('Content.Strategy.stgyInfo.personalUser');
                            } else {
                                $state.go('Content.Strategy.stgyInfo.enterpriseUser');
                            }
                            if($scope.userInfo.status == '3') {
                                $scope.showMessageTip('很抱歉，您的账户信息审核不通过，理由是：'+$scope.userInfo.noPassReason,'','error');
                            }
                            if (imgs && imgs.length > 0) {
                                if ($scope.userInfo.userType == '1') {
                                    $scope.userInfo.businessLicensePath = imgs[0].fileUrl;
                                    if (imgs[1]) {
                                        $scope.userInfo.qualificationsPath = imgs[1].fileUrl;
                                    }
                                    $scope.userInfo.businessLicenseUpLoadFlag = true;
                                } else {
                                    $scope.userInfo.certifObversePath = imgs[0].fileUrl;
                                    if (imgs[1]) {
                                        $scope.userInfo.qualificationsPath = imgs[1].fileUrl;
                                    }
                                    $scope.userInfo.certifObverseUploadFlag = true;
                                }
                            }
                            if ($scope.userInfo.fixedLineTel) {
                                var fixedLineTels = $scope.userInfo.fixedLineTel.split('-');
                                $scope.userInfo.fixedLineTelObj = {
                                    areaCode: fixedLineTels[0],
                                    no: fixedLineTels[1]
                                };
                            }
                            if ($scope.userInfo.telNo) {
                                $scope.userInfo.telNo = parseInt($scope.userInfo.telNo);
                            }
                            if ($scope.userInfo.qq) {
                                $scope.userInfo.qq = parseInt($scope.userInfo.qq);
                            }
                        }
                        $scope.industryTypeList = msgList[1].data;
                        if ($scope.industryTypeList && $scope.industryTypeList.length > 0) {
                            if (!$scope.userInfo.businessType) {
                                $scope.userInfo.businessType = $scope.industryTypeList[0].id;
                            }
                        }
                    }
                },
                errorFun: function (msg) {
                    $scope.showMessageTip(msg, '', 'error');
                },
                ajaxErrorFun: function () {
                    $scope.showMessageTip('网络连接失败，请刷新页面', '', 'error');
                }
            })

        };
        getUserInfo();
        $scope.activeUserType = 1;
        $scope.$watch('toStateName', function (newValue) {
            if (newValue == 'Content.Account.userInfo.enterpriseUser') {
                $scope.userInfo.userType = $scope.activeUserType = 1;
            } else if (newValue == 'Content.Account.userInfo.personalUser') {
                $scope.userInfo.userType = $scope.activeUserType = 2;
            }
            if (newValue == 'Content.Account.userInfo') {
                $state.go('Content.Account.userInfo.enterpriseUser');
            }
        });

        $scope.editUser = function () {
            $scope.currentForm.editFlag = !$scope.currentForm.editFlag;
        };
        $scope.submitUserInfo = function () {
            var msg = '';
            if (!$scope.userInfo.id) {
                msg = "是否确认提交，进行资质审核（请注意，账户类型之后将不可更改）？";
            } else {
                msg = "是否确认提交，进行资质审核？";
            }
            $scope.confirm({
                title: '确认提交',
                message: msg
            }).on(function (isOk) {
                var businessLicensePath = '';
                if($scope.userInfo.businessLicensePath && $scope.userInfo.businessLicensePath.split('?').length > 1) {
                    businessLicensePath = $scope.userInfo.businessLicensePath;
                }
                var certifObversePath = '';
                if($scope.userInfo.certifObversePath && $scope.userInfo.certifObversePath.split('?').length > 1) {
                    certifObversePath = $scope.userInfo.certifObversePath;
                }
                if (isOk) {
                    fmDaoService.saveOrUpdateOpenUser({
                        submitUrl: '/fm/admin/openuser/saveOpenUser.action',
                        scope: $scope,
                        params: {
                            'openUserBean.openUser.userType': commonMethods.nullStr($scope.userInfo.userType),
                            'openUserBean.openUser.compName': commonMethods.nullStr($scope.userInfo.compName),
                            'openUserBean.openUser.compUrl': commonMethods.nullStr($scope.userInfo.compUrl),
                            'openUserBean.openUser.orgNo': commonMethods.nullStr($scope.userInfo.orgNo),
                            'openUserBean.openUser.businessType': commonMethods.nullStr($scope.userInfo.businessType),
                            'openUserBean.openUser.businessLicensePath': commonMethods.nullStr(businessLicensePath),
                            'openUserBean.openUser.qualificationsPath': commonMethods.nullStr($scope.userInfo.qualificationsPath),
                            'openUserBean.openUser.contactName': commonMethods.nullStr($scope.userInfo.contactName),
                            'openUserBean.openUser.addrDetail': commonMethods.nullStr($scope.userInfo.addrDetail),
                            'openUserBean.openUser.telNo': commonMethods.nullStr($scope.userInfo.telNo),
                            'openUserBean.openUser.email': commonMethods.nullStr($scope.userInfo.email),
                            'openUserBean.openUser.fixedLineTel': commonMethods.nullStr($scope.userInfo.fixedLineTelObj ? $scope.userInfo.fixedLineTelObj.areaCode + '-' + $scope.userInfo.fixedLineTelObj.no : ''),
                            'openUserBean.openUser.qq': commonMethods.nullStr($scope.userInfo.qq),
                            'openUserBean.openUser.certifNo': commonMethods.nullStr($scope.userInfo.certifNo),
                            'openUserBean.openUser.certifObversePath': commonMethods.nullStr(certifObversePath),
                            'openUserBean.openUser.parentId': commonMethods.nullStr($scope.userInfo.parentId)
                        },
                        successFun: function () {
                            $scope.showMessageTip('更新用户信息成功', '', 'success');
                            $scope.currentForm.editFlag = false;
                            $scope.accountFixedUserType = $scope.userInfo.userType;
                            return;
                        },
                        errorFun: function (msg) {
                            $scope.showMessageTip(msg, '', 'error');
                        }
                    });
                }
            });

        };

    }].controller('stgyUpdateCtrl', ['$scope', '$state', 'fmDaoService',
    function ($scope, $state, fmDaoService) {
        $scope.currentForm.editFlag = true;
        $scope.passwordObj = {
            password: '',
            newPassword: '',
            newPassword2: ''
        };
        var formName = 'update_password_form';
        var submitFun = function () {
            var param = {
                "userBean.user.password": $scope.passwordObj.password,
                "userBean.user.newPassword": $scope.passwordObj.newPassword
            };
            fmDaoService.updatePassword({
                scope: $scope,
                param: param,
                successFun: function () {
                    $scope.showMessageTip('更新密码成功，现在跳转登录页面', function () {
                        $state.go('UserWelcome.Login', {}, {reload: true});
                    }, 'success');
                },
                errorFun: function (msg) {
                    $scope.showMessageTip(msg, '', 'error');
                }
            })
        };
        $scope.initForm(formName, $scope, submitFun);

    }]));

