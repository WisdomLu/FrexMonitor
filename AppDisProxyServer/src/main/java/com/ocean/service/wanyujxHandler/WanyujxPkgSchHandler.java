package com.ocean.service.wanyujxHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchApp;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchDev;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchNet;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchReq;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchResp;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchSlot;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.WanyujxPkgTask;

@Component(value="WanyujxPkgSchHandler")
public class WanyujxPkgSchHandler extends BaseHandler{

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		WanyujxPkgSchReq request=this.parsePkgParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,WanyujxPkgSchReq request) throws AppDisException {
		// TODO Auto-generated method stub
		return (WanyujxPkgSchResp)this.invoke(request, WanyujxPkgSchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
    public WanyujxPkgSchReq parsePkgParam(AppDisRecomReq req)throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		
/*		String valStr=strValidate(req);
		if(!VALIDATE_OK.equals(valStr)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,valStr+" parameter is Empty!");
		}*/
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		WanyujxPkgSchReq param=new WanyujxPkgSchReq();
		param.setRequestId(UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis());
		
		//app
		WanyujxPkgSchApp app =new WanyujxPkgSchApp();
		app.setAppPackage(req.getAppInfo().getPkgName());
		app.setAppVersion(req.getAppInfo().getVersionName());
		app.setAppName(req.getAppInfo().getAppName());
		param.setApp(app);
		
		//slot
		WanyujxPkgSchSlot slot=new WanyujxPkgSchSlot();
		slot.setSlotId(req.getJoinParam().get(0).getValue());
		slot.setSlotwidth(req.getAppSpaceInfo().getSpaceWidth());
		slot.setSlotheight(req.getAppSpaceInfo().getSpaceHeight());
		param.setSlot(slot);
		
		//device
		WanyujxPkgSchDev wyjxDev=new WanyujxPkgSchDev();
		wyjxDev.setImei(device.getImei());
		wyjxDev.setImeiMd5(DigestUtils.md5Hex(device.getImei()));
		wyjxDev.setMac(device.getMac());
		wyjxDev.setAndroidId(device.getAdid());
		try {
			wyjxDev.setModel(URLEncoder.encode(device.getPhonemodel(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wyjxDev.setVendor(device.getBrandName());
		wyjxDev.setScreenHeight(device.getDeviceHeight());
		wyjxDev.setScreenWidth(device.getDeviceWidth());
		
		if(OS_IOS.equals(device.getOs())){
			wyjxDev.setOsType(2);
			wyjxDev.setIdfa(device.getIdfa());
			wyjxDev.setIdfaMd5(DigestUtils.md5Hex(device.getIdfa()));
		}else{
			wyjxDev.setOsType(1);
		}
		wyjxDev.setOsVersion(device.getOsVerName());
		wyjxDev.setDeviceType(1);
		wyjxDev.setUa(user.getUa());
		try{
		     wyjxDev.setPpi(Integer.parseInt(device.getDpi()));
		}catch(Exception e){
			 wyjxDev.setPpi(480);
		}
		wyjxDev.setScreenOrientation(1);
		wyjxDev.setBrand(device.getBrandName());
		wyjxDev.setImsi(device.getImsi());
		param.setDevice(wyjxDev);
		//network
		WanyujxPkgSchNet net=new WanyujxPkgSchNet();
		net.setConnectionType(this.getNetwork(user.getNet()));
		net.setOperatorType(this.getMobile(device.getMobile()));
		if(user.getCid()>0){
			net.setCellular_id(String.valueOf(user.getCid()));
		}
		
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			net.setLat(Float.parseFloat(user.getLat()));
			net.setLon(Float.parseFloat(user.getLon()));
		}
		param.setNetwork(net);
		
		InterParam interParam=req.getInterParam();
		String pkgName=interParam.getPkgName();
		if(StringUtil.isEmpty(pkgName)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"pkg search api parameter is empty!");
			
		}
		param.setDown_pkgname(pkgName);
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
		}else if(NETWORK_5G.equals(net)){
			return 5;
		}
		return 1;
	}
	private  int getMobile(String moble){	
		if(MOBILE_CMCC.equals(moble)){
			return 1;
		}else if(MOBILE_CUCC.equals(moble)){
			return 3;
		}
		else if(MOBILE_CTCC.equals(moble)){
			return 2;
		}
		return 0;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		WanyujxPkgTask task = new WanyujxPkgTask();
		WanyujxPkgSchReq param = (WanyujxPkgSchReq) params;
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.WANYUJX_PKG_URL));
		

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		task.setHeaders(headers);
		return task;
	}
}
