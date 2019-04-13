package com.ocean.service.yrcpd_t2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.yrcpd_t2.appasyn.YouranT2AppasynApp;
import com.ocean.persist.app.dis.yrcpd_t2.appasyn.YouranT2AppasynReq;
import com.ocean.persist.app.dis.yrcpd_t2.appasyn.YouranT2AppasynResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.YouranT2AppasynTask;

@Component(value="YouranT2AppasynHandler")
public class YouranT2AppasynHandler   extends BaseHandler{

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		YouranT2AppasynReq request=parseAppasynParam(req);
		return (YouranT2AppasynResp)this.invoke(request, YouranT2AppasynResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}

	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	public YouranT2AppasynReq parseAppasynParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		YouranT2AppasynReq param=new YouranT2AppasynReq();
		param.setApp_id(req.getJoinParam().get(0).getValue());
		param.setHz_id(req.getJoinParam().get(1).getValue());
		
		param.setAppName(req.getAppInfo().getAppName());
		param.setVersionName(req.getAppInfo().getVersionName());
    	param.setVersionCode(Integer.parseInt(req.getAppInfo().getVersionCode()));
    	param.setIp(user.getClientIp());
    	param.setMac(device.getMac());
    	param.setDeviceType(1);
    	
    	param.setImei(device.getImei());
    	param.setImsi(device.getImsi());
    	try {
			param.setModel(URLEncoder.encode(device.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	param.setManufacturer(device.getBrandName());
    	param.setApiLevel(device.getOsversion());
    	param.setOsType(1);
    	param.setOsVersion(device.getOsVerName());
    	param.setOsVersionCode(device.getOsversion());
    	param.setApplicationType("1");
    	
    	param.setAndroidId(device.getAdid());
    	param.setSerialno(device.getSerialNo());
    	
    	param.setScreenSize(device.getDeviceWidth()+"x"+device.getDeviceHeight());
    	param.setScreenWidth(String.valueOf(device.getDeviceWidth()));
    	param.setScreenHeight(String.valueOf(device.getDeviceHeight()));
    	
    	param.setDip(Double.parseDouble(device.getDip()));
    	param.setScreenOrientation("1");
    	param.setNetworkType(getNetwork(user.getNet()));
    	param.setOperatorType(this.getOp(device.getMobile()));
    	
		if(req.isSetPage()){
			param.setPageSize(String.valueOf(req.getPage().getPageSize()));
		}else{
			param.setPageSize(String.valueOf(pageSize));//默认请求10个
		}
    	param.setUa(user.getUa());

    	
    	if(user.isSetLac()&&user.isSetCid()){
    		param.setCellularID(user.getCid());
    		param.setLac(user.getLac());
    	}else{
    		param.setCellularID(34860);
    		param.setLac(28883);
		}
		return param;
		
	}
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 100;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}
		else if(NETWORK_3G.equals(net)){
			return 3;
		}
		else if(NETWORK_4G.equals(net)){
			return 4;
		}
		return 0;
	}
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 1;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 3;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 2;
		}
		return 0;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		YouranT2AppasynTask task = new YouranT2AppasynTask();
		YouranT2AppasynReq param = (YouranT2AppasynReq) params;
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YOURANT2_LIST_ASYN_URL));
		url.append("&").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		//task.setHeaders(headers);
		return task;
	}
}
