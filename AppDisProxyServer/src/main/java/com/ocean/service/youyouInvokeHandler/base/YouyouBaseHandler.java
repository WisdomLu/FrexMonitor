package com.ocean.service.youyouInvokeHandler.base;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.service.base.BaseHandler;

public abstract class YouyouBaseHandler extends BaseHandler {
	public final Logger logger = LoggerFactory.getLogger(YouyouBaseHandler.class);
	  protected static final Map<String, String> youyouMobiles = new HashMap<String, String>(3);
	  static{
		youyouMobiles.put("CMCC", "46000");
		youyouMobiles.put("CUCC", "46001");
		youyouMobiles.put("CTCC", "46003");

	  }
	  protected static final Map<String, Integer> youyouCtype = new HashMap<String, Integer>();
	  static{
		youyouCtype.put(NETWORK_2G, 2);
		youyouCtype.put(NETWORK_3G, 3);
		youyouCtype.put(NETWORK_4G, 4);
		youyouCtype.put(NETWORK_WIFI, 1);
	  }
	  public boolean validate(AppDisRecomReq req){
		  return true;
	  }
}
