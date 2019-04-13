package com.ocean.service.baitongInvokerHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.baidubt.pkgsearch.BaitongPkgSearchReq;
import com.ocean.persist.app.dis.baidubt.pkgsearch.BaitongPkgSearchResp;
import com.ocean.persist.app.dis.baidubt.pkgsearch.BaitongPkgSearchSecParams;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.BaitongPkgTask;

@Component(value="BaitongPkgSearchHandler")
public class BaitongPkgSearchHandler extends BaseHandler{
	private final Logger logger = MyLogManager.getLogger();
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		BaitongPkgSearchReq request=this.parsePkgParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,BaitongPkgSearchReq request) throws AppDisException {
		// TODO Auto-generated method stub
		return (BaitongPkgSearchResp)this.invoke(request, BaitongPkgSearchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		DeviceInfo device=req.getDevice();
		if(device==null){
			logger.error("BAIDUBAITONG_APPSTORE device info is empty!");
			return false;
		}
		if(StringUtils.isEmpty(device.getImei())){
			logger.error("BAIDUBAITONG_APPSTORE imei is empty!");
			return false;
		}

		if(StringUtils.isEmpty(device.getImsi())){
			logger.error("BAIDUBAITONG_APPSTORE imsi or serialNo  is empty!");
			return false;
		}
		if(StringUtils.isEmpty(device.getOsversion())){
			logger.error("BAIDUBAITONG_APPSTORE os version is empty!");
			return false;
		}



	  return true;
 
	}
    public  BaitongPkgSearchReq parsePkgParam(AppDisRecomReq req)throws AppDisException{
    	if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		if(!validate(req)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"parameter is error!");
		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		AppInfo  app=req.getAppInfo();
    	BaitongPkgSearchReq baitReq=new BaitongPkgSearchReq();
    	BaitongPkgSearchSecParams sec=getSecParams(app,user,device);
    	String secParamStr=Bean2Utils.toHttpParams(sec);
		try {
			String secStr = URLEncoder.encode(Base64.encodeBase64String(secParamStr.getBytes(CHARSET_CODER)) ,CHARSET_CODER);
			baitReq.setParams(secStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baitReq.setSecret(DigestUtils.md5Hex(req.getJoinParam().get(1).getValue()+req.getJoinParam().get(0).getValue()));
		InterParam interParam=req.getInterParam();

		String pkgName=interParam.getPkgName();
		if(StringUtil.isEmpty(pkgName)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"pkg search api parameter is empty!");
			
		}
		baitReq.setPackage_name(pkgName);
		baitReq.setApi_key(req.getJoinParam().get(0).getValue());
		baitReq.setClienttype("api");
		baitReq.setAd_type(1);
		baitReq.setPlatform("android");
		baitReq.setApi_version("20");
		baitReq.setLog_id(DigestUtils.md5Hex(device.getImei()+sec.getTimestamp()));
		baitReq.setPage(1);
		if(req.isSetPage()){
			baitReq.setPage_size(req.getPage().getPageSize());
		}else{
			baitReq.setPage_size(pageSize);//默认请求10个
		}
		baitReq.setUser_ip(user.getClientIp());
		
		
		return baitReq;
    }
    public BaitongPkgSearchSecParams getSecParams(AppInfo  app,UserInfo user,DeviceInfo device){
    	BaitongPkgSearchSecParams sec=new BaitongPkgSearchSecParams();
    	sec.setTimestamp(System.currentTimeMillis());
    	
    	sec.setNetwork(this.getNetwork(user.getNet()));
    	sec.setProduct_version(app.getVersionName());
    	sec.setImei(device.getImei());
    	sec.setImsi(device.getImsi());
    	sec.setOs_version(device.getOsVerName());
    	sec.setBrand(device.getBrandName());
    	
    	try {
			sec.setModel(URLEncoder.encode(device.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sec.setResolution(device.getDeviceWidth()+"x"+device.getDeviceHeight());
    	sec.setMac(device.getMac());
    	sec.setAction_type(1);
    	sec.setAction("show_list");
    	return sec;
    }
	private  String getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return "wf";
		}else if(NETWORK_2G.equals(net)){
			return net;
		}
		else if(NETWORK_3G.equals(net)){
			return net;
		}
		else if(NETWORK_4G.equals(net)){
			return net;
		}
		return "wf";
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		BaitongPkgTask task = new BaitongPkgTask();
		BaitongPkgSearchReq param = (BaitongPkgSearchReq) params;
		
	
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.BAITOGN_PKG_URL));
		String sufix=url.toString().indexOf("?")<0?"?":"&";
		url.append(sufix).append(Bean2Utils.toHttpParams(param));//原始链接已经带有参数

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}

}
