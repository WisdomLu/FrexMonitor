package com.ocean.service.qihooInvokeHandler.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.service.base.AbstractAsynAppSupplier;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月28日 
      @version 1.0 
 */
public abstract class QihooBaseHandler  extends BaseHandler{
	  public final Logger logger = LoggerFactory.getLogger(QihooBaseHandler.class);
	  protected static final Map<String, String> qihooMobiles = new HashMap<String, String>(3);
	  static{
		qihooMobiles.put("CMCC", "46000");
		qihooMobiles.put("CUCC", "46001");
		qihooMobiles.put("CTCC", "46003");

	  }
	  protected static final Map<String, Integer> qihooCtype = new HashMap<String, Integer>();
	  static{
		qihooCtype.put(NETWORK_2G, 2);
		qihooCtype.put(NETWORK_3G, 3);
		qihooCtype.put(NETWORK_4G, 4);
		qihooCtype.put(NETWORK_WIFI, 1);
	  }
	  public boolean validate(AppDisRecomReq req){
			DeviceInfo device=req.getDevice();
			AppInfo app=req.getAppInfo();
			if(device==null){
				logger.error("QIHOO_APPSTORE device info is empty!");
				return false;
			}
			if(app==null){
				logger.error("QIHOO_APPSTORE app info is empty!");
				return false;
			}
			if(StringUtils.isEmpty(device.getDip())){
				logger.error("QIHOO_APPSTORE dip is empty!");
				return false;
			}
			if(StringUtils.isEmpty(device.getImei())){
				logger.error("QIHOO_APPSTORE imei is empty!");
				return false;
			}
			if(StringUtils.isEmpty(device.getAdid())){
				logger.error("QIHOO_APPSTORE androidId is empty!");
				return false;
			}
			if(StringUtils.isEmpty(device.getSerialNo())&&StringUtils.isEmpty(device.getImsi())){
				logger.error("QIHOO_APPSTORE imsi or serialNo  is empty!");
				return false;
			}
			if(StringUtils.isEmpty(device.getOsversion())){
				logger.error("QIHOO_APPSTORE os version is empty!");
				return false;
			}
			if(StringUtils.isEmpty(app.getPkgName())){
				logger.error("app pacakage name is empty!");
				return false;
			}
			if(StringUtils.isEmpty(app.getAppName())){
				logger.error("app  name is empty!");
				return false;
			}
			if(StringUtils.isEmpty(app.getVersionCode())){
				logger.error("app version code   is empty!");
				return false;
			}
			if(StringUtils.isEmpty(app.getVersionName())){
				logger.error("app version   name is empty!");
				return false;
			}

		  return true;
	  }
}
