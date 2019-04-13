include "AdRecommend.thrift"

namespace java com.ocean.proxy.thrift.entity

struct CommonRecord {
    1: required i64 beg_time;         // begin time
    2: required map<i64, i32> pv;     // map<timestamp, pv_num>
    3: required i32 sum;              // total pv
    4: required i32 max_active;       // 最大连续活跃时间
}

struct UserRecord {
    1: required CommonRecord common;
    2: required map<string, i32> ip;   // map<ip, num>
    3: required map<string, i32> dev;  // map<dev, num>
}

struct IpRecord {
    1: required CommonRecord common;
    2: required map<string, i32> user;  // map<uid, num>
    3: required map<string, i32> dev;  // map<dev, num>
}

struct DevRecord {
    1: required CommonRecord common;
    2: required map<string, i32> user;  // map<uid, num>
    3: required map<string, i32> ip;   // map<ip, num>
}

service SkyNet {
//  服务状态
    void ping(),
    
	bool check(1: string uid, 2: ad_recom.AdRecomReq req),
	
}
