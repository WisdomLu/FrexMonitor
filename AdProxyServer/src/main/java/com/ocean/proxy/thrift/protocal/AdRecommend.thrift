include "AdRecomData.thrift"
namespace java com.ocean.proxy.thrift.entity


//  广告服务器和客户端接口

/* 返回码 */
enum AdResponseCode {
    RC_SUCCESS   = 0,    // 成功
    RC_ERROR     = 1,    // 未知错误 
    RC_NODATA    = 2,    // 没数据
    RC_PARAM     = 3,
    RC_DATAERROR = 4,
    RC_PUTDOWN   = 5,    // 已下架
}
/* 期望的广告类型 */
enum ExpectedMarketType {
	ANY = 0;  //任何一种
	LINK = 1;  //链接推广类
	APP= 2;  //应用类 
	PHONE = 4;  //电话推广类
	MESSAGE = 5;  //短信类
	MAIL = 6;  //发送邮件类
	VIDEO = 7; //视频类
}

/* 广告位允许的图片格式*/
enum AdSpaceImgFmt {
	JPG = 1,
	GIF = 2,
}

/* 广告来源：自有广告、淘宝广告、百度广告*/
enum AdSource {
	SELF = 1,
	TAOBAO = 2,
	BAIDU = 3,
}

/* 广告请求来源：GATE(默认)、OTA*/
enum From {
	GATE = 1,
	OTA = 2,
}

/* 用户基础信息 */
struct AdUserInfo {
    1:  optional string imei,                         // 手机imei
    2:  optional string os,                           // 手机操作系统
    3:  optional string osversion,                    // 手机操作系统版本,api level
    4:  optional string phonemodel,                   // 手机型号
    5:  optional string mobile,                       // 运营商CMCC/CUCC/CTCC
    6:  optional string client_ip,                    // 客户端IP地址
    7:  optional string city,                         // 用户所在城市
    8:  optional string lon,                          
    9:  optional string lat,
    10: optional string ua,                           // 客户端的User-Agent值
    11: optional string aaid,   				      //advertising id
    12: optional string adid, 					      //android id, for android
    13: optional string idfa, 						  //ios id, for ios
    14: optional string brand_id,					  //device brand id, determined by phone model
    15: optional string brand_name, 				  //device's vendor
    16: optional i32 device_height, 				  //device's height
    17: optional i32 device_width, 				      //device's width
    18: optional string mac, 					  //device's mac address
    19: optional string imsi, 					  //device's imsi
	20: optional string dip,                      //手机分辨率，单位尺寸像素的密度,如2.0
	21: optional string density,                  //屏幕的密度，如401
	22: optional string city_name,                //city name
	23: optional i32 lac,								//basestation lac
    24: optional i32 cid,								//basestation cid
    25: optional string os_api_level,
    26: optional string sn,							//手机序列号
    27: optional list<string> tag;      //user tags
    28: optional i32 dev_price_level,     //设备价格级别
    29: optional string ua_webv,                        // 客户端浏览器/ webv的User-Agent值
}
struct VidExt{
	1: optional string tradingType; //交易类型，可能取值为 RTB、PDB、PMP、PD
	2: optional string campaignId; //执行单 ID	如果当前流量属于PDB 或 Preferred Deal 订单(isPreferredDeals=true)，则Ad Exchange会校验DSP回复的素材“campaign_id”是否与此字段匹配，若不一致则会判为无效竞价
	3: optional list<string> acceptAdvertisingType; //本次展示机会可接受的广告投放类型。101000：品牌；102101：效果_电商；102102：效果_游戏；102100：效果_其它。
													//DSP回复的广告素材需要符合此要求，Ad Exchange 会检查素材的advertising_type 字段，如果不符合要求，则判定为无效竞价。
}
//新增广告位请求属性2015-12-09
//按广告位请求，请求属性(广告位期望)
struct UserAdSpaceAttri{        
	1: required i32 spaceWidth;  //广告位宽
	2: required i32 spaceHeight; //广告位高
	3: optional list<ExpectedMarketType> expectedMarketTypes;//终端产品期望的广告类型
	4: optional i32 allowedOpentype;//此广告位允许的打开方式(一期不用)
	5: optional i32 spacePosition; //广告位位置 0: 未知   1： 首屏  2：非首屏 (一期不用)
	6: required AdRecomData.AdSpaceType spaceType; // position
	
	7: optional i32 appId;
	8: optional i32 adSpaceId;
	9: optional set<AdSpaceImgFmt> imgFormats;//图片格式--除了文字链类型，其他类型的广告都需要
   10: optional i64 imgMaxSize;//图片文件最大容量--除了文字链类型，其他类型的广告都需要
   11: optional i32 titleMin;//标题最少字数
   12: optional i32 titleMax;//标题最多字数
   13: optional i32 cwMin;//文案最少字数
   14: optional i32 cwMax;//文案最多字数
   15: optional set<AdSource> adSources;//允许的广告来源 - 1：自由广告； 2：淘宝API广告
   16: optional From from;//广告请求来源 - 1：GATE；2：OTA
   17: optional VidExt vidExt;//针对video API扩展字段
   18: optional i32 bidFloor; //RTB底价，以分为单位
   19: optional bool allowedHtml; //is http allowed.
   20: optional  string joinDspName;//接入三方的dsp英文名称
   21: optional i32 h5type; //0：h5和h5都可以：1：只拉h5; 2:只拉菲h5
   22: optional i32 apptype; //0：app和非app都可以：1：只拉app; 2:只拉非app
   23: optional string joinPoseId; //三方平台id
}

struct AdRecomReq {
    1:  required string app,                          // app简称,coolpad
    2:  required string type,                         // All或者具体类型
    3:  required string version,                      // 版本号字符串
    4:  required i32 result_num,                      // 数字
    5:  required string ogin_name,                    // 账户字符串
    6:  optional bool log = true,                     // 0不打印；1打印
    7:  optional string token,                        // 验证码
    8:  optional string channel,                      // 渠道号
    9:  optional AdUserInfo userinfo,
    10: optional string net,
    11: optional string hash,
    12: optional i32 rver = 0,                        // 协议版本号
    13: optional bool check_ver = true,
    14: optional UserAdSpaceAttri userAdSpaceAttri, //用户广告位属性(用户期望)
}

/* 瀑布流响应 */
struct AdInfoResp {
    1:  required AdResponseCode retcode,              // 错误码
    2:  required list<AdRecomData.AdContent> data,

}

struct MatchNews {
    1:  required i64 infoid;
    2:  required string title;
    3:  required i32 rssid;
    4:  required string tags;
}

struct AdWithMatchResp {
    1:  required AdInfoResp ad;
    2:  required list<AdInfoResp> ads;
}

struct UserCachedAds {
	1: 	required string uid;
    2:  required set<i64> ids;
}

struct UserCacheStatus {
    1:  required bool fetching;
}

//  广告服务
service AdRecommend {
    //  服务状态
    void ping(),

    //  获取信息列表接口
    AdInfoResp search(1: string uid, 2: AdRecomReq req),
    
    // type: 1: ad,  2: news. 0: default.
    i32 report_pv(1: string uid, 2: i64 adid, 3: i32 type = 0);
    i32 report_click(1: string uid, 2: i64 adid),
}

