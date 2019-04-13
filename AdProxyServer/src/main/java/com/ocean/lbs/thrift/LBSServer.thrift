namespace java com.ocean.thrift.entity
enum ErrorCode {
    RC_SUCCESS       = 0,      	  // 成功
    RC_PARAM_ERROR   = -1,     	  //请求参数错误
    RC_INTER_ERROR   = -2,     	  //系统内部错误
	RC_UNKNOW_ERROR  = -3,     	  //未知错误
}

enum CoorType {
	COOR_BD09LL = 0, 	//为一种大地坐标系，也是目前广泛使用的GPS全球卫星定位系统使用的坐标系； 
	COOR_GCJ02 = 1, 	//表示经过国测局加密的坐标
	COOR_WGS84 = 2, 	//为一种大地坐标系，也是目前广泛使用的GPS全球卫星定位系统使用的坐标系；

}

struct LBSByAddressReq {
    1: required string address,	//地名
    2: optional string city,	//城市名称
    3: optional CoorType  coor,	//坐标类型
}
struct LBSByAddressResp{
    1: required ErrorCode errorCode,	//错误码
    2: optional string errorMsg,	//错误信息
    3: optional LBSByAddressContent content, //查询内容
}
struct LBSByAddressContent{
    1: required string lat,		//维度
    2: required string lng,		//经度
    3: optional string level,	//地址类型
    
}


struct LBSByIPReq {
    1: required string ip,
    2: optional CoorType  coor,	//坐标类型
}

struct LBSByIPResp {
    1: required ErrorCode errorCode,	//错误码
    2: optional string errorMsg,	//错误信息
    3: optional  LBSByIPContent content,
}

struct LBSByIPContent{
    1: optional string		 	address,	//地名
    2: optional string          province,	//省份  
    3: required string 			city,    	//城市名称
    4: optional string 			cityCode,	//城市代码
    5: optional string          district,	//区县  

    6: optional string          street,  	//街道  
    7: optional string          streetNo,	//门址  
    8: required string 			lat,		//维度
    9: required string 			lng,		//经度
    
}
service LBSServer {
    //  服务状态
    void ping(),
    LBSByIPResp searchByIP(1:LBSByIPReq req),
    LBSByAddressResp searchByAddress(1:LBSByAddressReq req),
}