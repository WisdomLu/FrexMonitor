/**
 * Created by Administrator on 2016/3/22.
 */
var SiriusConstantsFactory = angular.module('Sirius.Constants', []);

SiriusConstantsFactory.constant('thresholdType', {"0": "不限", "1": "限制"});
SiriusConstantsFactory.constant('payModel', {"1": "CPC", "2": "CPM"});
SiriusConstantsFactory.constant('payModelDesc', {"1": "点击", "2": "千次曝光"});
SiriusConstantsFactory.constant('adType', {"1": "链接广告", "2": "应用广告", "6": "原生广告", "7": "视频广告"});
SiriusConstantsFactory.constant('adPoseType', {"1": "横幅广告", "2": "开屏广告", "3": "插屏广告", "4": "信息流广告", "5": "文字链广告"});
SiriusConstantsFactory.constant('adPoseTypeDesc', {
    "1": "主要为轮播大图、顶部Banner、底部Banner等广告位。",
    "2": "用户启动应用时，紧接着启动画面出现的纯图推广样式。适合品牌推广展示，推广显示时长。",
    "3": "用户进到APP的某个页面后，弹出的一个窗口，展示纯图推广内容",
    "4": "以图文样式展示推广内容，主要出现在手机终端桌面负一屏。",
    "5": "一行不可轮播的文字，点击后会进入一个landing page，可以用于推广app或网站。"
});
SiriusConstantsFactory.constant('ageSectionType', {"0": "不限", "1": "年龄段"});
SiriusConstantsFactory.constant('putRegion', {"0": "不限", "1": "省市"});
SiriusConstantsFactory.constant('gender', {"0": "不限", "1": "男", "2": "女"});
SiriusConstantsFactory.constant('marryStatus', {"0": "不限", "1": "已婚", "2": "未婚"});
SiriusConstantsFactory.constant('isHaveChild', {"0": "不限", "1": "已育", "2": "未育"});
SiriusConstantsFactory.constant('eduBack', {
    "0": "不限 ",
    "1": "博士",
    "2": "硕士",
    "3": "本科",
    "4": "大专",
    "5": "高中",
    "6": "初中",
    "7": "小学",
    "8": "其他"
});
SiriusConstantsFactory.constant('consumeLevel', {"0": "不限", "1": "高", "2": "中", "3": "低"});
SiriusConstantsFactory.constant('appAction', {"0": "不限 ", "1": "已安装", "2": "未安装"});
SiriusConstantsFactory.constant('devOS', {
    "0": "不限 ",
    "1": "IOS",
    "2": "Android",
    "3": "windows",
    "4": "Android wear",
    "5": "tencent os",
    "6": "yun os for wear"
});
SiriusConstantsFactory.constant('devType', {"1": "移动设备 ", "2": "pc设备", "3": "tv", "0": "其他"});
SiriusConstantsFactory.constant('dno', {"0": "不限", "1": "移动", "2": "联通", "3": "电信"});
SiriusConstantsFactory.constant('netEnv', {"0": "不限", "1": "wifi", "2": "4g", "3": "3g", "4": "2g"});
SiriusConstantsFactory.constant('brandId', {
    "0": "不限",
    "1": "三星",
    "2": "华为",
    "3": "小米/红米",
    "4": "VIVO",
    "5": "OPPO",
    "6": "联想",
    "7": "酷派/ivvi",
    "8": "乐视",
    "9": "金立",
    "10": "魅族",
    "11": "中兴",
    "12": "努比亚",
    "13": "奇酷360/大神",
    "14": "其他"
});
SiriusConstantsFactory.constant('devPriceLev', {
    "0": "不限",
    "1": "0-999",
    "2": "1000-1999",
    "3": "2000-3499",
    "4": "3500-5000",
    "5": "5000以上"
});
SiriusConstantsFactory.constant('fastSearchDate', {"1": "今天", "2": "昨天", "3": "本周"});
SiriusConstantsFactory.constant('interestSel', {"0": "不限", "1": "选择"});
SiriusConstantsFactory.constant('saveOrNo', {"0": "不保存", "1": "保存"});
SiriusConstantsFactory.constant('messageType', {"0": "全部", "1": "系统消息", "2": "广告业务", "3": "其它消息"});
SiriusConstantsFactory.constant('isUnRead', {"0": "已读", "1": "未读"});
SiriusConstantsFactory.constant('userType', {"1": "企业", "2": "个人"});
SiriusConstantsFactory.constant('dataTableDom', 't<\'row\'<\'col-xs-12 col-sm-6 col-md-6\'i><\'col-xs-12 col-sm-6 col-md-6\'p <\' float-right\'l>>>');
SiriusConstantsFactory.constant('approveStatus', {"1": "待审核", "2": "通过审核", "3": "未通过审核"});
SiriusConstantsFactory.constant('platType', {"1": "Android", "2": "IOS"});
SiriusConstantsFactory.constant('adCheckStatus', {"3": "上线", "4": "下线"});
SiriusConstantsFactory.constant('planExcStatus', {"0": "下线", "1": "上线"});
SiriusConstantsFactory.constant('appVersion', 1.4);