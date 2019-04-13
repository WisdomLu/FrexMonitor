package com.ocean.service.qqInvokHandler.base;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月28日 
      @version 1.0 
 */
public abstract class QQBaseHandler extends BaseHandler{
	public  boolean validate(AppDisRecomReq req){
		return false;
	}
}
