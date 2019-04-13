include "Common.thrift"
namespace java com.ocean.proxy.thrift.entity

// 广告服务器和广告发布系统接口
enum AdPubRespCode {
    RC_SUCCESS      = 0,     // 成功
    RC_NO_CONNECT   = 1,     // 数据存储服务未链接
    RC_DATAERROR    = 6,     // 未找到修改数据的索引
    RC_PARAM_ERROR  = 2,     // 参数错误
    RC_UNKOWN_ERROR = 3,     // 未知错误
}

enum AdPubAction {
    //1-待审核  2-已审核通过待上线  3-已上线  4-已下线  5-审核不通过 (整型)
    EN_AD_ERROR_ST      = 0,
    EN_AD_WAIT_CHECK    = 1,
    EN_AD_WAIT_ONLINE   = 2,
    EN_AD_ONLINE        = 3,
    EN_AD_PUTDOWN       = 4,
    EN_AD_NOPASS        = 5,
}

//1.底部按钮 2.相关推荐 3.底部banner 4.顶部banner 5.文字链广告位 6.评论区广告
enum AdPos {
    EN_Button   = 1,
    EN_Recom,
    EN_Banner,
    EN_TopBanner,
    EN_WordsLink,
    EN_Commet,
    EN_PosLen,
}

struct AdStatus {
    1:  required i64 adId;          // 广告id
    2:  required i64 status;        // 广告状态或者排名
}

struct TimeIndex {
    1:  required i32 first;
    2:  required i64 sec;
}
struct DynamicEffect {
	1: required i32 id;
	2: required string url;
}
// 总排名的计算得分参数
struct AdRankingInfo {
    1: required i64 adId,
    2: required i64 score,
    3: required i64 clickCount,     //
    4: required i64 viewCount,      //
    5: required i32 recommendType,
    6: required i32 adWeight,
    7: optional i32 matchType;      // 低位到高位依次是, 单独投放,关键字匹配,资讯内容源内容分类匹配
    8: optional TimeIndex tmInd;
}

const string DB_ADRANK = "adRank"   // singleton

struct AdRank {
    1: required map<i64, AdRankingInfo> adrank,      // 上架待过滤待排序广告
    2: required list<AdRankingInfo> adTotalRank,    // Ad Ranking 有效广告的集合
}

struct LimitTimeRangeSec {
    1: required i64 startTime;                  // 广告的开始曝光时间 值为从1970.1.1到指定时间的秒数
    2: required i64 endTime;                    // 广告的结束曝光时间 值为从1970.1.1到指定时间的秒数
    3: optional bool isRepeat = false; // 时间段内是否每天重复出现
}

struct AdTimeRanageDynamic {
    1: optional LimitTimeRangeSec time_range;   //时间段
    2: optional i32 pv_number=0; 
    3: optional i32 click_number=0;
}

const string DB_IDDYNA= "adDyid_"          // more than one      adid -> Key

struct AdUserDynamic {
    1: required i32 clickNum=0;            //总点击数
    2: required i32 viewNum=0;             //总阅读数
    3: optional map <i32, i32> rangeView;  //!!!废弃
    4: optional map <i32, i32> rangeClick; //!!!废弃
    5: optional i32 adid;
    6: optional list<AdTimeRanageDynamic> timeRangeDynamic;
}

const string DB_IDLIST = "adIdList"     // singleton -> AdRank
struct AdIdlist {
    1: required list<i64> adlist,  // 内部与广告系统redis传输 所有广告id
}
const string AD_FLUSHTS = "ad_flush_time_stamp"
struct AdFlushTimeStamp {
	1: required i64 time_stamp; //timestamp for saving adlist to redis
}

enum AdSpaceType {
	BANNER = 1,           //横幅广告(包括banner)
	OPENING = 2,          //开屏广告
    INTERSTITIAL = 3,     //插屏广告
    INFOFLOW = 4,         //信息流广告
    TEXTLINK = 5,         //文字链广告
}

enum SettlementType {
	CPM = 0, //cpm, cost per impression
	CPC = 1, //cpc, cost per click
	CPI = 2, //cpi, cost per install
	CPA = 3, //cpa, cost per action  	
}

struct Position {
    1: optional i32 putType;       // 投放方式（1.单独投放,2.匹配投放）
    // 当投放方式为单独投放,广告位类型值对应位置 1.普通展示 2.通栏展示 3.全屏展示 4.置顶通栏
    // 当投放方式为匹配投放,广告位类型值对应位置 1.底部按钮 2.相关推荐 3.底部banner 4.顶部banner 5.文字链广告位 6.评论区广告
    // 务必一起更新AdPos
    2: optional i32 positionType;  // 广告位类型
    
    // 如果按广告位组织，则为SpaceType  
    3: optional AdSpaceType spaceType;
}

struct AdImg {
    1: optional string formate;     // 图片格式
    2: optional i32 height;         // 高
    3: optional i32 width;          // 宽
    4: optional string src;         // 源图片url
    5: optional string alt;         // 图片位置
    6: optional string ref;         // <IMG>标识
}

struct AdVid {
    1: optional string src;         // video url
    2: optional i32 width;          // 宽
    3: optional i32 height;         // 高
	4: optional string format;     // 格式: .mp4, .swf
    5: optional i32 duration;		// in seconds
    6: optional string img_src;     // capture of the video
}

struct LimitTimeRange {
    1: required i32 startTime;      // 广告的开始曝光时间 值0到23
    2: required i32 endTime;        // 广告的结束曝光时间 值为0到23
}

struct Date {
    1: required i32 year;
    2: required i32 month;
    3: required i32 day;
}

// 注意! 已放弃使用
struct DateClickViewLimit   {
    1:  required Date startDate;
    2:  required Date endDate;
    3:  optional i32 limitCond;               // 限制条件 1.pv 2.click 3.不限 4.pv,click
    4:  optional i32 limitNum;                // 限制PV次数, 当限制条件为1或者4时, 规定0为非法参数 
    5:  optional i32 limitClickNum;           // 限制次点击数, 当限制条件为2或4时, 规定0为非法参数 
    6:  optional LimitTimeRange timeRange;    // 广告投放时间段 
    7:  optional i32 TurnPlayType;            // 轮播类型：0.不轮播  1. PV限制的轮播    2.点击限制的轮播 3.同时限制PV和点击 
    8:  optional i32 TurnLimit;               // 轮播PV限制数, 如果TurnPlayType值为1或者3, 规定0为非法参数 
    9:  optional i32 TurnClickLimit;          // 轮播点击限制数, 如果TurnPlayType值为2或者3,规定0为非法参数 
    10: optional list<LimitTimeRangeSec> tm;  // 注意! 已放弃使用,最终“翻译”后的时间
}

struct DateClickViewLimitV2 {
    1:  required list<LimitTimeRangeSec> tm;  // 广告的开始曝光时间 值为从1970.1.1到指定时间的秒数 格林威治
    2:  required i32 limitCond;               // 限制条件 1.曝光 2.点击 3.不限 4.同时限定PV和点击 
    3:  optional i32 limitNum;                // 限制PV次数, 当限制条件为1或者4时,规定0为非法参数 
    4:  optional i32 limitClickNum;           // 限制次点击数,当限制条件为2或4时,规定0为非法参数 
    5:  required i32 TurnPlayType;            // 轮播类型：0.不轮播  1. PV限制的轮播    2.点击限制的轮播 3.同时限制PV和点击 
    6:  optional i32 TurnLimit;               // 轮播PV限制数, 如果TurnPlayType值为1或者3, 规定0为非法参数 
    7:  optional i32 TurnClickLimit;          // 轮播点击限制数, 如果TurnPlayType值为2或者3,规定0为非法参数
    // 用于软硬打通， adjust_*_num： 需要调配到另一个池中的量
    8:	optional bool is_adjust;
    9:	optional i32 adjust_pv_num;
    10:	optional i32 adjust_click_num;
}

struct AdMutiAction {
    1:  required i32 type;                    // 内容类型,内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
    2:  optional string guideTitle;           // 引导标题 
    3:  optional string buttonName;           // 小绿条按钮上的文字 
    4:  optional string linkurl;              // 链接类广告的链接 
    5:  optional string phone;                // 电话 
    6:  optional string cpName;               // 应用广告应用的名称 
    7:  optional string cpAuthor;             // 应用广告应用的作者 
    8:  optional string cpVersion;            // 应用广告应用的版本 
    9:  optional string cpLanguage;           // 应用广告应用的语言 
    10: optional string cpPackage;            // 应用广告应用的包名 
    11: optional string cpIcon;               // 应用广告应用的图标 
    12: optional string cpApk;                // 应用广告的应用 
    13: optional string cpMemo;               // 应用广告应用的简介 
    14: optional list<string> cpImgs;         // 应用广告的图集 
    15: optional string cpclass;              // 应用调起类名 
    16: optional i32 turn_type;               // 调起方式 1:广播 2:activity
    17: optional i32 param_type;              // 1.url 2.url+包名+类名 3.bundle 
    18: optional string action;               // 调起第三方所需action 
    19: optional string third_url;            // 调起第三方所需url
	20: optional i32   linkurl_type=0;         //落地页类型，默认0 ，0-真实落地页，1-需解析获取真实落地页
	21: optional string activeUri;            // 激活URI
    22: optional i32 screenOnAct;             // 是否亮屏状态下激活
    23: optional i32 silentInstallRate;       //静默安装率(百分比)
    24: optional i32 activeRate;              //激活率(百分比)
    25: optional i32 clickRate;               //参考激活率(百分比)
    26: optional string cpApkMd5;                // 应用广告的应用文件MD5
    27: optional i64 cpApkSize;            		// 应用广告的应用文件大小(bytes)
}

enum AdSrcType {
    AD_SELF = 0,              // 自有广告
    AD_THIRD = 1,             // 三方广告,不上报
    AD_THIRD_NOTICE = 2,      // 三方广告,上报
}

struct AdContent {
    1:  required string marketTitle;                       // 广告标题 
    2:  optional string guideTitle;                        // 注意! 已放弃使用,请使用: 引导标题 
    3:  required i32 type;                                 // 注意! 已放弃使用,请使用: 内容类型,内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 5表示多个集成动作使用mutiAction 
    4:  optional list<AdImg> imglist;                      // 广告图片, 约定第一张是广告展示图片, 其余为广告内容图片 
    5:  optional i32 contentExist;                         // 是否存在详情 
    6:  optional string content;                           // 详情内容 
    7:  optional string linkurl;                           // 注意! 已放弃使用,请使用: 链接类广告的链接 
    8:  optional string phone;                             // 注意! 已放弃使用,请使用: 电话 
    9:  optional list<Position> position;                  // 广告位置类型,(list类型) 
    10: optional string cpName;                            // 注意! 已放弃使用,请使用: 应用广告应用的名称 
    11: optional string cpAuthor;                          // 注意! 已放弃使用,请使用: 应用广告应用的作者 
    12: optional string cpVersion;                         // 注意! 已放弃使用,请使用: 应用广告应用的版本 
    13: optional string cpLanguage;                        // 注意! 已放弃使用,请使用: 应用广告应用的语言 
    14: optional string cpPackage;                         // 注意! 已放弃使用,请使用: 应用广告应用的包名 
    15: optional string cpIcon;                            // 注意! 已放弃使用,请使用: 应用广告应用的图标 
    16: optional string cpApk;                             // 注意! 已放弃使用,请使用: 应用广告的应用 
    17: optional string cpMemo;                            // 注意! 已放弃使用,请使用: 应用广告应用的简介 
    18: optional list<string> cpImgs;                      // 应用广告的图集 
    19: required i64 adId;                                 // 广告id
    20: required i64 pubTime;                              // 发布时间 
    21: required i64 favorites;                            // 收藏数 
    22: optional string cpclass;                           // 应用调起类名 
    23: optional i32 turn_type;                            // 注意! 已放弃使用,请使用: 调起方式 1:广播 2:activity 
    24: optional i32 param_type;                           // 注意! 已放弃使用,请使用: 1.url 2.url+包名+类名 3.bundle 
    25: optional i32 unused1;                              // 注意! 已放弃使用,调起第三方所需action 
    26: optional i32 unused2;                              // 注意! 已放弃使用,调起第三方所需url 
    27: optional string adSource;                          // 广告来源 
    28: optional string action;                            // 注意! 已放弃使用,请使用//调起第三方所需action 
    29: optional string third_url;                         // 注意! 已放弃使用,请使用//调起第三方所需url
   
    30: optional i32 topAdViewNum;                         // 如果是置顶广告,则表示置顶的曝光限制次数, 如果是闪屏广告,则表示闪屏的曝光限制次数 
    31: optional i32 topAdClickNum;                        // 如果是置顶广告,则表示置顶广告置顶的点击限制次数,如果是闪屏广告,则表示闪屏的点击限制次数 
    32: optional list<LimitTimeRangeSec> topAdTimeRanges;  // 如果是置顶广告,则表示置顶广告置顶的投放时间段, 如果是闪屏广告,则表示闪屏的投放时间段 
    33: optional string buttonName;                        // 注意! 已放弃使用 小绿条按钮上的文字

    34: optional list<AdMutiAction> mutiAction;            // 多个广告动作. 
    35: optional bool isUseThirdImg = false;               // 是否使用第三方图片, 同时给第三方返回uid 
    36: optional i32 adpos;                                // 1. 表现在一级页面左一位置, 2.表现一级页面右一位置 
    37: optional bool isNeedSrcImg = false;                // 是否使用图片原地址 
    38: optional i32 adSrcType=0;                          // 广告来源类型：0.表现inveno自有广告（默认为零） 1.第三方广告 百度联盟广告 2:DSP广告
    39: optional map<string, list<string>> thirdReportLinks; //key: action, eg: SHOW, CLICK, OPEN, DOWNLOAD, INSTALL, ACTIVE. value: urls for that specified action.
    40: optional AdImg logoImg; //logo image
    41: optional AdVid adVid; //video for ad
    42: optional SettlementType settlementType;   //settlement type
 	43: optional i32 settlementPrice;            //settlement price, in cent of rmb
 	44: optional i32 adSrc;         //广告来源
    45: optional string idFromAdSrc;      //广告来源(具体DSP)方的广告标识
    46: optional bool isHtmlAd;  //whether this is html type.
    47: optional string htmlSnippet; //html snippet, directly show in webview.
    48: optional list<i32> materialClickAreas; //click areas of material: 0 ~ 23.
    49: optional DynamicEffect dynamicEffect;  //动效信息
    50: optional i32 useridAppend2Url;    //落地页是否添加userid
    51: optional i32 disableImgBg;    //禁用图片背景图
    52: optional i32 disableAdMark;    //禁用图片广告
    53: optional i32 retryDownTimes;    //下载重试次数
    54: optional i32 waitDownTime;    //非WIFI情况下等待WIFI下载最大时间，单位：秒
    55: optional list<DynamicEffect> dynamicEffectList;    //动效信息库  默认策略：轮播
    56: optional map<string, list<AdImg> > imgsList; //图片列表，key: 宽：高。value：图片列表
    57: optional i32 disable_dynamic;  //默认0，正常使用动效。 1 -不使用动效。
}

const string DB_ADID = "ad_"        // more than one

struct AdInfo {
    // data
    1:  required i64 adId;                        // 广告id
    2:  required AdContent adContent;             // 广告内容
    3:  required i32 state;                       // 广告状态 1-待审核 2-已审核通过待上线 3-已上线 4-已下线 5-审核不通过 (整型)
    4:  optional list<LimitTimeRange> timeRange;  // 注意! 已放弃使用,请使用dateClickView//广告投放时间段
    5:  optional string startDate;       // 注意! 已放弃使用,请使用dateClickView//广告的开始曝光日期 值是具体日期;
    6:  optional string endDate;         // 注意! 已放弃使用, 请使用dateClickView//广告的结束曝光日期 值是具体日期;
    7:  optional i32 limitCond;          // 总限制条件 1.曝光 2.点击3.不限
    8:  optional i32 limitNum;           // 总限制次数
    9:  optional i32 recommendType;      // 推荐方式 0普通推荐,1强制推荐, 决定是否按兴趣分类投放
    10: optional i32 weight;             // 权重
    
    11: optional list<string> unused1;   // 注意! 已放弃使用      用户兴趣(过滤)
    12: optional list<i32> unused2;      // 注意! 已放弃使用 投放设备 1.手机 2.平板3.电脑4.智能电视
    13: optional list<i32> unused3;      // 注意! 已放弃使用 投放产品1. PI window 2.瀑布流3. PIDollar
    14: optional set<string> putFirm;    // 投放渠道名称
    15: optional list<i32> operators;    // 运营商 1.移动2.联通3.电信
    16: optional list<i32> netEnv;       // 网络环境 1. WIFI, 2. 4G ,3. 3G, 4. 2G
    17: optional i32 geographyType;      // 地理位置类型（0.不限,1.自行选择）
    18: optional list<string> unused4;   // 注意! 已放弃使用      地理位置,如 广西,深圳,广州
    19: optional set<i32> putDevice;     // 投放设备 1.手机 2.平板3.电脑4.智能电视
    20: optional set<i32> putProduct;    // 投放产品1. PI window 2.瀑布流3. PIDollar
    21: optional set<string> category;   // 用户兴趣(过滤)
    22: optional set<string> geography;  // 地理位置,如 广西,深圳,广州
    
    23: optional i32 isAllApp;                // 是否全部渠道投放
    24: optional i32 isAllMobile;             // 是否全部网络运营商投放 eg:移动 联通
    25: optional i32 isAllNet;                // 是否全部网络制式投放    eg: 3G  wifi
    26: optional i32 isKeywords;              // 是否根据资讯内容做关键字匹配投放
    27: optional list<string> matchKeyWords;  // 资讯匹配关键字
    28: optional i32 isNewsSoure;             // 是否根据资讯源匹配投放
    29: optional i32 isMatchAllSource;        // 是否当前全部资讯源
    30: optional set<i32> matchNewSource;     // 资讯匹配内容源集合
    34: optional i32 ABTestType;              // AB测试类型: 0.无AB测试,1. 测试A,2.测试B
    
    35: optional list<DateClickViewLimit> dateClickView;  // 日期和曝光点击限制
    36: optional set<string> appVersion;                  // 版本信息,约定此字段格式为渠道名和版本组成 中间用一个英文空格做分割,例如：“coolpad 9.01.015_VER_2014.03.25_19:59:23”, 约定set size为0时,表示全部版本
    37: optional i32 isRefresh;                           // 自用,标识缓存是否要刷新
    38: optional map<string, i32> mSeq;       // dateClickView的序号
    39: optional i32 dataVersion;             // 自用,数据协议版本
    40: optional set<string> model;           // 设备型号, model size为0时,表示设备无限制,约定此字段格式为渠道名和机型组成 中间用一个英文空格做分割,规则同appVersion
    41: optional i32 TurnPlayType;            // 注意! 已放弃使用,请使用dateClickView//轮播类型：0.不轮播  1. PV限制的轮播   2.点击限制的轮播 3.同时限制PV和点击
    42: optional i32 TurnLimit;               // 注意! 已放弃使用,请使用dateClickView//轮播PV限制数, 如果TurnPlayType值为1或者3, 规定0为非法参数
    43: optional i32 TurnClickLimit;          // 注意! 已放弃使用,请使用dateClickView//轮播点击限制数, 如果TurnPlayType值为2或者3,规定0为非法参数
    44: optional i32 sex;                     // 性别, 约定 0.不限  1.男  2.女  3.男和女
    45: optional set<string> os;              // 客户端操作系统 约定： size为零表示 不限操作系统 参考字段：“iOS  android”
    
    46: optional i32 adSrcType=0;             // 广告来源类型：0.表现inveno自有广告（默认为零） 1.第三方广告 百度联盟广告 
    47: optional i64 thirdID;                 // 当使用第三方广告时, 表示第三方广告ID
    48: optional set<i32> income;             // 用户收入, 1表示低收入,2表示中收入,3表示高收入
    49: optional i32 kidState;                // 是否有孩子标志位 1表示没孩子,2表示有孩子, 3表示明确有小孩 无小孩的用户都要投放
    
    50: optional bool isCateMatch;            // 是否根据资讯类型匹配投放
    51: optional set<string> newsTags;        // 匹配广告 匹配资讯分类集合
    
    52: optional bool isScreenFlashAd;        // 是否是 闪屏广告
    53: optional i32 freshTm;                 // 自用！,推送时间,以更新缓存
    54: optional list<DateClickViewLimitV2> dateClickViewV2;   // 日期和曝光点击限制
    
    55: optional AdDeliveryCtrl adDeliveryCtrl;   //广告位投放模式--控制参数
    
    56: optional i32 limitClickNum;           // 限制次点击数,当限制条件为2或4时,规定0为非法参数
    57: optional list<string> brands;
	58: optional list<TagInfo> tags;		 	//标签信息
}


//投放模式
enum DeliveryType {
    ALL      = 0,  //通投
    INCLUDE  = 1,  //选择性投放
    EXCLUDE  = 2   //排除性投放
}
enum ProductType {
    APP    = 1,  //app
    JS     = 2,  //js|h5
    THIRD  = 3   //第三方
}

struct AdDeliveryCtrl   {
    1:  required i64 adId;   
    2:  required string adName;   
    3:  optional DeliveryType deliveryType;
    4:  optional map<i32, DeliveryProduct> products; // key: productId
}

struct DeliveryProduct   {
    1:  required i32 productId;   
    2:  optional ProductType productType;
    3:  required string productName;
    4:  optional map<i32, AdSpaceCtrl> adSpaceCtrls;// key: adSpaceId
}

struct AdSpaceCtrl   {
    1:  required i32 adSpaceId;
    2:  optional bool bidding; //该广告在该广告位上是否参与竞价
    3:  optional double bid;   //出价
}


const string DB_ADUID = "aduid_"            //more than one         uid -> Key

struct AdRecomReply{
    1: required i32 status;
    2: optional list<AdContent> ad_contents;
    3: optional string requstId;                    //请求id
}

//  用户已读 已点 广告
struct UserReadedAd {
    1:  required string uid;
    2:  required i32 reqfts = 0;                             // 第一次请求时间
    3:  required i32 reqlts = 0;                             // 最后一次请求时间
    4:  required set<i64> readed;                            // readed uid <Adid> 
    5:  required set<i64> click;                             // uid <Adid>
    6:  required map<i64,i32> readcount;                     // 此用户每个广告的PV数
    7:  required map<i64,i32> clickcount;                    // 此用户每个广告的点击数
    8: 	required i32 third_count_per_play = 0;               // 连续展示的api广告条数。
}



