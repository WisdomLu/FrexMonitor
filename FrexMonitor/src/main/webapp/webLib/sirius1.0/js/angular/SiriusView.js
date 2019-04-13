/**
 * Created by zy13266660221 on 2016/3/1.
 */



var SiriusViewService = angular.module('Sirius.View', []);


SiriusViewService.directive('siriusRemBody', function () {
    return {
        restrict: 'AE',
        link: function (scope, elem, attrs, ctrl) {
            basicRem(874, 150);
        }
    };

});


/**
 * 唤醒模态框
 */
SiriusViewService.directive('siriusModalCall', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: -1,
        link: function (scope, elem, attrs) {
            var modalWindow = $("#" + attrs.siriusModalCall);
            var modalContent = $("#" + attrs.siriusModalCall).find('[sirius-modal-content]');
            //点击modal外部内容关闭modal
            modalWindow.on('click', function (e) {
                scope.$apply(function () {
                    scope.modal[attrs.siriusModalCall].isCloseModal = 1;
                });
            });
            //通过点击modal内容框阻止冒泡事件来使modal不关闭
            if (modalContent) {
                modalContent.on('click', function (e) {
                    e.stopImmediatePropagation();
                });
            }
            elem.on('click', function () {
                if (!attrs.disabled) {
                    scope.$apply(function () {
                        scope.modal[attrs.siriusModalCall].isCloseModal = 0;
                        if ($parse(attrs.siriusBind)(scope)) {
                            scope.modal[attrs.siriusModalCall].bind = $parse(attrs.siriusBind)(scope);//通过siriusBind属性给modal初始化一个默认对象
                        } else {
                            scope.modal[attrs.siriusModalCall].bind = null;
                        }
                    });
                    modalWindow.css({'display': 'block'});
                    setTimeout(function () {
                        modalWindow.css({'opacity': '1', 'pointer-events': 'auto'});
                    }, 300);
                }
            });

            scope.$watch('modal', function (newValue) {
            	/*console.log(newValue);*/
                if (newValue[attrs.siriusModalCall].isCloseModal) {
                    modalWindow.css({'opacity': '0', 'pointer-events': 'none'});
                    setTimeout(function () {
                        modalWindow.css({'display': 'none'});
                    }, 300);
                }
            }, true);
        }
    };

}]);
/**
 * 覆盖层效果
 */
SiriusViewService.directive('siriusOverlay', function () {
    return {
        restrict: 'AE',
        priority: -1,
        link: function (scope, elem, attrs) {
            scope.$watch(attrs.siriusOverlay, function (newValue) {
                if (newValue) {
                    var events = attrs.events.split(',');
                    $(elem).parent().bind(events[0], function () {
                        $(elem).css({'display': 'block'});
                        setTimeout(function () {
                            $(elem).css({'opacity': '1', 'pointer-events': 'auto'});
                        }, attrs.transitionTime);
                    });
                    elem.bind(events[1], function () {
                        $(this).css({'opacity': '0', 'pointer-events': 'none'});
                        setTimeout(function () {
                            $(this).css({'display': 'none'});
                        }, attrs.transitionTime);
                    });
                }
            });


        }
    };

});
/**
 * loading progress
 */
SiriusViewService.directive('siriusLoadingOverlay', function () {
    return {
        restrict: 'AE',
        template: function (elem, attrs) {
            var htmlStr = "<div class='load-overlay'>" +
                "<div class='progress progress-striped active'>" +
                "<div id=" + attrs.progressBarId + " class='progress-bar progress-bar-success' role='progressbar'" +
                "aria-valuemin='0' aria-valuemax='100'>" +
                "<span class='sr-only'>加载中</span>" +
                "</div>" +
                "</div>" +
                "</div>";
            return htmlStr;
        },
        replace: true,
        link: function (scope, elem, attrs) {
            $("#" + attrs.progressBarId).css({
                'width': 0
            });
            scope.$watch(attrs.siriusLoadingOverlay, function (newValue) {
                if (newValue) {
                    elem.css({
                        display: 'block',
                        opacity: 1
                    });
                } else {
                    elem.css({
                        display: 'none',
                        opacity: 0
                    })
                }
            });

            elem.parent().find('img').on('load', function () {
                setTimeout(function () {
                    scope.$apply(function () {
                        $("#" + attrs.progressBarId).css({
                            'width': '100%'
                        });
                        scope[attrs.siriusLoadingOverlay] = false;
                        $("#" + attrs.progressBarId).css({
                            'width': 0
                        });
                    });
                }, 500);

            });
        }
    };

});
SiriusViewService.directive('siriusCheckBox', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: 20,
        link: function (scope, elem, attrs) {
            if (attrs.siriusTop != undefined) {
                scope.elePositionTop = $(elem).position().top;
            }

            elem.on('click', function (e) {
                if (attrs.siriusGeoCall != undefined) {
                    e.stopImmediatePropagation();
                }
                var activeBind = $parse(attrs.activeBind)(scope);
                scope.$apply(function () {
                    if (attrs.siriusRadio != undefined) {
                        var value = activeBind[attrs.siriusIndex];
                        if (!value) {
                            activeBind[attrs.siriusIndex] = !activeBind[attrs.siriusIndex];
                            for (var i in activeBind) {
                                if (i != attrs.siriusIndex) {
                                    activeBind[i] = false;
                                }
                            }
                        }
                    } else {
                        if (attrs.siriusOriCheck != undefined) {
                            var value = activeBind[attrs.siriusIndex];
                            if (!isUniqueTrue(activeBind) || (isUniqueTrue(activeBind) && !value)) {
                                if (attrs.siriusIndex == 0 && !value) {
                                    for (var i in activeBind) {
                                        if (i != 0) {
                                            activeBind[i] = false;
                                        }
                                    }
                                }
                                if (attrs.siriusIndex != 0 && activeBind[0] && !value) {
                                    activeBind[0] = false;
                                }
                                activeBind[attrs.siriusIndex] = !activeBind[attrs.siriusIndex];
                            }
                        } else {
                            activeBind[attrs.siriusIndex] = !activeBind[attrs.siriusIndex];
                        }

                    }

                    function isUniqueTrue(obj) {
                        var trueCount = 0;
                        for (var i in obj) {
                            if (obj[i]) {
                                trueCount++;
                            }
                        }
                        if (trueCount > 1) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                });
            });

        }
    };

}]);
SiriusViewService.directive('siriusGeoCall', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: 10,
        link: function (scope, elem, attrs) {
            $('.sirius-rem-body').bind('click', function () {
                $('.dsp-geo').css({
                    'display': 'none'
                });
            });
            $('.dsp-geo').bind('click', function (e) {
                e.stopImmediatePropagation();
            });
            elem.on('click', function (e) {
                var top = $(elem).offset().top - elem.scrollTop();
                var left = $(elem).offset().left - (240 - elem.width() / 2);
                var height = $(elem).outerHeight();
                var geoTop = top + height + 10;
                if ($('.dsp-geo').css('display') == 'block') {
                    $('.dsp-geo').css({
                        'display': 'none'
                    });
                } else {
                    $('.dsp-geo').css({
                        'display': 'block',
                        'top': geoTop,
                        'left': left
                    });
                    var fn = $parse(attrs.siriusGeoCall);
                    scope.$apply(function () {
                        fn(scope);
                    });
                }
            });

        }
    };
}]);
SiriusViewService.directive('siriusGeoCheckBox', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        priority: -1,
        link: function (scope, elem, attrs) {

            elem.on('click', function () {
                var activeBind = $parse(attrs.activeBind)(scope);
                scope.$apply(function () {
                    activeBind.checked = !activeBind.checked;
                    if (attrs.siriusGeoCheckBox) {
                        var fn = $parse(attrs.siriusGeoCheckBox);
                        fn(scope);
                    }
                });
            });


        }
    };

}]);
SiriusViewService.directive('siriusToggle', ['$parse', function ($parse) {
    return {
        restrict: 'AE',
        template: function (elem, attrs) {
            var htmlStr = "<div class='dsp-toggle'><div></div></div>";
            return htmlStr;
        },
        replace: true,
        priority: -1,
        link: function (scope, elem, attrs) {
            var fn = $parse(attrs.siriusToggle);
            var toggleOn = attrs.toggleOn;
            scope.$watch(attrs.siriusBind, function (newValue) {
                elem.tooltip({html: true});
                if (toggleOn == newValue) {
                    if ($(elem).hasClass('off')) {
                        $(elem).removeClass('off');
                    }
                    $(elem).addClass('on');
                } else {
                    if ($(elem).hasClass('on')) {
                        $(elem).removeClass('on');
                    }
                    $(elem).addClass('off');
                }
            });
            elem.on('click', function () {
                scope.$apply(function () {
                    if (fn) {
                        fn(scope);
                    }
                });
            });
        }
    };

}]);

SiriusViewService.directive('siriusUploadFile', ['$parse', function ($parse) {
    return {
        template: function (elem, attrs) {
            var uploadHtml = "<input type='file' id='" + attrs.uploadFileId + "' style='position: absolute;left: -9999px;'>";
            elem.append(uploadHtml);
        },
        link: function (scope, elem, attrs) {
            var img = elem.find('img');
            setTimeout(function () {
                img.on('error', function () {
                    $(this).attr('src', "dspModule/public/img/imgError.jpg");
                });
            }, 0);
            var imgLoadBind = elem.find('*[img-load]').attr('img-load');
            scope.$watch(imgLoadBind, function (newValue) {
                var imgDiv = elem.find(".upload-img-div");
                if (newValue) {
                    imgDiv.css({
                        display: 'block',
                        opacity: 1
                    });
                } else {
                    imgDiv.css({
                        display: 'none',
                        opacity: 0
                    })
                }
            });
            elem.addClass('position-relative');
            var fileInput = elem.find('input[type=file]').eq(0);
            fileInput.on('change', function (e) {
                var fn = $parse(attrs.siriusUploadFile);
                scope.$apply(function () {
                    fn(scope, {$event: e});
                })
            });
            angular.forEach(elem.find('[sirius-upload-trigger]'), function (trigger) {
                $(trigger).on('click', function () {
                    fileInput.trigger('click');
                });
            });
            var outerTrigger = $("#" + attrs.outerTrigger);
            if (outerTrigger) {
                outerTrigger.on('click', function () {
                    fileInput.trigger('click');
                })
            }

        }

    }

}]);

SiriusViewService.directive('siriusTrHasChild', ['$parse', '$compile', function ($parse, $compile) {
    return {
        link: function (scope, elem, attrs) {
            var bind = $parse(attrs.siriusTrHasChild)(scope);
            var childTrContent = $parse(attrs.childTrContent)(scope);
            if (childTrContent) {
                elem.find('td[trigger-child-td]').bind('click', function (e) {
                    var id = "Child_tr_id_" + scope.$index;
                    var childTr = $compile("<tr id='" + id + "'><td></td><td></td><td>" + childTrContent + "</td><td></td></tr>")(scope);
                    if (elem.parent().find("#" + id).length <= 0) {
                        elem.find('td').css({
                            'border-bottom': 'none'
                        });
                        elem.after(childTr);
                        $("#" + id).addClass('detail-tr');
                        $("#" + id).bind('click', function (e) {
                            this.remove();
                            scope.$apply(function () {
                                bind.detailContentOpen = !bind.detailContentOpen;
                            });
                        });
                    } else {
                        elem.find('td').css({
                            'border-bottom': '1px #eaecf0 solid'
                        });
                        $("#" + id).remove();
                    }
                    var thisElem = this;
                    scope.$apply(function () {
                        var fn = $parse(thisElem.attributes['trigger-child-td'].nodeValue);
                        fn(scope);
                        bind.detailContentOpen = !bind.detailContentOpen;
                    });

                });
            }

        }

    }
}]);

SiriusViewService.directive('siriusAlert', function () {
    return {
        link: function (scope, elem, attrs) {
            var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
            var ahtml = elem.html();
            var _dialog = function (options) {
                var ops = {
                    message: "提示内容",
                    title: "操作提示",
                    btnOk: "确定",
                    btnCancle: "取消"
                };

                $.extend(ops, options);
                var html = elem.html().replace(reg, function (node, key) {
                    return {
                        Title: ops.title,
                        Message: ops.message,
                        BtnOk: ops.btnOk,
                        BtnCancel: ops.btnCancle
                    }[key];
                });
                elem.html(html);
                elem.find('.modal-content').css({
                    top: '280px'
                });
                elem.modal({
                    width: 500,
                    backdrop: 'static'
                });
            };
            scope.alert = function (options) {
                elem.html(ahtml);
                elem.find('.ok').removeClass('btn-success').addClass('btn-primary');
                elem.find('.cancel').hide();
                _dialog(options);

                return {
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            elem.find('.ok').click(function () {
                                callback(true)
                            });
                        }
                    }
                };
            };
            scope.confirm = function (options) {
                elem.html(ahtml);
                elem.find('.ok').removeClass('btn-primary').addClass('btn-success');
                elem.find('.cancel').show();
                _dialog(options);

                return {
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            elem.find('.ok').click(function () {
                                callback(true);
                            });
                            elem.find('.cancel').click(function () {
                                callback(false);
                            });
                        }
                    }
                };
            }
        }

    }

});

SiriusViewService.directive('siriusFastSearchDate', ['$parse', '$filter', '$rootScope', function ($parse, $filter, $rootScope) {
    return {
        restrict: "AE",
        template: function (elem, attrs) {
            var searchConditionStr = attrs.siriusSearchCondition;
            elem.addClass("search-fast-date");
            var htmlStr = "<span date-type='1' class=\"{{" + searchConditionStr + ".searchDateType==1?'dsp-active':'dsp-inactive'" + "}}\">今天</span>" +
                "<span date-type='2' class=\"{{" + searchConditionStr + ".searchDateType==2?'dsp-active':'dsp-inactive'" + "}} margin-left-30\">昨天</span>" +
                "<span date-type='3' class=\"{{" + searchConditionStr + ".searchDateType==3?'dsp-active':'dsp-inactive'" + "}} margin-left-30\">本周</span>";
            return htmlStr;
        },
        link: function (scope, elem, attrs) {
            var searchCondition = $parse(attrs.siriusSearchCondition)(scope);
            var searchFun = $parse(attrs.siriusSearch);
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth();
            var date = now.getDate();
            var day = now.getDay();
            if (attrs.dateNull != undefined) {
                var searchDateObj = {
                    searchDateType: '',
                    searchStartDate: '',
                    searchEndDate: '',
                    searchDate: ''
                };
            } else {
                if (attrs.startDate) {
                    var startDate = new Date(attrs.startDate);
                }
                if (attrs.endDate) {
                    var endDate = new Date(attrs.endDate);
                }
                var searchStartDate = $filter('date')(startDate ? startDate : now, 'yyyy-MM-dd');
                var searchEndDate = $filter('date')(endDate ? endDate : now, 'yyyy-MM-dd');
                var searchDateObj = {
                    searchDateType: !attrs.startDate && !attrs.endDate ? 1 : '',
                    searchStartDate: searchStartDate,
                    searchEndDate: searchEndDate,
                    searchDate: searchStartDate + '~' + searchEndDate
                };
            }

            angular.extend(searchCondition, searchDateObj);

            elem.on('click', 'span', function () {
                searchCondition = $parse(attrs.siriusSearchCondition)(scope);
                var that = this;
                scope.$apply(function () {
                    searchDateObj.searchDateType = $(that).attr('date-type');
                    switch (searchDateObj.searchDateType) {
                        case "1":
                            searchDateObj.searchStartDate = $filter('date')(now, 'yyyy-MM-dd');
                            searchDateObj.searchEndDate = $filter('date')(now, 'yyyy-MM-dd');
                            searchDateObj.searchDate = searchDateObj.searchStartDate + '~' + searchDateObj.searchEndDate;
                            break;
                        case "2":
                            searchDateObj.searchStartDate = $filter('date')(new Date(year, month, date - 1), 'yyyy-MM-dd');
                            searchDateObj.searchEndDate = $filter('date')(new Date(year, month, date - 1), 'yyyy-MM-dd');
                            searchDateObj.searchDate = searchDateObj.searchStartDate + '~' + searchDateObj.searchEndDate;
                            break;
                        case "3":
                            if (day == 0) {
                                day = 7;
                            }
                            searchDateObj.searchStartDate = $filter('date')(new Date(year, month, date - (day - 1)), 'yyyy-MM-dd');
                            searchDateObj.searchEndDate = $filter('date')(new Date(year, month, date + (7 - day)), 'yyyy-MM-dd');
                            searchDateObj.searchDate = searchDateObj.searchStartDate + '~' + searchDateObj.searchEndDate;
                            break;
                    }
                    $rootScope.$broadcast('fastDateChange', searchDateObj.searchStartDate, searchDateObj.searchEndDate);
                    angular.extend(searchCondition, searchDateObj);
                    searchFun(scope);
                });
            });

        }

    }

}]);

SiriusViewService.directive('siriusMaterial', ['commonMethods', '$parse', function (commonMethods, $parse) {
    return {
        link: function (scope, elem, attrs) {
            var img = elem.find('img');
            setTimeout(function () {
                img.on('error', function () {
                    $(this).attr('src', "dspModule/public/img/imgError.jpg");
                });
            }, 0);
            elem.on('click', 'i', function (e) {
                var iClick = this;
                var nameStrong = $(iClick).parent().find('strong');
                nameStrong.removeClass('display-inline-block').addClass('display-none');
                var limitDiv = $(iClick).parent().find('[limit-input]');
                var nameInput = $(iClick).parent().find('input');
                limitDiv.removeClass('display-none').addClass('display-inline-block');
                nameInput.focus();
                var originValue = nameInput.val();
                nameInput.off('blur');
                nameInput.on('blur', function (e) {
                    var newValue = this.value;
                    if (!commonMethods.trim(newValue) || commonMethods.trim(newValue) == originValue) {
                        nameInput.val(originValue);
                        scope.$apply(function () {
                            nameInput.controller('ngModel').$setViewValue(originValue);
                        });
                        limitDiv.removeClass('display-inline-block').addClass('display-none');
                        nameStrong.removeClass('display-none').addClass('display-inline-block');
                        return;
                    }
                    if (newValue != originValue) {
                        scope.confirm({
                            title: '素材名称',
                            message: '确认修改吗？'
                        }).on(function (isOk) {
                            if (isOk) {
                                $parse($(iClick).attr('update-fun'))(scope);
                                //limitDiv.removeClass('display-inline-block').addClass('display-none');
                                //nameStrong.removeClass('display-none').addClass('display-inline-block');
                            } else {
                                nameInput.val(originValue);
                                scope.$apply(function () {
                                    nameInput.controller('ngModel').$setViewValue(originValue);
                                });
                                //limitDiv.removeClass('display-inline-block').addClass('display-none');
                                //nameStrong.removeClass('display-none').addClass('display-inline-block');
                            }
                        })
                    } else {
                        //limitDiv.removeClass('display-inline-block').addClass('display-none');
                        //nameStrong.removeClass('display-none').addClass('display-inline-block');
                    }
                })

            })
        }
    }
}]);

SiriusViewService.directive('siriusGeo', ['$rootScope', function ($rootScope) {
    return {
        restrict: 'AE',
        replace: true,
        template: "<div class='dsp-geo'>" +
        "<div>已选</div>" + "<div>" + "<div>" + " <span ng-repeat='province in selectedProvince'>{{province.text}}</span>" +
        "</div>" + "<div>" + "<span ng-repeat='city in selectedCity'>{{city.text}}</span>" +
        "</div>" + "</div>" + "<div class=\"{{geoAllCheck.checked?\'check\':\'un-check\'}}\" sirius-geo-check-box='checkAll()' active-bind='geoAllCheck'>全选</div>" + "<div class='dsp-geo-display' ng-repeat='(firstChar,provinceList) in geoTree'>" +
        "<label>{{firstChar}}</label>" + "<div>" + "<span class=\"{{!province.checked?\'un-check\':province.checked==1?\'check\':'section-check'}}\" " +
        "ng-repeat='province in provinceList'" + " sirius-geo-check-box='checkProvince(firstChar,$index,province)'" +
        "active-bind='province'>" + "<span>{{province.text}}</span>" + "<span class=\"{{openProvince[0]==firstChar&&openProvince[1]==$index?\'geo-collapse\':\'geo-open\'}}\"" +
        "ng-click='switchOpenProvince(firstChar,$index,$event,provinceList.length)'>" + "<span></span><span></span></span></span></div>" +
        "<div class='geo_city' ng-show='openProvince[0]==firstChar'>" + "<span class=\"{{city.checked?\'check\':\'un-check\'}}\"" +
        "sirius-geo-check-box='checkCity(openProvince[0],openProvince[1],provinceList[openProvince[1]],$index)'" +
        "active-bind='city' ng-repeat='city in provinceList[openProvince[1]].children'>" +
        "{{city.text}}</span></div></div>" +
        "<div class='geo-footer'><div><div class='btn btn-default' ng-click='cancelGeo()'>取消</div></div><div><div class='btn btn-success' ng-click='submitGeo()'>提交</div></div></div></div>",
        scope: true,
        link: function (scope, elem, attrs) {
        },
        controller: ['$scope', 'dspDaoService', 'commonMethods', function ($scope, dspDaoService, commonMethods) {
            $scope.checkAll = function () {
                for (var i in $scope.geoTree) {
                    angular.forEach($scope.geoTree[i], function (province, pIndex) {
                        province.checked = $scope.geoAllCheck.checked;
                        $scope.selectedProvince[i + pIndex] = {
                            id: province.id,
                            text: province.text
                        };
                        angular.forEach(province.children, function (city) {
                            city.checked = $scope.geoAllCheck.checked;
                        });
                    });
                }
                if (!$scope.geoAllCheck.checked) {
                    $scope.selectedProvince = {};
                }
                $scope.selectedCity = {};
            };

            var initGeo = function () {
                $scope.geoAllCheck = {checked: false};
                $scope.geoTree = {};
                $scope.selectedProvince = {};
                $scope.selectedCity = {};
                $scope.openProvince = null;
                angular.forEach($scope.provinceList, function (province) {
                    province.checked = 0;
                    angular.forEach(province.children, function (city) {
                        city.checked = 0;

                    });
                });
                if (!geoLoadedFlag) {
                    $scope.provinceList = {};
                    getGeoList();
                } else {
                    geo($scope.provinceList);
                }
            };
            $scope.$watch('caller.name', function (newValue, oldValue) {
                if (newValue != oldValue) {
                    initGeo();
                }
            });
            var geoLoadedFlag = false;
            $scope.$on('openGeo', function (e, caller) {
                $scope.caller = caller;
            });
            var getGeoList = function () {
                dspDaoService.getGeo({
                    geoUrl: "/dsp/admin/adcenter/getAdWorldArea.action",
                    scope: $scope,
                    data: {},
                    successFun: function (msg) {
                        $scope.provinceList = msg.data[0].children;
                        geoLoadedFlag = true;
                        geo($scope.provinceList);
                    },
                    errorFun: function (msg) {
                        $scope.showMessageTip(msg, '', 'error');
                    }
                });
            };
            var geo = function (provinceList) {
                provinceList = provinceList.sort(function (obj1, obj2) {
                    return obj1.text.localeCompare(obj2.text);
                });
                if (commonMethods.isEmptyObj($scope.geoTree)) {
                    angular.forEach(provinceList, function (item) {
                        var firstChar = pinyin.getCamelChars(item.text)[0];
                        if (!$scope.geoTree[firstChar]) {
                            $scope.geoTree[firstChar] = [];
                        }
                        $scope.geoTree[firstChar].push(item);
                    });
                }
                if ($scope.caller && $scope.caller.oriAreaIds) {
                    setSelectedArea($scope.caller.oriAreaIds);
                }
            };
            var setSelectedArea = function (oriAreaIds) {
                $scope.selectedProvince = {};
                $scope.selectedCity = {};
                if (!oriAreaIds) {
                    return;
                }
                var areaIds = oriAreaIds.split(',');
                if (!$scope.geoTree) {
                    $scope.geoTree = {};
                    geo($scope.getSessionStorage('provinceList'));
                }
                for (var i in $scope.geoTree) {
                    angular.forEach($scope.geoTree[i], function (province, index) {
                        angular.forEach(areaIds, function (areaId) {
                            if (province.id == areaId) {
                                province.checked = 1;
                                angular.forEach(province.children, function (city) {
                                    city.checked = true;
                                });
                                if ($scope.selectedProvince[i + index] == undefined) {
                                    $scope.selectedProvince[i + index] = {};
                                }
                                $scope.selectedProvince[i + index].id = province.id;
                                $scope.selectedProvince[i + index].text = province.text;
                                return false;
                            }
                        });
                        angular.forEach(province.children, function (city, cityIndex) {
                            angular.forEach(areaIds, function (areaId) {
                                if (city.id == areaId) {
                                    if ($scope.selectedProvince[i + index] != undefined) {
                                        return true;
                                    }
                                    province.checked = 2;
                                    city.checked = true;
                                    if ($scope.selectedCity[i + index + cityIndex] == undefined) {
                                        $scope.selectedCity[i + index + cityIndex] = {};
                                    }
                                    $scope.selectedCity[i + index + cityIndex].id = city.id;
                                    $scope.selectedCity[i + index + cityIndex].text = city.text;
                                    return false;
                                }
                            })
                        })
                    });
                }
            };

            $scope.$on('changeSelectedAreas', function (e, oriAreaIds) {
                setSelectedArea(oriAreaIds);
                $scope.updateSessionStorage('selectedProvince', $scope.selectedProvince);
                $scope.updateSessionStorage('selectedCity', $scope.selectedCity);
                $rootScope.$broadcast('updateSelectedAreas');
            });

            $scope.checkProvince = function (rows, cols, province) {
                if ($scope.selectedProvince[rows + cols] == undefined) {
                    $scope.selectedProvince[rows + cols] = {};
                }
                angular.forEach($scope.geoTree[rows][cols].children, function (city) {
                    city.checked = province.checked;
                });
                if (province.checked) {
                    $scope.selectedProvince[rows + cols].id = province.id;
                    $scope.selectedProvince[rows + cols].text = province.text;
                } else {
                    delete $scope.selectedProvince[rows + cols];
                }
                for (var i in $scope.selectedCity) {
                    if (i.startsWith(rows + cols)) {
                        delete $scope.selectedCity[i];
                    }
                }
            };
            $scope.checkCity = function (rows, cols, province, cityIndex) {
                var allCityChecked = true;
                var allCityUnChecked = true;
                angular.forEach($scope.geoTree[rows][cols].children, function (city) {
                    if (city.checked) {
                        allCityUnChecked = false;
                    } else {
                        allCityChecked = false;
                    }
                });
                if (allCityChecked) {
                    province.checked = 1;
                    if ($scope.selectedProvince[rows + cols] == undefined) {
                        $scope.selectedProvince[rows + cols] = {};
                    }
                    $scope.selectedProvince[rows + cols].id = province.id;
                    $scope.selectedProvince[rows + cols].text = province.text;
                    for (var i in $scope.selectedCity) {
                        if (i.startsWith(rows + cols)) {
                            delete $scope.selectedCity[i];
                        }
                    }
                    return;

                }
                if (allCityUnChecked) {
                    province.checked = 0;
                    delete $scope.selectedCity[rows + cols + cityIndex];
                    for (var i in $scope.selectedProvince) {
                        if (i == (rows + cols)) {
                            delete $scope.selectedProvince[i];
                        }
                    }
                }
                if (!allCityChecked && !allCityUnChecked) {
                    province.checked = 2;
                    for (var i in $scope.selectedProvince) {
                        if (i == (rows + cols)) {
                            delete $scope.selectedProvince[i];
                        }
                    }
                    angular.forEach(province.children, function (city, index) {
                        if ($scope.selectedCity[rows + cols + index] == undefined) {
                            $scope.selectedCity[rows + cols + index] = {};
                        }
                        if (city.checked) {
                            $scope.selectedCity[rows + cols + index].id = city.id;
                            $scope.selectedCity[rows + cols + index].text = city.text;
                        } else {
                            delete $scope.selectedCity[rows + cols + index];
                        }
                    });
                    return;
                }
            };
            $scope.switchOpenProvince = function (rows, cols, e, totalCols) {
                if (rows + cols == $scope.openProvince) {
                    $scope.openProvince = null;
                } else {
                    $scope.openProvince = rows + cols;
                    if (cols <= 3) {
                        $(".geo_city").css({
                            'padding-left': 107 + cols * 105
                        });
                    }
                    if (cols > 3) {
                        $(".geo_city").css({
                            'padding-left': 107 + (cols - 4) * 105
                        });
                    }
                }
                e.stopPropagation();//阻止事件冒泡
            };
            $scope.cancelGeo = function () {
                $('.dsp-geo').css({
                    'display': 'none'
                });
            };
            $scope.submitGeo = function () {
                var oriAreaIds = '';
                angular.forEach($scope.provinceList, function (province) {
                    if (province.checked == 1) {
                        oriAreaIds += province.id + ',';
                    }
                    angular.forEach(province.children, function (city) {
                        if (city.checked) {
                            oriAreaIds += city.id + ',';
                        }
                    });

                });
                $scope.updateSessionStorage('selectedProvince', $scope.selectedProvince);
                $scope.updateSessionStorage('selectedCity', $scope.selectedCity);
                oriAreaIds = oriAreaIds.substring(0, oriAreaIds.length - 1);
                $rootScope.$broadcast('submitGeoSelected', oriAreaIds);
                $('.dsp-geo').css({
                    'display': 'none'
                });
            };

        }]
    }
}]);

SiriusViewService.directive('siriusToolTip', ['$parse', '$timeout', function ($parse, $timeout) {
    return {
        link: function (scope, elem, attrs) {
            $timeout(function () {
                if ($parse(attrs.siriusToolTip)(scope) != false) {
                    elem.tooltip({html: true});
                }
            })

        }
    }
}]);

//ng-repeat不能先解析name值问题
SiriusViewService.directive("siriusDyName", function () {
    return {
        require: "ngModel",
        link: function (scope, elm, iAttrs, ngModelCtr) {
            ngModelCtr.$name = iAttrs.siriusDyName + '_' + scope.$index;
            iAttrs.name = iAttrs.siriusDyName + '_' + scope.$index;
            $(elm)[0].name = iAttrs.siriusDyName + '_' + scope.$index;
            var formController = elm.controller('form') || {
                    $addControl: angular.noop
                };
            formController.$addControl(ngModelCtr);

            scope.$on('$destroy', function () {
                formController.$removeControl(ngModelCtr);
            });
        }
    };
});

SiriusViewService.directive('siriusFilterSelect', ['$timeout', function ($timeout) {
    return {
        link: function (scope, elm, iAttrs) {
            $.fn.selectpicker.defaults = {
                noneSelectedText: '没有选中任何项',
                noneResultsText: '没有找到匹配项',
                countSelectedText: '选中{1}中的{0}项',
                maxOptionsText: ['超出限制 (最多选择{n}项)', '组选择超出限制(最多选择{n}组)'],
                multipleSeparator: ', '
            };
            $timeout(function () {
                elm.selectpicker({
                    noneSelectedText: '不限'
                });
            });

            scope.$on('selectRefresh', function (e,value) {
                $timeout(function () {
                    elm.selectpicker('val', value);
                    elm.selectpicker('refresh');
                });
            });

        }
    };
}]);

SiriusViewService.directive("siriusFilterSelectNp", ['$parse', '$timeout', function ($parse, $timeout) {
    return {
        link: function (scope, elm, iAttrs) {
            $.fn.selectpicker.defaults = {
                noneSelectedText: '没有选中任何项',
                noneResultsText: '没有找到匹配项',
                countSelectedText: '选中{1}中的{0}项',
                maxOptionsText: ['超出限制 (最多选择{n}项)', '组选择超出限制(最多选择{n}组)'],
                multipleSeparator: ', '
            };
            if (scope.$last === true) {
                $timeout(function () {
                    elm.parent('select').selectpicker('val', iAttrs.siriusFilterSelectNp.split(','));
                    elm.parent('select').selectpicker('refresh');
                });
            }

        }
    };
}]);

SiriusViewService.directive("siriusMessageTip", function () {
    return {
        restrict: 'AE',
        template: "<div class='message-tip' ng-bind='messageTip'></div>",
        replace: true,
        link: function (scope, elem) {
            scope.showMessageTip = function (msg, callbackFun, type, time) {
                if (!time) {
                    time = 1500;
                }
                if (type == 'error') {
                    elem.css({
                        'background': '#ff805e'
                    });
                } else if (type == 'success') {
                    elem.css({
                        'background': '#ff805e',
                        /*'background': '-moz-linear-gradient(top, #70c40f 0%, #87ce18 100%)',
                        'background': '-webkit-linear-gradient(top, #70c40f 0%, #87ce18 100%)',
                        'background': 'linear-gradient(to bottom, #70c40f 0%, #87ce18 100%)'*/
                    });
                } else {
                    elem.css({
                        'background': 'rgba(0, 0, 0, 0.6)'
                    });
                }
                scope.messageTip = msg;
                elem.css({
                    'display': 'block',
                    'opacity': '1'
                });
                setTimeout(function () {
                    elem.css({
                        'opacity': 0
                    });
                    setTimeout(function () {
                        elem.css({
                            'display': 'none'
                        });
                        if (callbackFun && typeof  callbackFun == "function") {
                            callbackFun();
                        }
                    }, 500);
                }, time);
            };
        }
    }
});
//事件委托
SiriusViewService.directive('siriusDelegate', ['$parse', function ($parse) {
    return {
        link: function (scope, elem, attrs) {
            var delegateEvent = attrs.delegateEvent;
            var delegateElem = attrs.delegateElem;
            elem.on(delegateEvent, delegateElem, function (e) {
                var fn = $parse(attrs.siriusDelegate);
                var targetElem = $(this);
                scope.$apply(function () {
                    fn(scope, {index: targetElem.scope().$index});
                });
            })
        }
    }
}]);


SiriusViewService.directive('siriusImgSliderNp', ['$timeout',function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var elemId = attrs.siriusImgSliderNp;
            var parentScope = scope.$parent;
            /*parentScope['swiper_'+elemId] = null;*/
            if (scope.$last === true) {
                $timeout(function() {
                    if(parentScope['swiper_'+elemId]){
                        parentScope['swiper_'+elemId].destroy(true, true);
                    }
                    parentScope['swiper_'+elemId] = new Swiper('#'+elemId,{
                        //loop : true,
                        //speed:500,
                        pagination : '#'+elemId + ' .swiper-pagination',
                        nextButton: '#'+elemId +  ' .swiper-button-next',
                        prevButton: '#'+elemId +  ' .swiper-button-prev',
                        //autoplayDisableOnInteraction : false,
                    });
                });
            }
        }
    };
}]);