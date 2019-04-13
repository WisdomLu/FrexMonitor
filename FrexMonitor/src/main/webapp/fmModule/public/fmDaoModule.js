/**
 * Created by dell on 2016/3/4.
 */

var fmDaoService = angular.module('fm.daoService', []);

fmDaoService.factory('fmDaoService', ['SiriusHttpService', function (SiriusHttpService) {
    var baseOptions = {
        scope: {},
        asyncF: true,
        successFun: function () {
        },
        errorFun: function () {
        },
        ajaxErrorFun: function () {
        },
        siriusHttpType: "SIRIUS_HTTP_NO_LIMIT",
        siriusHttpStateName: "NOT_SET"
    };
    return {
        getUserInfo: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/getUserInfo.action',
                scope: option.scope,
                data: {},
                successFun: option.successFun,
                errorFun: option.errorFun,
                ajaxErrorFun: option.ajaxErrorFun
            })
        },
        login: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            var errorMap = {
                "-1": "请输入注册邮箱",
                "-2": "请输入正确密码",
                "-3": "请输入验证码"
            };
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/loginInvenoUser.action',
                scope: option.scope,
                data: {
                    'userBean.user.username': option.user.username,
                    'userBean.user.password': option.user.password,
                    'userBean.checkCode': option.user.checkcode
                },
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(errorMap[msg.rCode] || msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        logout: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/loginoutInvenoUser.action',
                scope: option.scope,
                data: {},
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: 'Logout',
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function () {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        validFindpwd: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/validFindpwd.action',
                scope: option.scope,
                data: {
                    'userBean.user.checkcode': option.user.checkcode
                },
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        resetPwd: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/resetPwd.action',
                scope: option.scope,
                data: option.params,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getVerifyCode: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            var errorMap = {
                "-1": "该邮箱已经注册过",
                "-4": "不存在该用户"
            };
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/sendCodeEmail.action',
                scope: option.scope,
//                timeout: 10000,
                data: option.params,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(errorMap[msg.rCode] || msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        register: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            var errorMap = {
                "-1": "该邮箱已注册",
                "-2": "验证码过期或者没有发送获取验证码邮件",
                "-3": "验证码错误"
            };
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/saveUser.action',
                scope: option.scope,
                data: {
                    'userBean.user.email': option.user.username,
                    'userBean.registerCode': option.user.verifyCode,
                    'userBean.user.password': option.user.password,
                    'register-repassword': option.user.confirmPassword
                },
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(errorMap[msg.rCode] || msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getSpreadPlanList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/getExtList.action',
                scope: option.scope,
                data: option.param,
                successFun: option.successFun,
                loadingFlag: option.loadingFlag,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: "spread",
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getValidVideoList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/getVideoList.action',
                scope: option.scope,
                data: {
                    'videoBean.adVideo.status': 2
                },
                successFun: option.successFun,
                loadingFlag: option.loadingFlag,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: "spread_video",
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        getAllDynamicList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/getDynamicEffectlList.action',
                scope: option.scope,
                data: {
                	"voBean.pagin.toPage": 1,
                    "voBean.pagin.pageSize": -1
                },
                successFun: option.successFun,
                loadingFlag: option.loadingFlag,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: "spread_dynamic",
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        getIndustryTypeList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/getIndustryTypeList.action',
                scope: option.scope,
                data: option.param,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        createOrUpdateSpreadPlan: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.plan.submitUrl,
                scope: option.scope,
                data: {
                    'planBean.planId': option.plan.id,
                    'planBean.planName': option.plan.planName,
                    'planBean.thresholdType': option.plan.thresholdType,
                    'planBean.threshold': option.plan.threshold,
                    'planBean.isUniform': option.plan.isUniform
                },
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getAdPoseSizeList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: "/fm/admin/adcenter/getAdPositionSizeList.action",
                scope: option.scope,
                data: {
                    "adPositionBean.adPositionTypeId": option.putSpec.adPoseType
                },
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        turnOnOffPlan: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: "/fm/admin/adcenter/TurnOn_offExtplan.action",
                scope: option.scope,
                data: {
                    "planBean.extention.id": option.plan.id
                },
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getGeo: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.geoUrl,
                scope: option.scope,
                data: option.data,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getAdById: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/getAdById.action',
                scope: option.scope,
                data: option.params,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getAdList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/getAdList.action',
                scope: option.scope,
                data: option.param,
                loadingFlag: true,
                successFun: option.successFun,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: "ad",
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        turnOnOffAd: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/updateAdOn_Offline.action',
                scope: option.scope,
                data: {
                    "advertisementBean.advertisement.id": option.ad.id
                },
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        copyAd: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/copyAd.action',
                scope: option.scope,
                data: {
                    "advertisementBean.advertisement.id": option.ad.id
                },
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        createOrUpdateAd: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.submitUrl,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getOriPkgList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/getAdOrientationPkgList.action',
                scope: option.scope,
                data: option.param,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        }, getOriPkgById: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/getAdOrientationPkgById.action',
                scope: option.scope,
                data: option.params,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getMsgList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/system/getMessageList.action',
                scope: option.scope,
                data: option.param,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        updatePassword: function (option) {
            var errorMap = {
                '-1': "您尚未登陆",
                '-2': "修改失败"
            };
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/user/updatePassword.action',
                scope: option.scope,
                data: option.param,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(errorMap[msg.rCode] || msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getIndustryTypeList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adcenter/getIndustryTypeList.action',
                scope: option.scope,
                data: {},
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getMaterialList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/getMaterialList.action',
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getMatSizeListForSearch: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/getMatSizeListForSearch.action',
                scope: option.scope,
                data: option.params,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        updateMaterial: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/updateMaterial.action',
                scope: option.scope,
                data: option.params,
                successFun: option.successFun,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.httpStateName,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        deleteMaterial: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/deleteMaterial.action',
                scope: option.scope,
                data: option.params,
                successFun: option.successFun,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.httpStateName,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        saveMateria: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/saveMateria.action',
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });

        },
        getAppList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/application/getApplicationList.action',
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        getAppInfo: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/application/getAppInfo.action',
                scope: option.scope,
                data: option.params,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        getAppTypeList: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/application/getAppTypeList.action',
                scope: option.scope,
                data: {},
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        deleteApp: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/application/deleteApp.action',
                scope: option.scope,
                data: option.params,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        createOrUpdateApp: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.submitUrl,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        getReportData: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.url,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        exportReportData: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.url,
                scope: option.scope,
                data: option.params,
//                timeout: 20000,
                loadingFlag: true,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        createOrUpdateOriPkg: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.submitUrl,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        deleteOriPkg: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/deleteOrientation.action',
                scope: option.scope,
                data: option.params,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        saveOrUpdateOpenUser: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.submitUrl,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        createOrUpdateVideo: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.submitUrl,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },

        //广告创意
        /*
         * wc,  submitUrl 这个东西之前放上层干嘛
         */
        selectDesign: function (option) {
            option = $.extend(true, {
                submitUrl:  '/fm/admin/adcenter/selectDesign.action'
            }, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.submitUrl,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.httpStateName || option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        getDynamicEffectInfo: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: '/fm/admin/adtool/queryDynamicEffect.action',
                scope: option.scope,
                data: option.params,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
        createOrUpdateDynamicEffect: function (option) {
            option = $.extend(true, {}, baseOptions, option);
            SiriusHttpService.SAjax({
                url: option.submitUrl,
                scope: option.scope,
                data: option.params,
                loadingFlag: true,
                siriusHttpType: "SIRIUS_HTTP_LIMIT",
                siriusHttpStateName: option.scope.formName,
                successFun: option.successFun,
                errorFun: function (msg) {
                    option.errorFun(msg.msg || "未知错误", msg.rCode);
                },
                ajaxErrorFun: function (msg) {
                    option.errorFun("网络连接异常，请刷新页面");
                }
            });
        },
    }
}]).factory('fmUploadFileService', function () {
    return {
        uploadFile: function (options) {
            var baseOptions = {
                url: '',
                scope: {},
                successFun: function () {
                },
                errorMap: {},
                errorFun: function () {
                },
                inputElemt: {},
                beanName: '',
                elemtId: '',
                progressBarId: '',
                progressPercent: ''
            };
            options = $.extend(baseOptions, options);

            $.invenoFormFileUpload({
                url: options.url,
                secureuri: false,
                name: options.beanName,
                fileElementId: options.elemtId,
                progressBarId: options.progressBarId,
                progressPercent: options.progressPercent,
                dataType: 'json',
                cache: false,
                success: function (data) {
                    data = JSON.parse(data);
                    options.scope.$apply(function () {
                        if (data["rCode"] == 0 || data['succeed'] == '1') {
                            options.successFun(data, options);
                        } else {
                            options.errorFun(options.errorMap[data["rCode"]] || data.msg || '请选择文件');
                        }
                    });
                },
                error: function () {
                    options.scope.$apply(function () {
                        options.errorFun('系统错误，请稍后再试');
                    })
                }
            });
        }
    }
});

