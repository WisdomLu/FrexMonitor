package com.ocean.core.common.system;

/**  
 *  ����ƣ�    ErrorCode   
 *  �����ˣ�    Alex 
 */
public interface ErrorCode {
    int SUCCEED=0;//成功
    int INTER_ERROR=-1;//内部错误
    int PARAM_ERROR=1001;//参数错误
    int LOGOUT_ERROR=1002;
    int AD_EMPTY=0;//没有广告返回，请求正常
}
