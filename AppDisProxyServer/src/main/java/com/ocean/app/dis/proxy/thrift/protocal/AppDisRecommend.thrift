namespace java com.ocean.app.dis.proxy.thrift.entity

// 接口返回状态
enum ErrorCode {
    RC_SUCCESS       = 0,      	  // 成功
    RC_PARAM_ERROR   = -1,     	  //请求参数错误
    RC_INTER_ERROR   = -2,     	  //系统内部错误
	RC_UNKNOW_ERROR  = -3,     	  //未知错误
}

//应用类型
enum AppType {
	TYPE_APP  			= 1,          //APP 类型
	TYPE_GAME 			= 2,          //Game 类型
}


/* 
	com.tencent.android.qqdownloader 腾讯应用宝
	com.qihoo.appstore 360手机助手
	com.baidu.appsearch 百度手机助手
	com.xiaomi.market 小米应用商店
	com.huawei.appmarket 华为应用商店
	com.wandoujia.phoenix2 豌豆荚
	com.dragon.android.pandaspace 91手机助手
	com.hiapk.marketpho 安智应用商店
	com.yingyonghui.market 应用汇
	com.tencent.qqpimsecure QQ手机管家
	com.mappn.gfan 机锋应用市场
	com.pp.assistant PP手机助手
	com.oppo.market OPPO应用商店
	cn.goapk.market GO市场
	zte.com.market 中兴应用商店
	com.yulong.android.coolmart 宇龙Coolpad应用商店
	com.lenovo.leos.appstore 联想应用商店
	com.coolapk.market cool市场

*/

enum JoinSource {
	TENCENT_QQDOWNLOADER   	= 1,  		 // 应用宝
	BAIDU_APPSEARCH       	= 2,         // 百度
   	QIHOO_APPSTORE 			= 3,  		 // 360应用商城
	WANDOUJIA_PHOENIX2    	= 4,     	 // 豌豆荚
	XIAOMI_MARKET 			=5,			 //小米应用商店
	HUAWEI_APPMARKET		=6,			 //华为应用商店
	DRAGON_PANDASPACE		=7,			 //91手机助手
	HIAPK_MARKETPHO			=8,			 //安智应用商店
	YINGYONGHUI_MARKET		=9,			 //应用汇
	TENCENT_QQPIMSECURE		=10,		 //QQ手机管家
	MAPPN_GFAN				=11,		 //机锋应用市场
	PP_ASSISTANT			=12,		 //PP手机助手
	OPPO_MARKET				=13,		 //OPPO应用商店
	GOAPK_MARKET			=14,		 //GO市场
	ZTE_MARKET				=15,		 //中兴应用商店
	YULONG_COOLMART			=16,	     //宇龙Coolpad应用商店
	LENOVO_LEOS_APPSTORE	=17,		 //联想应用商店
	COOLAPK_MARKET			=18,		 //cool市场
	SOGOU_ZHUSHOU           =19,         //搜狗
	WANKA_APPSTORE			=20, 		 //玩咖
	ONEMOBI_APPSTORE		=21, 		 //onemobi 应用分发
	ANZHI_APPSTORE			=22, 		 //安智 应用分发
	WANGXIANG_APPSTORE      =23,          //旺翔 
	HAIQIBING_APPSTORE      =24,          //海奇兵（和应用宝API接口协议一样）
	QIHOO_TYPE2_APPSTORE    =25,          //360新的API（新旧接口并存）
	QUQUANSU_APPSTORE       =26,          //趣元素应用分发API
	YITONG_APPSTORE         =27,          //亿铜
	YOURAN_APPSTORE         =28,          //悠然
	DALUOMA_APPSTORE        =29,           //大罗马
	WANYUJIUXIN_APPSTORE    =30,           //万裕久鑫
	YOURAN_TYPE2_APPSTORE         =31,          //悠然
	BAIDUBAITONG_APPSTORE         =32,          //悠然
	TIANMEI_APPSTORE         =33,          //天美
	MILIANG_APPSTORE         =34,          //米量
	QUYUANSU2_APPSTORE         =35,          //趣元素2
	YOUYOU_APPSTORE         =36,            //优优
	ZS_APPSTORE             =37,            //Zs
}


//接口类型
enum InterType{
    KEY_WORD_SEARCH			= 1,		     // 关键字搜索接口
	APP_LIST_SEARCH		 	= 2,		     // 应用列表查询（应用推荐）
	TOP_LIST_SEARCH			= 3,		     // 榜单查询
	PKG_SEARCH				= 4,			 // 应用包名或id查询
	HOT_WORD_SEARCH 		= 5,             // 热词搜索
	APP_RECOMMEND_SEARCH 	= 6,         // 应用相关推荐
}

enum ReportType{
    REPORT_GET=1,							//get方式上报
    REPORT_POST=2,							//post方式上报
	
}

//附加信息
struct ExtData{
   1: required string key;                    //属性名
   2: required string value;                  //属性值  
   3: optional string desc;                   //描述
}

struct AppImg {
    1: optional string formate;     // 图片格式
    2: optional i32 height;         // 高
    3: optional i32 width;          // 宽
    4: optional string src;         // 图片url
    5: optional string alt;         // 图片位置
    6: optional string ref;         // <IMG>标识
}

//应用详情
struct AppInfo{
    1:  required AppType type;                // 应用类型，默认APP
    2:  optional string pkgName;              // 应用包名
    3:  optional string appName;              // 应用名称
    4:  optional string iconUrl;              // 应用小图标
	5:  optional string logoUrl;              // logo图标
    6:  optional string fileSize;             // 应用文件大小
    7:  optional string versionCode;          // 应用版本号
    8:  optional string versionName;          // 应用版本名称
	9:  optional string developer;            // 应用的出品方或个人作者 
    10: optional string appLanguage;          // 应用广告应用的语言 
    11: optional string  minSdkVersion;        // 支持的最小版本的 Android 版本
	12: optional string  desc;                 // 应用简介
	
}


struct AppDisContent {
    1:  required i64 adId;                    // 广告id,由proxy生成
	2:  optional string appId;                // 应用id 
	3:  optional string apkId;                // apk包名对应的id
	4:  optional string apkMd5; 		      // 应用apkMd5 
	5:  optional string signMd5;              // 包签名MD5
	6:  optional list<string> tags;           // 应用标签
	7:  optional string apkUrl;               // 应用在应用商城的最新下载地址
	8:  optional i64 totalDownloadTimes;      // 应用在应用商城的下载总量
	9:  optional i64 installedCount;          // 应用在应用商城的安装总量
	10: optional string categoryId;           // 应用在应用商城的分类id
    11: optional string categoryName;         // 应用在应用商城的分类名称
	12: optional string publicDate;           // 应用在应用商城的发布时间
	13: optional string updateDate;           // 应用在应用商城的更新时间
	14: required AppInfo appInfo;             // 应用详情
    15: optional list<AppImg> imglist;        // 应用在应用商城的图片列表
    16: optional string content;              // 应用在应用商城的简介
    17: optional JoinSource joinSource;	      // 接入的应用商城
	18: optional ReportType reportType=ReportType.REPORT_GET;       //上报类型,默认为get
	19: optional map<string, list<string>>    thirdReportParams;    //reportType.value=2时使用， server端post上报，所需参数（json串），上报类型 SHOW（pv上报）, CLICK(点击上报),DOWNLOAD_BEGIN（下载开始上报）,DOWNLOAD_END（下载完成上报）, INSTALL（安装上报）,ACTIVE（激活上报）
	20: optional map<string, list<string>>    thirdReportLinks;     //reportType.value=1时时间，上报链接，上报类型 SHOW（pv上报）, CLICK(点击上报),DOWNLOAD_BEGIN（下载开始上报）,DOWNLOAD_END（下载完成上报）, INSTALL（安装上报）,ACTIVE（激活上报）
	21: optional list<ExtData> ext;           // 附加信息
	22: optional i32 isAd;					  //是否为应用广告,默认为0-未知,1-为商业化广告，2-非商业化
	23: optional string payMode;  			  //结算方式，取值CPC/CPM/CPA等
	24: optional string price;  			  //结算价格（单位元）
}
/* 用户基础信息 */
struct DeviceInfo {
    1:  optional string imei;                         // 手机imei
    2:  optional string os;                           // 手机操作系统
    3:  optional string osversion;                    // 手机操作系统版本code
    4:  optional string phonemodel;                   // 手机型号
    5:  optional string mobile;                       // 运营商 :CMCC/CUCC/CTCC
    6:  optional string aaid;   				      // advertising id
    7:  optional string adid; 					      // android id; for android
    8:  optional string idfa; 						  // ios id; for ios
    9:  optional string brandId;					  // device brand id; determined by phone model
    10: optional string brandName; 				      // device's vendor
    11: optional i32 deviceHeight; 				      // device's height
    12: optional i32 deviceWidth; 				  	  // device's width
    13: optional string mac; 					  	  // device's mac address
    14: optional string imsi; 					      // device's imsi
    15: optional string serialNo; 				      // 移动设备序列号，Build.SERIAL
    16: optional string dip,                      	  //手机分辨率，单位尺寸像素的密度,如2.0
	17: optional string dpi,                 		  //density屏幕的密度，如401
	18: optional string osVerName;                    //手机操作系统版本name

}
//用户信息
struct UserInfo{
	1:  required string uid; 					      // 用户id
    2:  required string clientIp;                     // 客户端IP地址
    3:  optional string city;                         // 用户所在城市
    4:  optional string lon;                          // 用户所在位置经度
    5:  optional string lat;					      // 用户所在位置纬度
	6:  optional string net;                          // 网络环境 wifi, 4g ,3g, 2g
	7:  optional string ua;                           // 客户端的User-Agent值
	8:	optional i32 lac;								//basestation lac
    9:	optional i32 cid;								//basestation cid
}
//
struct AppSpaceInfo{        
	1:  required i32 adSpaceId;                       // zk录入的广告位id
	2:  optional i32 appId;                           // zk录入的应用id
	3:  required i32 spaceWidth;  					  // 广告位宽
	4:  required i32 spaceHeight; 					  // 广告位高


}
struct Page{
    1:  required i32 pageSize;    				     // 页大小
    2:  required i32 from;						     // 分页查询的起始索引值
    3:  required i32 limit;            				 // 分页查询的结束索引值
}
struct InterParam{
    1:  optional string keyWord;                    // 关键字查询接口使用
	2:  optional string hotWord;                    // 热词查询接口使用
	3:  optional list<ExtData> requiredParam;       // 不同接口额外特殊参数,如分页上下文的信息
	4:  optional string pkgName;                    // 应用包名
}
struct AppDisRecomReq {
    1:  required UserInfo userInfo;					  // 用户信息
    2:  optional AppInfo appInfo;                     // 应用详情，单个应用查询时使用
    4:  required DeviceInfo device;                   // 设备信息
    5:  optional string version;                      // 协议版本号  默认为1.0
    6:  required AppSpaceInfo appSpaceInfo;    		  // 广告位属性
	7:  optional list<AppType> shieldType;     	      // 屏蔽类型
	8:  optional Page page;					          // 分页查询
	9:  optional bool hasNext=false;                  // 0:没有下一页; 1:有下一页    
	10: optional JoinSource joinSource;		          // 接入三方应用商城
	11: optional list<ExtData>   joinParam;           // 接入应用商店的必要参数，在配置广告位时设置
	12: required InterType interType;                 // 请求接口类型
	13: optional InterParam interParam;              // 具体接口的参数

}

struct AppExtraData{
	1: required i32 id;       // 不同账号
    2: required i32 channel;  // 应用宝,360,百度
}


struct AppDisRecomReply{		
    1: required ErrorCode errorCode;       			  //返回错误码
	2: optional string    errorMsg;                   //出错时，返回错误信息
    3: optional list<AppDisContent> appDisContent;	  //广告列表
	4: optional map<string, list<string>> thirdReportLinks; //上报链接，如果AdContent中有上报地址以AdContent为准，上报类型 SHOW（pv上报）, CLICK(点击上报),DOWNLOAD_BEGIN（下载开始上报）,DOWNLOAD_END（下载完成上报）, INSTALL（安装上报）,ACTIVE（激活上报）
	5: optional AppExtraData extra_data; 	//extra data response.
}

//  应用分发服务
service AppDisRecommend {
    //  服务状态
    void ping(),
    //  获取信息列表接口
    AppDisRecomReply search(1:AppDisRecomReq req),

}

