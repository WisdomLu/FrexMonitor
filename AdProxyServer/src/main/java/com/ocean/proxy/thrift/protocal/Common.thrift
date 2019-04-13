namespace java com.ocean.proxy.thrift.entity

/* 返回码 */
enum ResponseCode {
    RC_SUCCESS   = 0,    //成功
    RC_ERROR     = 1,    //未知错误
    RC_NODATA    = 2,    //没数据
    RC_PARAM     = 3,
    RC_DATAERROR = 4,
    RC_REDIS     = 5,
}

const string RD_KEY_NEWS = "news_"        //资讯key
const string RD_KEY_COMMENT = "cmt_"      //评论key
const string RD_KEY_COMMENTH = "cmth_"    //热门评论key
const string RD_KEY_FAV = "fav_"     //在线收藏key
const string RD_KEY_PROFILE = "uid_"     //profilekey
const string RD_KEY_BAIDU = "baidu_"     //百度API广告id

struct TransportValue{
    1:  required string app,
    2:  required string server,
    3:  required string fun,
    4:  required string param,
}

struct ReturnResult{
    1:  required string idc,
    2:  required string server,
    3:  required string fun,
    4:  optional string param,
    5:  required list<string> result,
}