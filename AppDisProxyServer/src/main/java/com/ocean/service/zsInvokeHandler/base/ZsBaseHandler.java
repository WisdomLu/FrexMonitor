package com.ocean.service.zsInvokeHandler.base;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.service.base.BaseHandler;

public abstract class ZsBaseHandler extends BaseHandler {
	public final Logger logger = LoggerFactory.getLogger(ZsBaseHandler.class);
	  protected static final Map<String, String> zSMobiles = new HashMap<String, String>(3);
	  static{
		zSMobiles.put("CMCC", "1");
		zSMobiles.put("CUCC", "2");
		zSMobiles.put("CTCC", "3");

	  }
	  protected static final Map<String, Integer> zSCtype = new HashMap<String, Integer>();
	  static{
		zSCtype.put(NETWORK_2G, 2);
		zSCtype.put(NETWORK_3G, 3);
		zSCtype.put(NETWORK_4G, 4);
		zSCtype.put(NETWORK_WIFI, 1);
	  }
	  public boolean validate(AppDisRecomReq req){
		  return true;
	  }
}
