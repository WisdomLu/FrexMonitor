package com.ocean.service.quyuansuInvokHandler.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月28日 
      @version 1.0 
 */
public abstract class QuyuansuBaseHandler  extends BaseHandler{
	  public final Logger logger = LoggerFactory.getLogger(QuyuansuBaseHandler.class);

	  public boolean validate(AppDisRecomReq req){
		  return true;
	  }
	  
}
