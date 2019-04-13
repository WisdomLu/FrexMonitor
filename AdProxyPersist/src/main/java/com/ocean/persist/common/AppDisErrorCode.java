package com.ocean.persist.common;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月13日 
      @version 1.0 
 */
public interface AppDisErrorCode {
    int RC_SUCCESS       = 0;     	  // 成功
    int RC_PARAM_ERROR   = -1;     	  //请求参数错误
    int RC_INTER_ERROR   = -2;     	  //系统内部错误
	int RC_UNKNOW_ERROR  = -3;     	  //未知错误
}
