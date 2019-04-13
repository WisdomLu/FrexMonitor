/**
 * Created by Administrator on 2016/4/1.
 */
var SiriusCommonService = angular.module('Sirius.Common', []);

SiriusCommonService.service('commonMethods', ['$rootScope', '$filter', 'dspDaoService',function ($rootScope, $filter, dspDaoService) {
    return {
        nullStr: function (str) {
            if (str == null || str == undefined) {
                return '';
            } else {
                return str;
            }
        },
        checkStrToObj: function (str, options, targetObj) {
            var obj = {};
            if (str) {
                var _arr = str.split(',');
                angular.forEach(_arr, function (_str) {
                    obj[_str] = true;
                });
                angular.forEach(options, function (option) {
                    if (obj[option] == undefined) {
                        obj[option] = false;
                    }
                });
            } else {
                if (options) {
                    angular.forEach(options, function (option) {
                        if (option == 0) {
                            obj[option] = true;
                        } else {
                            obj[option] = false;
                        }
                    });
                }
            }
            angular.extend(targetObj, obj);
        },
        objToCheckStr: function (obj) {
            if (obj) {
                var str = '';
                for (var i in obj) {
                    if (obj[i]) {
                        str += i + ',';
                    }
                }
                str = this.replaceEndComma(str, '');
                return str;
            } else {
                return '';
            }
        },
        isEmptyObj: function (obj) {
            for (var i in obj) {
                return false;
            }
            return true;
        },
        replaceEndComma: function (str, replaceStr) {
            if (!str) {
                return '';
            } else {
                return str.replace(/,$/gi, replaceStr)
            }
        },
        trim: function (str) {
            if (!str) {
                return '';
            } else {
                return str.replace(/(^\s*)|(\s*$)/g, "");
            }
        },
        getPutTimeStrValue: function (putTimeList) {
            var strValue = "";
            var that = this;
            angular.forEach(putTimeList, function (putTime) {
                var dateStr = putTime.dateStr;
                var str = dateStr + "#" + $filter('time')(putTime.timeStrMin) + "~" + $filter('time')(putTime.timeStrMax);
                strValue += that.trim(str) + '##';
            });
            if (strValue.length > 0) {
                strValue = strValue.substring(0, strValue.length - 2);
            }
            return strValue;
        },
        isValidIdNo: function (idCard) {
            var that = this;
            var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];    // 加权因子
            var ValideCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2];            // 身份证验证位值.10代表X
            function IdCardValidate(idCard) {
                if (!idCard) {
                    return false;
                }
                idCard = that.trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格
                if (idCard.length == 15) {
                    return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证
                } else if (idCard.length == 18) {
                    var a_idCard = idCard.split("");                // 得到身份证数组
                    if (isValidityBrithBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard)) {   //进行18位身份证的基本验证和第18位的验证
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            return IdCardValidate(idCard);

            /**
             * 判断身份证号码为18位时最后的验证位是否正确
             * @param a_idCard 身份证号码数组
             * @return
             */
            function isTrueValidateCodeBy18IdCard(a_idCard) {
                var sum = 0;                             // 声明加权求和变量
                if (a_idCard[17].toLowerCase() == 'x') {
                    a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作
                }
                for (var i = 0; i < 17; i++) {
                    sum += Wi[i] * a_idCard[i];            // 加权求和
                }
                valCodePosition = sum % 11;                // 得到验证码所位置
                if (a_idCard[17] == ValideCode[valCodePosition]) {
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * 验证18位数身份证号码中的生日是否是有效生日
             * @param idCard 18位书身份证字符串
             * @return
             */
            function isValidityBrithBy18IdCard(idCard18) {
                var year = idCard18.substring(6, 10);
                var month = idCard18.substring(10, 12);
                var day = idCard18.substring(12, 14);
                var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
                // 这里用getFullYear()获取年份，避免千年虫问题
                if (temp_date.getFullYear() != parseFloat(year)
                    || temp_date.getMonth() != parseFloat(month) - 1
                    || temp_date.getDate() != parseFloat(day)) {
                    return false;
                } else {
                    return true;
                }
            }

            /**
             * 验证15位数身份证号码中的生日是否是有效生日
             * @param idCard15 15位书身份证字符串
             * @return
             */
            function isValidityBrithBy15IdCard(idCard15) {
                var year = idCard15.substring(6, 8);
                var month = idCard15.substring(8, 10);
                var day = idCard15.substring(10, 12);
                var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
                // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
                if (temp_date.getYear() != parseFloat(year)
                    || temp_date.getMonth() != parseFloat(month) - 1
                    || temp_date.getDate() != parseFloat(day)) {
                    return false;
                } else {
                    return true;
                }
            }

        },
        errorAlert: function (scope,msg, callbackFun) {
            scope.$emit('ajaxError',msg,callbackFun);
        },
        /**
         * 1.全国组织机构代码由八位数字（或大写拉丁字母）本体代码和一位数字（或大写拉丁字母）校验码组成。
         *
         *本体代码采用系列（即分区段）顺序编码方法。校验码按照以下公式计算：
         *C9=11-MOD(∑Ci(i=1→8)×Wi,11)
         *式中： MOD——代表求余函数；
         *i——代表代码字符从左至右位置序号；
         *Ci——代表第i位上的代码字符的值（具体代码字符见附表）；
         *C9——代表校验码；
         *Wi——代表第i位上的加权因子；
         *当C9的值为10时，校验码应用大写的拉丁字母X表示；当C9的值为11时校验码用0表示。
         *
         *2.代码的表示形式
         *为便于人工识别，应使用一个连字符“—”分隔本体代码与校验码。机读时，连字符省略。表示形式为：
         *xxxxxxxx—X
         *
         *3.自定义区
         *为满足各系统管理上的特殊需要，规定本体代码PDY00001至PDY99999为自定义区，供各系统编制内部组织机构代码使用。自定义区内编制的组织机构代码不作为个系统之间信息交换的依据。
         */
        isValidOrgCode: function (orgCode) {
            //机构代码
            var ws = [3, 7, 9, 10, 5, 8, 4, 2];
            var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
            var reg = /^([0-9A-Z]){8}-[0-9|X]$/;
            var sum = 0;
            for (var i = 0; i < 8; i++) {
                sum += str.indexOf(orgCode.charAt(i)) * ws[i];
            }
            var c9 = 11 - (sum % 11);
            c9 = c9 == 10 ? 'X' : c9;
            if (!reg.test(orgCode) || c9 == orgCode.charAt(9)) {
                return false;
            }
            return true;
        },
        setSelectedProvinceAndCity: function (areaIds) {
            if ($rootScope.getSessionStorage('provinceList')) {
                $rootScope.$broadcast('changeSelectedAreas', areaIds);
            } else {
                dspDaoService.getGeo({
                    geoUrl: "/dsp/admin/adcenter/getAdWorldArea.action",
                    scope: $rootScope,
                    data: {},
                    successFun: function (msg) {
                        var provinceList = msg.data[0].children;
                        $rootScope.updateSessionStorage('provinceList', provinceList);
                        $rootScope.$broadcast('changeSelectedAreas', areaIds);
                        return;
                    },
                    errorFun: function (msg) {
                        $rootScope.showMessageTip(msg);
                    }
                });
            }
        },
        getDateModel: function (putTime) {
            var dateArray = putTime.dateStr.split('~');
            var dateModel = {
                dateMin: new Date(dateArray[0]),
                dateMax: new Date(dateArray[1]),
                timeMin: putTime.timeStrMin,
                timeMax: putTime.timeStrMax
            };
            return dateModel;
        }
    }
}]);