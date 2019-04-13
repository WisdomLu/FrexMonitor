/**
 * Created by Administrator on 2016/3/28.
 */

var SiriusFilterService = angular.module('Sirius.Filter', []);


SiriusFilterService.filter('ageSectionType', ['ageSectionType',function (ageSectionType) {
    return function (input) {
        return ageSectionType[input];
    }
}]).filter('putRegion', ['putRegion',function (putRegion) {
    return function (input) {
        return putRegion[input];
    }
}]).filter('gender', ['gender',function (gender) {
    return function (input) {
        return gender[input];
    }
}]).filter('marryStatus', ['marryStatus',function (marryStatus) {
    return function (input) {
        return marryStatus[input];
    }
}]).filter('isHaveChild', ['isHaveChild',function (isHaveChild) {
    return function (input) {
        return isHaveChild[input];
    }
}]).filter('eduBack', ['eduBack',function (eduBack) {
    return function (input) {
        if (input == -1) {
            return "";
        }
        return eduBack[input];
    }
}]).filter('consumeLevel', ['consumeLevel',function (consumeLevel) {
    return function (input) {
        return consumeLevel[input];
    }
}]).filter('appAction', ['appAction',function (appAction) {
    return function (input) {
        return appAction[input];
    }
}]).filter('devOS', ['devOS',function (devOS) {
    return function (input) {
        return devOS[input];
    }
}]).filter('dno', ['dno',function (dno) {
    return function (input) {
        return dno[input];
    }
}]).filter('netEnv', ['netEnv',function (netEnv) {
    return function (input) {
        return netEnv[input];
    }
}]).filter('brandId', ['brandId',function (brandId) {
    return function (input) {
        return brandId[input];
    }
}]).filter('devPriceLev', ['devPriceLev',function (devPriceLev) {
    return function (input) {
        if (input == -1) {
            return "";
        }
        return devPriceLev[input];
    }
}]).filter('payModel', ['payModel',function (payModel) {
    return function (input) {
        return payModel[input];
    }
}]).filter('payModelDesc', ['payModelDesc',function (payModelDesc) {
    return function (input) {
        return payModelDesc[input];
    }
}]).filter('adType', ['adType',function (adType) {
    return function (input) {
        return adType[input];
    }
}]).filter('adPoseType', ['adPoseType',function (adPoseType) {
    return function (input) {
        return adPoseType[input];
    }
}]).filter('adPoseTypeDesc', ['adPoseTypeDesc',function (adPoseTypeDesc) {
    return function (input) {
        return adPoseTypeDesc[input];
    }
}]).filter('adOrientationSelSwitch', function () {
    return function (input) {
        switch (input) {
            case false:
                return "显示筛选";
                break;
            case true:
                return "收起筛选";
                break;
        }
    }
}).filter('wordPrompt', function () {
    return function (input, param1) {
        return input + "/" + param1;
    }
}).filter('time', function () {
    return function (input) {
        if (input == 24) {
            return "23:59";
        } else {
            if (input < 10) {
                input = "0" + "" + parseInt(input);
            }
            return input + ":00";
        }
    }
}).filter('timeStrToClock', function () {
    return function (input) {
        if (input.startsWith("23:59")) {
            return 24;
        } else {
            return parseInt(input.substr(0, 2));
        }
    }
}).filter('saveOrNo', ['saveOrNo',function (saveOrNo) {
    return function (input) {
        return saveOrNo[input];
    }
}]).filter('messageType', ['messageType',function (messageType) {
    return function (input) {
        return messageType[input];
    }
}]).filter('isUnRead', ['isUnRead',function (isUnRead) {
    return function (input) {
        return isUnRead[input];
    }
}]).filter('userType', ['userType',function (userType) {
    return function (input) {
        return userType[input];
    }
}]).filter('approveStatus', ['approveStatus',function (approveStatus) {
    return function (input) {
        return approveStatus[input];
    }
}]).filter('platType', ['platType',function (platType) {
    return function (input) {
        return platType[input];
    }
}]).filter('adCheckStatus', ['adCheckStatus',function (adCheckStatus) {
    return function (input) {
        return adCheckStatus[input];
    }
}]).filter('planExcStatus', ['planExcStatus',function (planExcStatus) {
    return function (input) {
        return planExcStatus[input];
    }
}]).filter('threshold', ['$filter',function ($filter) {
    return function (input) {
        if (isNaN(input)) {
            return input;
        } else {
            return $filter("currency")(input,'￥');
        }
    }
}]).filter('limit', ['$filter',function ($filter) {
    return function (input) {
        if (isNaN(input)) {
            return input;
        } else {
            return $filter("number")(input,'0');
        }
    }
}]);



