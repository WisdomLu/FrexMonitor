namespace java com.ocean.proxy.thrift.entity

struct NewsDisplay {
    1:  optional i32  show_type;       // 1表示顶部banner“巨无霸” 2表示带图资讯，图片+标题展示 3表示纯文字资讯，标题+摘要+来源 4表示纯文字资讯，标题+来源
    2:  optional bool isattitude;      // 是否允许表态
    3:  optional byte pos=0;           // 此资讯在一屏中的位置，0表示随机位置
    4:  optional map<i32, i32> atts;   // 态度     
}

struct Location {
    1:  optional string city;          // 市:深圳
    2:  optional string state;         // 省:广东
    3:  optional string country;       // 国家
    4:  optional i32 weight;           // 地域权重:0-10
}

/* 广告 */
struct Ad {
    1:  optional string infoid;             // 咨询id
    2:  optional string summary;            // 简介
    3:  optional string phone;              // 电话号码
    4:  optional i32 second;                // 二级动作
    5:  optional string imgurl;             // 页面图片的url
    6:  optional string cpicon;             // cp图标
    7:  optional string itype;              // 
    8:  optional string cpversion           // 版本号
    9:  optional string cpauthor;           // 作者
    10: optional i32 againPvervalCount;     // 
    11: optional string rtime;              // 发布时间
    12: optional string action;             // 
    13: optional string longitude;          // 范围投放广告的点的经度，当广告不是范围投放时可以为空。
    14: optional i32 cnum;                  // 
    15: optional string dataid;             // 咨询id
    16: optional i32 starttime;             // 开始曝光时间
    17: optional string linkurl;            // 链接参数或者原文地址
    18: optional string size;               // 音视频文件的大小
    19: optional string content;            // 内容
    20: optional string cppackage;          // 广告应用包名
    21: optional string startdate;          // 广告的开始曝光日期
    22: optional i32 dnum;                  // 
    23: optional i32 ratio;                 // 资讯类所占总资讯的比例，默认是10，资讯的总数为100
    24: optional i32 hot;                   // 是否为热点
    25: optional i32 radius;                // 范围投放广告的半径
    26: optional string latitude;           // 范围投放广告的点的纬度，当广告不是范围投放时可以为空
    27: optional string enddate;            // 广告的结束曝光日期 值是具体日期：如2013-3-14
    28: optional i32 endtime;               // 广告的结束曝光时间
    29: optional string pbig;               // 包的大小
    30: optional string stick;              // 广告是否置顶  1表示正常显示(取消置顶)，2表示置顶显示
    31: optional list<string> imglist;      // 应用广告的图集
    32: optional i32 pvnum;                 // 点击数
    33: optional i32 state;                 // 广告的状态 3：上架，4：下架
    34: optional string title;              // 资讯标题
    35: optional string cplanguage;         // 包语言
    36: optional string oidString;          // 源rss id
    37: optional string cpname;             // 渠道名字
    38: optional i32 majori;                // 重要标记(暂时未用)
    39: optional i32 downcount;             // 下载量
    40: optional i32 aging;                 // 表示广告是否具有时效性，默认为1表示按照时间段展示，2表示一直有效， 3表示按照发布时间
    41: optional string banner;             // 广告是否通栏显示  1表示正常显示，2表示通栏显示
    42: optional string originurl;          // 标识原文地址
    43: optional string keyword;            // 广告的描述，即对这个广告所选受众的属性：如男，女，白领，20岁到30岁等
    44: optional string origin;             // 资讯来源
    45: optional string ctype;              // 内容类型1表示链接类广告，2表示应用广告，3表示互动广告 4表示通话类型，5表示品牌推广 ，6视频类广告，7音频类广告，8表示小说阅读类型
    46: optional string cpapk;              // apk包路径
    47: optional string cpmemo;             // 渠道简介
    48: optional string duration;           // 视频文件的播放时长
    49: optional string adtitle;            // 引导标题
    50: optional i32 first;                 // 一级动作(只在主界面有效)，1表示打开内部网页，2表示使用浏览器打开，3表示打开内页，4 拨打电话5表示激活或者下载应用
    51: optional i32 tune_type;             // 调起方式. 0 是普通资讯。1是小说。2是视频。3是音乐
    52: optional i32 weight;                // 人工设置广告权重
    53: optional i32 param_type;
    54: optional string third_url;          // 调起第三方所需url
    55: optional i32 vcode;
    56: optional string cpclass;            // 应用调起类名
}

/* 绿条对象 */
struct ExtendJson {
    1:  optional string cpPackage;
    2:  optional string phone;
    3:  optional string linkurl;
    4:  optional i32 second;
    5:  optional i32 tunetype;
    6:  optional string origin;
    7:  optional string ctype;
    8:  optional string cpicon;
    9:  optional string adtitle;
    10: optional string infoid;
    11: optional string content;
    12: optional string cpapk;
    13: optional string title;
    14: optional string rtime;
    15: optional string cpname;
    16: optional string originurl;
    17: optional i32 first;         // 1表示打开内部网页,2表示使用浏览器打开,3表示打开内页,4 拨打电话,5表示激活或者下载应用,6跳客户端web页,7是H5方案2打开。8是含有H5视频打开
    18: optional i32 param_type;
    19: optional string action;
    20: optional string third_url;
    21: optional i32 vcode;
    22: optional string cpclass;    // 应用调起类名
}

/* 图片 */
struct Img {
    1: optional string ref;         // 标识
    2: optional i32 height;         // 高
    3: optional i32 width;          // 宽
    4: optional string src;         // 源图片url
    5: optional string alt;         // 图片位置
    6: optional i32 urlhash = 1;    // 是否使用hash图片
    7: optional list<byte> content;
    8: optional string desc;        // 人工图集字段 有值表明是人工图集
}

struct SubSpecial {
    1:  optional i32  type;       // 0:资讯,1:占位符
    2:  required string infoid;   // 咨询id
    3:  required string title;    // 标题
    4:  optional Img img;         // 缩略图
    5:  optional  string source;  // 资讯来源
    6:  optional i32 tm;          // 发布时间
    7:  optional string link;
    8:  optional string keyword;  // 关键字
    9:  optional byte showtype;   // 展示方式 0普通，1图集，2通栏，3轮播图
    10: optional Img adimgurl;    // 宣传图
    11: optional list<Img> imgs;
}

struct Special {
    1:  required string title;                // 专题标题
    2:  optional Img img;                     // 宣传图
    3:  required i32 expire;                  // 失效时间
    4:  required string specialbrief;         // 专题简介
    5:  optional list<SubSpecial> subSpecials;
    6:  optional i32 top = 0;                 // 专题置顶
    7:  optional byte status;                 // 1:预览.  0:正常.
    8:  optional string source;               // 来源
    9:  optional i32 tm;                      // 发布时间
}

struct subVote {
    1:  optional string voteTitle;              // 投票选项标题
    2:  optional Img voteImg;                   // 投票选项图片
}

struct Topic {
    1:  optional byte isBarrage;                // 是否开启弹幕。0：开启。1：不开启。 
    2:  optional map<i32, i32> effecttm;        // 开始时间-结束时间。单位秒
    4:  optional string voteTitle;              // 投票标题
    5:  optional list<subVote> subVoteList;     // 投票选项
    6:  optional i32 count;                     // 可以选择的投票项
}

struct TopicDetaildata{
    1:  optional string topicid,      // 话题id
    2:  optional string title,        //  标题
    3:  optional string picurl,       // 图片链接 
    4:  optional string agreedesc,    // 支持描述
    5:  optional string disagreedesc, // 反对描述
    6:  optional i64 agreeamount,     // 支持人数
    7:  optional i64 disagreeamount,  // 反对人数
    8:  optional string clickurl      // 链接html5地址
}


/* 咨询 */
struct Info {
    1:  required string stemplate;          // 展示模板
    2:  required string tags;               // 分类
    3:  required string body;               // 文章内容
    4:  optional ExtendJson json;           // 绿条对象
    5:  required i32 reply_count;           // 回复数量
    6:  required i32 rssid;                 // 来源id
    7:  required string source_url;         // 原文链接
    8:  required string source_mining;      // 内容来源
    9:  optional list<Img> img;             // 图片集
    10: required string infoid;             // 咨询id
    11: required string snippet;            // 简介
    12: required string type;               // 咨询类型: news
    13: required string category;           // 详细分类: 花瓣网->创意->摄影
    14: required i32 tm;                    // 发布时间
    15: required string title;              // 标题
    16: required string source;             // 原文来源
    17: optional i32 firmid;                // 发送给的渠道
    18: optional Ad ad;                     // 广告
    19: required string datatype;           // 咨询类型:1,2,3
    20: optional i32 favorite_num  = 0;     // 收藏数
    21: optional i32 click_num     = 0;     // 点击数
    22: optional i32 comments_num  = 0;     // 评论数
    23: optional i32 share_num     = 0;     // 转载数
    24: optional byte isextend;             // 是否包含ad,json
    25: optional byte hot;                  // 热度0-100
    26: optional list<ExtendJson> jsons;    // 支持多个小绿条
    27: optional list<Location> location;   // 地域属性
    28: optional i32 timeprop;              // 时效性:-1,0,1
    29: optional byte top;                  // 0-普通资讯,1-头条,2-精编，4-人工热门 6-下架人工热门 3-无标识头条
    30: optional i32 score;                 // 评分
    31: optional i32 status = 0;            // 资讯状态:-1已下线,0正常
    32: optional i32 banner = 0;            // 资讯是否通栏展示,1:通栏
    33: optional i32 iscomm = 0;            // 表示此条资讯是否允许评论，0表示允许，1表示不允许
    34: optional string channel;            // 大类
    35: optional i32 pnum = 0;              // 评论数
    36: optional string label;
    37: optional i32 hand = 0;              // 手工发:1  自动发：0  周末资讯回滚：2
    38: optional list<string> labels;       // 标签
    39: optional set<string> apps;          // 支持的渠道，全渠道不填内容
    40: optional string oldtags;            // 原标签
    41: optional i32 taste;                 //  人群兴趣ID值
    42: optional i32 person_channel;        // 个性频道ID值
    43: optional i32 happy;
    44: optional i32 anger;
    45: optional Special special;
    46: optional i32 subscribeid=-1;         // 订阅id
    47: optional i32 labeltm=0;              // 标签失效时间 天数
    48: optional map<i32, i32> labelmaptm;   // 标签失效时间 时间段
    49: optional Img thumbnail;              // 缩略图
    50: optional list<string> recomms;       // 相关推荐资讯，最多5条,ID从大到小
    51: optional map<string, set<string> > pm;   // 支持的机型,渠道:机型
    52: optional NewsDisplay exdata;             // 小知主干新加字段 
    53: optional i32 rtm;                        // 阅读时间
    54: optional i32 stm;                        // 收藏时间
    55: optional i32 isbanner;                   // 资讯通栏展示
    56: optional i32 templatedatatype;           // 1:单图 2:多图 3:图集 4:无图
    57: optional i32 rnum;                       // 阅读数
    58: optional map<i32, i32> bannertm;         // banner失效时间 时间段
    59: optional i32 ontopbanner=0;              // 位于顶部bannner资讯
    60: optional i32 rsstype;                    // rss大源分类
    61: optional Img icon;                       // 通知消息图片
    62: optional i32 msgpushtm;                  // 消息推送时间
    63: optional string lang;                    // 资讯的语言
    64: optional Topic topic;                    // 话题
    65: optional map<string, set<string> > vm;   // 支持的版本，协议版本号。渠道:版本
    66: optional i32 topbannerstatus = 0;        // 顶部bannner单独下架:-1已下线,0正常
    67: optional i32 gtm;                        // 获取时间
    68: optional TopicDetaildata  topicdetailinfo;  // 淘皮客
}

struct ScreenImg {
    1:  optional i32 imgid;              // 图片ID
    2:  optional Img img;                // 图片
    3:  optional map<i32, i32> imgtm;    // 生效时间段
    4:  optional byte status;            // 状态。1：生效。2:停止
    5:  optional string app;
    6:  optional map<i32, i32> imghour;  // 投放时段,比如7点到9点投放
}
