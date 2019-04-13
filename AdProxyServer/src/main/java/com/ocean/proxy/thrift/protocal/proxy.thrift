include "AdRecomData.thrift"
include "AdRecommend.thrift"

namespace java com.ocean.proxy.thrift.entity

struct ProxyUserIndex {
    1:  required i32 index;                         // 下一个要读取的广告 index
}

struct ProxyUserStatus {
    1:  optional bool fetching;                     // 是否正在拉取广告
}

struct ProxyUserInfo {
    1:  optional list<i64> adids;                   // 已经拉回的广告 id
    2:  optional map<string, i32> fetch_num;        // 每个厂商的广告拉取次数 
    3:  optional i32 total_fetch_num;               // 总的拉取次数
    4:  optional AdRecommend.AdUserInfo ad_user_info;
}

struct ProxyAdInfo {
    1:  optional AdRecomData.AdContent ad_content;   
    2:  optional string proxy_name;                 // 三方厂商名
    3:  optional list<string> pv_url;
    4:  optional list<string> click_url;
}
service ApiProxy {
    /* 服务状态 */
    void ping(),

    // 请求广告
    AdRecomData.AdRecomReply poll(1: string uid, 2: AdRecommend.AdRecomReq adreq),
    
    // 上报pv
    i32 notice(1: string uid, 2: i64 adid),

    // 上报click数
    i32 notice_click(1: string uid, 2: i64 adid),

    // 预取广告
    void prefetch(1: string uid, 2: AdRecommend.AdRecomReq adreq),
}
