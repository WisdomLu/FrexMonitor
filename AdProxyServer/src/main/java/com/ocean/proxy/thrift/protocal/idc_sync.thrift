include "AdRecomData.thrift"
include "AdRecommend.thrift"

namespace java com.ocean.proxy.thrift.entity

struct AdRangeDyncmic {
    1: required i32 start_time;
    2: required i32 end_time;
    3: required i32 count;
}

struct AdTimeRangeInfo {
    1: required list<ad_recom_data.LimitTimeRangeSec> range_info;
}

struct AdRangeInfo {
    1: required list<AdRangeDyncmic> ranges;
}

service IdcDynamic {
    // 服务状态
    void ping();

	i32 updatePv(1:i64 adid, 2:i32 time_stamp);
	i32 updateClick(1:i64 adid, 2:i32 time_stamp);
	
	ad_recom_data.AdUserDynamic getDynamic(1:i64 adid);
	
	// not used
	ad_recom_data.AdUserDynamic getPredictedDynamic(1:i64 adid);

	i32 setAdTimeRangeInfo(1:i64 adid, 2:AdTimeRangeInfo range_info);
}

// AdDataSync service
struct AdSyncData {
    1:  required string idc,        // 机房名
    2:  required i64 tm = 0,        // 时间戳
    3:  required ad_recom_data.AdUserDynamic adDyna,  // PV和点击
}

struct AdSyncParam {
    1:  required string idc,
    2:  required i64 adid,
}

service IdcCenter {
    // 服务状态
    void ping(),
    
    i32 reportPv(1:string idc, 2:i64 time, 3:i64 adid, 4:ad_recom_data.AdUserDynamic dy),
    i32 reportClick(1:string idc, 2:i64 time, 3:i64 adid, 4:ad_recom_data.AdUserDynamic dy),
    
    list<ad_recom_data.AdUserDynamic> getDynamic(1:string idc, 2:i64 adid),
}
