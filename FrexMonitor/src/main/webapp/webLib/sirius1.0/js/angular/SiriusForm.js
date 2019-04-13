/**
 * Created by zy13266660221 on 2016/3/1.
 */



var SiriusFormService = angular.module('Sirius.Form', []);


/**
 * form
 *
 */
SiriusFormService.directive('siriusForm', ['$rootScope', function ($rootScope) {
    return {
        restrict: 'AE',
        priority: 9,
        link: function (scope, elem, attrs) {
            var formName = attrs.name;
            scope[formName].siriusFormError = "";

            scope[formName].setSiriusFormError = function (errorStr) {
                scope[formName].siriusFormError = errorStr;
            };

            scope[formName].getSiriusFormError = function () {
                return scope[formName].siriusFormError;
            };

            scope[formName].siriusAutoFormError = function () {
                var inputEls = elem.find("[sirius-input]");
                for (var i = 0; i < inputEls.length; i++) {
                    if (scope[formName][inputEls[i].name].$invalid) {
                        //scope[formName].siriusFormError = $(inputEls[i]).attr("sirius-input");
                        $rootScope.$broadcast('formInvalid', inputEls[i].name);
                        return;
                    }
                }
            }

        }
    };
}]);


/**
 * submit btn
 *
 */
SiriusFormService.directive('siriusFormSubmit', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: -1,
        link: function (scope, elem, attrs) {


            elem.on('click', function () {
                var formName = '';
                var form = null;
                if (attrs.formName == undefined) {
//                    formName = elem.parents("form")[0].name;
                	formName = $(elem.parents("form")[0]).attr("name");
                    form = elem.parents("form").eq(0)
                } else {
                    formName = $parse(attrs.formName)(scope);
                    form = angular.element("form[name=" + formName + "]");
                }
                var fn = $parse(attrs.siriusFormSubmit);
                //猎豹浏览器自动填充
                $('input[ng-model]', form).each(function (index, item) {
                    if (angular.element(this).attr('type') !== 'checkbox' && angular.element(this).attr('type') !== 'radio') {
                        angular.element(this).controller('ngModel').$setViewValue($(this).val());
                    }
                });

                var formScope = scope;
                if (attrs.formScope != undefined) {
                    formScope = $parse(attrs.formScope)(scope);
                }
                // 设置所有的都已修改
                form.find("[sirius-input]").each(function (index, item) {
                    formScope[formName][item.name]["siriusDirty"] = true;
                });

                formScope[formName].siriusDirtyClean = function(){
                    form.find("[sirius-input]").each(function (index, item) {
                        formScope[formName][item.name]["siriusDirty"] = false;
                    });
                }

                scope.$apply(function (e) {
                    if (fn) {
                        var formScope = scope;
                        if (attrs.formScope) {
                            formScope = $parse(attrs.formScope)(scope);
                        }
                        if (!formScope[formName].$valid) {
                            //SiriusHttpService.setStateError(formName);
                            formScope[formName].siriusAutoFormError();
                            return;
                        }
                        fn(scope, {$event: e});
                    }
                });
                return false;
            });

        }
    };
}]);


/*
 * 自定义siriusDirty， ie， placehold兼容性
 */
SiriusFormService.directive('siriusInput', function () {
    return {
        restrict: 'AE',
        priority: 9,
        link: function (scope, elem, attrs) {
//            var formName = elem.parents("form")[0].name;
        	var formName = $(elem.parents("form")[0]).attr("name");
            var inputName = attrs.name;

            scope[formName][inputName]["siriusDirty"] = false;

            elem.on('keydown', function () {
                scope.$apply(function () {
                    scope[formName][inputName]["siriusDirty"] = true;
                });
            });
        }
    };
});


/*
 * enter 提交
 */
SiriusFormService.directive('siriusEnterInput', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: -1,
        link: function (scope, elem, attrs) {
            elem.on('keydown', function (e) {
                var curKey = e.which;
                var fn = $parse(attrs.siriusEnterInput);
                if (curKey == 13) {
                    if (fn) {
                        scope.$apply(function () {
                            fn(scope);
                        });
                    } else {
                        elem.click();
                    }
                    return false;
                }
            });
        }
    };
}]);


/* tooltip*/
SiriusFormService.directive('siriusTooltipSwitch', function () {
    return {
        restrict: 'AE',
        template: function (elem, attrs) {
            var formName = elem.parents("form")[0].name;
            var inputName = attrs.name;
            var tooltip = "<div class='dsp-form-tooltip' ng-class=\"getTooltipClass(\'" + formName + "\',\'" + inputName + "\')\" sirius-tooltip ><span class='text-middle'><span>{{" + formName + "[\'" + inputName + "\']" + ".tooltip" + "}}</span></span></div>";
            elem.after(tooltip);
        },
        priority: -1,
        link: function (scope, elem, attrs) {
            var toolTip = elem.parent().find("[sirius-tooltip]");
            toolTip.extend({
                _show: function () {
                    var left = elem.position().left + elem.outerWidth();
                    if (attrs.extLength != undefined) {
                        left += parseFloat(attrs.extLength);
                    }
                    this.css({
                        left: left
                    });
                    this.show();
                }
            });
            toolTip.hide();
            scope.getTooltipClass = function (formName, inputName) {
                if (scope[formName][inputName].$invalid && scope[formName][inputName].siriusDirty) {
                    return 'danger';
                } else {
                    return 'normal';
                }
            };
            var formName = elem.parents("form")[0].name;
            var inputName = attrs.name;
            scope.$watch(function () {
                return angular.toJson([scope[formName][inputName].$invalid, scope[formName][inputName].siriusDirty]);
            }, function (newValue) {
                newValue = angular.fromJson(newValue);
                if (newValue[0] && newValue[1]) {
                    toolTip._show();
                    scope[formName][inputName]["tooltip"] = attrs.dangerTooltip;
                } else {
                    if (!newValue[0] && newValue[1]) {
                        toolTip.hide();
                    }
                    scope[formName][inputName]["tooltip"] = attrs.normalTooltip;
                }
            });

            elem.on('mouseenter', function (e) {
                toolTip._show();
            });
            elem.on('focus', function (e) {
                toolTip._show();
            });
            elem.on('mouseleave', function (e) {
                toolTip.hide();
            });
            elem.on('blur', function (e) {
                toolTip.hide();
            });
            scope.$on('formInvalid', function (e, targetName) {
                if (attrs.name == targetName) {
                    toolTip._show();
                }
            });
        }
    };
});

/**
 * GetVerifyCode btn
 *
 */
SiriusFormService.directive('siriusFormGetVerifyCode', ['$parse', '$interval', '$rootScope', function ($parse, $interval, $rootScope) {
    return {
        restrict: 'AE',
        priority: -1,
        link: function (scope, elem, attrs) {
            var formName = elem.parents("form")[0].name;
            var fn = $parse(attrs.siriusFormGetVerifyCode);
            var intervalTime = 0;
            scope[formName][attrs.id] = {};
            var init = function () {
                intervalTime = attrs.intervalTime;
                angular.element(elem).css({'font-size': '14px'});
                scope[formName][attrs.id].value = "获取验证码";
                scope[formName][attrs.id].disable = false;
            };
            init();
            elem.on('click', function (e) {
                if (scope[formName][attrs.mediaAccountName].$invalid) {
                    scope.$apply(function () {
                        scope[formName][attrs.mediaAccountName].siriusDirty = true;
                        $rootScope.$broadcast('formInvalid', 'username');
                    });
                    return;
                }
                scope[formName][attrs.id].disable = true;
                scope.$apply(function () {
                    fn(scope, {$event: e});
                });
                var interval = $interval(function () {
                    intervalTime--;
                    angular.element(elem).css({'font-size': '13px'});
                    scope[formName][attrs.id].value = "(" + intervalTime + ")秒后重新获取";
                    if (intervalTime <= 0) {
                        $interval.cancel(interval);
                        init();
                    }
                }, 1000);
            })

        }
    };
}]);
/**
 * date range picker
 */
SiriusFormService.directive('dateRangePicker', ['$rootScope','$timeout', '$parse', function ($rootScope,$timeout, $parse) {
    return {
        restrict: 'AE',
        priority: -1,
        link: function (scope, elem, attrs) {
            var minDate = $parse(attrs.minDate)(scope);

            var daterangepicker = elem.daterangepicker({
                "locale": {
                    "format": "YYYY-MM-DD",
                    "separator": "~",
                    "applyLabel": "确定",
                    "cancelLabel": "取消",
                    "fromLabel": "从",
                    "toLabel": "到",
                    "daysOfWeek": [
                        "日",
                        "一",
                        "二",
                        "三",
                        "四",
                        "五",
                        "六"
                    ],
                    "monthNames": [
                        "一月",
                        "二月",
                        "三月",
                        "四月",
                        "五月",
                        "六月",
                        "七月",
                        "八月",
                        "九月",
                        "十月",
                        "十一月",
                        "十二月"
                    ]
                },
                minDate: minDate
            });

            scope.$on('fastDateChange', function (e, startDate, endDate) {
                elem.data('daterangepicker').setStartDate(startDate);
                elem.data('daterangepicker').setEndDate(endDate);
            });

            elem.on('apply.daterangepicker', function (ev, picker) {
                var value = picker.startDate.format('YYYY-MM-DD') + '~' + picker.endDate.format('YYYY-MM-DD');
                $(this).val(value);
                angular.element(this).controller('ngModel').$setViewValue(value);
                var fn = $parse(attrs.dateRangePicker);
                scope.$apply(
                    function () {
                        fn(scope);
                    }
                );
            });

        }
    };
}]);

/**
 * 限制字数输入框指令
 */
SiriusFormService.directive('limitInput', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: -1,
        template: function (elem, attrs) {
            var maxLength = attrs.maxWordCount;
            var inputStr = "<input ";
            if (attrs.extend) {
                inputStr = inputStr + attrs.extend;
            }
            if (attrs.siriusNgClass) {
                inputStr = inputStr + " ng-class=" + "\"" + attrs.siriusNgClass + "\"";
            }
            inputStr = inputStr + ">";
            if (attrs.wordCountPrompt != "none") {
                var wordPromptHtml = "<span class='_word-count-prompt'>" + "{{" + attrs.id + ".wordCount | wordPrompt:" + maxLength + "}}" + "</span>";
                inputStr = inputStr + wordPromptHtml;
            }
            return inputStr;
        },
        link: function (scope, elem, attrs) {
            if (attrs.dspToolTip != undefined) {
                elem.find('textarea').tooltip({html: true});
            }
            angular.element(elem).css('position', 'relative');
            elem.find('input').addClass('limit-input');
            var maxLength = attrs.maxWordCount;
            scope[attrs.id] = {};
            scope[attrs.id].wordCount = 0;
            var contentLen = 0;
            elem.on('keydown', function (e) {
                if (contentLen >= maxLength) {
                    if (e.keyCode != '8' && e.keyCode != '37' && e.keyCode != '38' && e.keyCode != '39' && e.keyCode != '40') {
                        return false;
                    }
                }

            });
            elem.on('keyup', function (e) {
                if (e.keyCode != '37' && e.keyCode != '38' && e.keyCode != '39' && e.keyCode != '40') {
                    limitWord();
                }
            });
            elem.on('mousedown', function (e) {
                limitWord();
            });

            function limitWord() {
                contentLen = elem.find('input')[0].value.length;
                if (contentLen <= maxLength) {
                    scope.$apply(function () {
                        scope[attrs.id].wordCount = contentLen;
                    });
                }
                if (contentLen >= maxLength) {
                    elem.find('input')[0].value = elem.find('input')[0].value.substring(0, maxLength);
                    scope.$apply(function () {
                        $(elem.find('input')[0]).controller('ngModel').$setViewValue(elem.find('input')[0].value);
                    });
                }
            }

        }
    };
}]);
/**
 * 限制字数输入框指令
 */
SiriusFormService.directive('limitTextarea', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: -1,
        template: function (elem, attrs) {
            var maxLength = attrs.maxWordCount;
            var inputStr = "<textarea ";
            if (attrs.extend) {
                inputStr = inputStr + attrs.extend;
            }
            if (attrs.siriusNgClass) {
                inputStr = inputStr + " ng-class=" + "\"" + attrs.siriusNgClass + "\"";
            }
            inputStr = inputStr + "></textarea>";
            if (attrs.wordCountPrompt != "none") {
                var wordPromptHtml = "<span class='_word-count-prompt'>" + "{{" + attrs.id + ".wordCount | wordPrompt:" + maxLength + "}}" + "</span>";
                inputStr = inputStr + wordPromptHtml;
            }
            return inputStr;
        },
        link: function (scope, elem, attrs) {
            if (attrs.dspToolTip != undefined) {
                elem.find('textarea').tooltip({html: true});
            }
            angular.element(elem).css('position', 'relative');
            elem.find('textarea').addClass('limit-textarea');
            var maxLength = attrs.maxWordCount;
            scope[attrs.id] = {};
            scope[attrs.id].wordCount = 0;
            var contentLen = 0;
            elem.on('keydown', function (e) {
                if (contentLen >= maxLength) {
                    if (e.keyCode != '8' && e.keyCode != '37' && e.keyCode != '38' && e.keyCode != '39' && e.keyCode != '40') {
                        return false;
                    }
                }

            });
            elem.on('keyup', function (e) {
                if (e.keyCode != '37' && e.keyCode != '38' && e.keyCode != '39' && e.keyCode != '40') {
                    limitWord();
                }
            });
            elem.on('mousedown', function (e) {
                limitWord();

            });

            function limitWord() {
                contentLen = elem.find('textarea')[0].value.length;
                if (contentLen <= maxLength) {
                    scope.$apply(function () {
                        scope[attrs.id].wordCount = contentLen;
                    });
                }
                if (contentLen >= maxLength) {
                    elem.find('textarea')[0].value = elem.find('textarea')[0].value.substring(0, maxLength);
                }
            }

        }
    };
}]);
SiriusFormService.directive('siriusProcessBar', ['$compile', '$timeout', function ($compile, $timeout) {
    return {
        template: function (elem) {
            elem.append("<div id='process-outer-border'> </div>");
        },
        restrict: 'AE',
        priority: -1,
        link: function () {
            var processOuterBorderWidth = 0;
            $timeout(function () {
                var createAdProcessRowLeft = $('#create_ad_process_row').css('left');
                createAdProcessRowLeft = createAdProcessRowLeft.substring(0, createAdProcessRowLeft.length - 2);
                var createAdProcessRowMarginLeft = $('#create_ad_process_row').css('margin-left');
                createAdProcessRowMarginLeft = createAdProcessRowMarginLeft.substring(0, createAdProcessRowMarginLeft.length - 2);
                processOuterBorderWidth = parseFloat(createAdProcessRowLeft) + parseFloat(createAdProcessRowMarginLeft);
                $('#process-outer-border').css({
                    'position': 'absolute',
                    'left': 0,
                    'bottom': '-2px',
                    'background-color': '#ff6000',
                    'height': '3px',
                    'width': processOuterBorderWidth
                });

            }, 0);

        }
    };

}]);

SiriusFormService.directive('siriusConfirmPassword', ['$parse', function ($parse) {
    return {
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModelController) {
            ngModelController.$parsers.push(function (viewValue) {
                var password = $parse(attrs.siriusConfirmPassword)(scope);
                if (viewValue == password) {
                    ngModelController.$setValidity('siriusConfirmPassword', true);
                } else {
                    ngModelController.$setValidity('siriusConfirmPassword', false);
                }
                return viewValue;
            });
        }
    };

}]);

SiriusFormService.directive('siriusValidIdCard', ['commonMethods', function (commonMethods) {
    return {
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModelController) {
            ngModelController.$parsers.push(function (viewValue) {
                if (commonMethods.isValidIdNo(viewValue)) {
                    ngModelController.$setValidity('siriusValidIdCard', true);
                } else {
                    ngModelController.$setValidity('siriusValidIdCard', false);
                }
                return viewValue;
            });
        }
    };

}]);

SiriusFormService.directive('siriusAgreeProtocol', function () {
    return {
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModelController) {
            ngModelController.$parsers.push(function (viewValue) {
                if (viewValue && viewValue == "true") {
                    ngModelController.$setValidity('siriusAgreeProtocol', true);
                } else {
                    ngModelController.$setValidity('siriusAgreeProtocol', false);
                }
                if ("false" == viewValue) {
                    return false;
                }
                return viewValue;
            });
        }
    };

});

SiriusFormService.directive('siriusValidAreaCode', function () {
    return {
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModelController) {
            var re = /^0\d{2,3}$/;
            ngModelController.$parsers.push(function (viewValue) {
                if (!viewValue || re.test(viewValue)) {
                    ngModelController.$setValidity('siriusValidAreaCode', true);
                } else {
                    ngModelController.$setValidity('siriusValidAreaCode', false);
                }
                return viewValue;
            });
        }
    };

});

SiriusFormService.directive('siriusValidTelNo', function () {
    return {
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModelController) {
            var re = /^\d{7,8}$/;
            ngModelController.$parsers.push(function (viewValue) {
                if (!viewValue || re.test(viewValue)) {
                    ngModelController.$setValidity('siriusValidTelNo', true);
                } else {
                    ngModelController.$setValidity('siriusValidTelNo', false);
                }
                return viewValue;
            });
        }
    };

});

SiriusFormService.directive('siriusValidMobileNo', function () {
    return {
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModelController) {
            var re = /^1\d{10}$/;
            ngModelController.$parsers.push(function (viewValue) {
                if (!viewValue || re.test(viewValue)) {
                    ngModelController.$setValidity('siriusValidMobileNo', true);
                } else {
                    ngModelController.$setValidity('siriusValidMobileNo', false);
                }
                return viewValue;
            });
        }
    };

});

SiriusFormService.directive('siriusValidOrgCode', ['commonMethods', function (commonMethods) {
    return {
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModelController) {
            ngModelController.$parsers.push(function (viewValue) {
                if (!viewValue || commonMethods.isValidOrgCode(viewValue)) {
                    ngModelController.$setValidity('siriusValidOrgCode', true);
                } else {
                    ngModelController.$setValidity('siriusValidOrgCode', false);
                }
                return viewValue;
            });
        }
    };

}]);

SiriusFormService.directive('siriusValidPutTimes', ['commonMethods', function (commonMethods) {
    return {
        link: function (scope, elem, attrs) {
            scope.$watch(attrs.allTime, function (newValue) {
                var allPutTimes = newValue;
                setTimeout(function () {
                    scope.$apply(function () {
                        var allPutTimeElems = elem.find('[date-range-picker]');
                        $.each(allPutTimeElems, function (outerIndex,elem) {
                            var putTime = angular.element(elem).scope().putTime;
                            var putTimeModel = commonMethods.getDateModel(putTime);
                            if (allPutTimes && allPutTimes.length > 1) {
                                $.each(allPutTimes, function (innerIndex,pt) {
                                    if (innerIndex != outerIndex) {
                                        var pt = commonMethods.getDateModel(pt);
                                        if (pt.dateMax < putTimeModel.dateMin || pt.dateMin > putTimeModel.dateMax) {
                                            putTime.isValid = true;
                                            angular.element(elem).controller('ngModel').$setValidity('siriusValidPutTime', true);
                                        } else {
                                            if ((pt.timeMin >= putTimeModel.timeMin && pt.timeMin <= putTimeModel.timeMax)
                                                || (pt.timeMax >= putTimeModel.timeMin && pt.timeMax <= putTimeModel.timeMax)
                                                || (pt.timeMin < putTimeModel.timeMin && pt.timeMax > putTimeModel.timeMax)) {
                                                putTime.isValid = false;
                                                angular.element(elem).controller('ngModel').$setValidity('siriusValidPutTime', false);
                                                return false;
                                            } else {
                                                putTime.isValid = true;
                                                angular.element(elem).controller('ngModel').$setValidity('siriusValidPutTime', true);
                                            }
                                        }
                                    }
                                })
                            } else {
                                putTime.isValid = true;
                                angular.element(elem).controller('ngModel').$setValidity('siriusValidPutTime', true);
                            }
                        })
                    });
                }, 0);

            }, true);
        }
    };

}]);

SiriusFormService.directive('siriusPagination', ['$rootScope', function ($rootScope) {
    return {
        template: "<ul class='pagination' ng-click='changePage($event)'><li ng-class=\"{'disabled':isFirstPage}\" ng-click='previewPage($event)'><a>上一页</a></li><li ng-repeat='page in pageList' ng-class=\"{'disabled':page.disabled,'active':page.active}\"><a>{{page.content}}</a></li><li ng-click='nextPage($event)' ng-class=\"{'disabled':isLastPage}\"><a>下一页</a></li></ul>",
        replace: true,
        link: function (scope) {
            scope.$on('loadPages', function (e, totalPages, curPage, isInit) {
                if (totalPages) {
                    scope.curPage = curPage;
                    scope.totalPages = totalPages;
                    var pageList = scope.pageList = [];
                    if (curPage == 1) {
                        scope.isFirstPage = true;
                    } else {
                        scope.isFirstPage = false;
                    }
                    if (curPage == totalPages) {
                        scope.isLastPage = true;
                    } else {
                        scope.isLastPage = false;
                    }
                    if (totalPages <= 7) {
                        for (var i = 1; i <= totalPages; i++) {
                            pageList.push({
                                content: i,
                                active: i == curPage
                            });
                        }
                    } else {
                        if (curPage < 5) {
                            for (var i = 1; i <= 5; i++) {
                                pageList.push({
                                    content: i,
                                    active: i == curPage
                                });
                            }
                            pageList.push({
                                content: '...',
                                disabled: true
                            });
                            pageList.push({
                                content: totalPages,
                                active: totalPages == curPage
                            });
                        } else if (curPage >= 5 && curPage <= totalPages - 4) {
                            pageList.push({
                                content: 1
                            });
                            pageList.push({
                                content: '...',
                                disabled: true
                            });
                            for (var i = 0; i < 3; i++) {
                                pageList.push({
                                    content: curPage - 1 + i,
                                    active: i == 1
                                });
                            }
                            pageList.push({
                                content: '...',
                                disabled: true
                            });
                            pageList.push({
                                content: totalPages
                            });
                        } else {
                            pageList.push({
                                content: 1
                            });
                            pageList.push({
                                content: '...',
                                disabled: true
                            });
                            for (var i = totalPages - 4; i <= totalPages; i++) {
                                pageList.push({
                                    content: i,
                                    active: i == curPage
                                });
                            }
                        }
                    }
                } else {
                    scope.isFirstPage = true;
                    scope.isLastPage = true;
                }
                if (!isInit) {
                    $rootScope.$broadcast('toPage', scope.curPage);
                }
            });
            scope.previewPage = function (e) {
                if (scope.isFirstPage) {
                    return;
                }
                if (--scope.curPage == 1) {
                    scope.isFirstPage = true;
                }
                $rootScope.$broadcast('loadPages', scope.totalPages, scope.curPage);
                e.stopPropagation();
            };
            scope.nextPage = function (e) {
                if (scope.isLastPage) {
                    return;
                }
                if (++scope.curPage == scope.totalPages) {
                    scope.isLastPage = true;
                }
                $rootScope.$broadcast('loadPages', scope.totalPages, scope.curPage);
                e.stopPropagation();
            };
            scope.changePage = function (e) {
                if (!$(e.target).parent().hasClass('disabled') && !$(e.target).parent().hasClass('active')) {
                    $rootScope.$broadcast('loadPages', scope.totalPages, $(e.target).text());
                }
            }
        }
    }
}]);