package com.ocean.service.tianmeiInvokeHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.SortUrlParamsUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.tianmei.getAppList.TianmeiAppListUrlParams;
import com.ocean.persist.app.dis.tianmei.kwdSearch.TianmeiKeywordSchReq;
import com.ocean.persist.app.dis.tianmei.kwdSearch.TianmeiKeywordSchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.TianmeiKeywordTask;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="TianmeiPkgSearchHandler")
public class TianmeiKeywordSearchHandler    extends BaseHandler{
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		TianmeiKeywordSchReq request=this.parseKeywordSearchParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,TianmeiKeywordSchReq request) throws AppDisException {
		// TODO Auto-generated method stub
		return (TianmeiKeywordSchResp)this.invoke(request, TianmeiKeywordSchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}

	public TianmeiKeywordSchReq parseKeywordSearchParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		TianmeiKeywordSchReq param =new TianmeiKeywordSchReq();
		TianmeiAppListUrlParams urlParam=new TianmeiAppListUrlParams();
		AppInfo app=req.getAppInfo();
		if(StringUtils.isNotEmpty(app.getVersionCode())){
			urlParam.setPver(Integer.parseInt(app.getVersionCode()));
		}else{
			urlParam.setPver(20);
		}
		urlParam.setPkg(app.getPkgName());
		urlParam.setOsver(device.getOsVerName());
		urlParam.setOsint(Integer.parseInt(device.getOsversion()));
		
		try {
			urlParam.setBrand(URLEncoder.encode(device.getBrandName(), CHARSET_CODER));
			urlParam.setModel(URLEncoder.encode(device.getPhonemodel(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		urlParam.setAppid(req.getJoinParam().get(0).getValue());
		urlParam.setNettype(getNetwork(user.getNet()));
		urlParam.setResolution(device.getDeviceWidth()+"_"+device.getDeviceHeight());
		urlParam.setImsi(device.getImsi());
		urlParam.setImei(device.getImei());
		urlParam.setIp(user.getClientIp());
		urlParam.setMno("0");
	   	if(user.isSetLac()&&user.isSetCid()){
	   		urlParam.setCid(String.valueOf(user.getCid()));
	   		urlParam.setLac(String.valueOf(user.getLac()));
    	}else{
    		urlParam.setCid("0");
			urlParam.setLac("0");
		}

	   	urlParam.setAndroidid(device.getAdid());
	   	urlParam.setNonce((int)(System.currentTimeMillis()/1000));
	   	urlParam.setMac(this.converMac(device.getMac()).toUpperCase());
	   	urlParam.setSerno(device.getSerialNo());
	   	urlParam.setSo(1);
	   	if(StringUtils.isNotEmpty(device.getDpi())){
	   		urlParam.setDpi(Integer.parseInt(device.getDpi()));
	   	}else{
	   		urlParam.setDpi(0);
	   	}
	   	
	   	urlParam.setAdsize(req.getAppSpaceInfo().getSpaceWidth()+"x"+req.getAppSpaceInfo().getSpaceHeight());
	   	try {
			urlParam.setUa(URLEncoder.encode(user.getUa(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	
		InterParam interParam=req.getInterParam();
		if(StringUtil.isEmpty(interParam.getKeyWord())&&StringUtils.isEmpty(interParam.getHotWord())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"key word search parameter is empty!");
		}
		String keyWord=interParam.getKeyWord();
		if(StringUtil.isEmpty(keyWord)){
			keyWord=interParam.getHotWord();
		}
		try {
			urlParam.setPk(URLEncoder.encode(keyWord, CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   	String urlParams=SortUrlParamsUtils.toSortHttpParams(urlParam);
	   	param.setUrlParams(urlParams);
	   	String sign=DigestUtils.md5Hex(param.getUrlParams()+req.getJoinParam().get(1).getValue()).toLowerCase();
	   	param.setSign(sign);
	   	return param;
	}
	
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 1;
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
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		TianmeiKeywordTask task = new TianmeiKeywordTask();
		TianmeiKeywordSchReq param = (TianmeiKeywordSchReq) params;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;utf-8");
	
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.TIANMEI_KWD_URL));
		url.append("?").append(param.getUrlParams());
		param.setUrlParams(null);
		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		task.setHeaders(headers);
		return task;
	}
}
