package com.ocean.service.qihooT2InvokeHandler;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.appSearch360T2.keywordSearch.KeywordSearch360T2Puller;
import com.ocean.persist.app.dis.appSearch360T2.keywordSearch.KeywordSearch360T2Request;
import com.ocean.persist.app.dis.appSearch360T2.keywordSearch.KeywordSearch360T2Response;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.qihooInvokeHandler.base.QihooBaseHandler;
import com.ocean.task.QihooType2KeywordTask;
@Component(value="Qihoo360T2KeywordSearchHandler")
public class Qihoo360T2KeywordSearchHandler extends QihooBaseHandler{
	@Autowired
	private KeywordSearch360T2Puller puller;

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		KeywordSearch360T2Request request=parseKeyWordSearchParam(req);
		//return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		return (KeywordSearch360T2Response)this.invoke(request, KeywordSearch360T2Response.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	private KeywordSearch360T2Request parseKeyWordSearchParam(AppDisRecomReq req) throws AppDisException{
		KeywordSearch360T2Request request=new KeywordSearch360T2Request();
		if(CollectionUtils.isEmpty(req.getJoinParam())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,req.getJoinSource().name()+": please check third plat applying paramters is  empty or not");
			
		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		AppInfo app=req.getAppInfo();
		if(!validate(req)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"parameter is error!");
		}
		for(int i=0;i<req.getJoinParam().size();i++){
			ExtData ext=req.getJoinParam().get(i);
			if(i==0){
				request.setFrom(ext.getValue());
			}else if(i==1){
				request.setCh(ext.getValue());
			}else if(i==2){
				request.setAdspaceid(ext.getValue());
			}

		}
		request.setSdk_track(0);
		request.setTs((int)(System.currentTimeMillis()/1000));
		
		request.setIp(user.getClientIp());
		request.setImei(device.getImei());
		request.setAndroidid(device.getAdid());
		if(StringUtils.isNotEmpty(device.getSerialNo())){
			request.setSerialno(device.getSerialNo());
		}else{
			request.setSerialno(device.getImsi());
		}
	
		request.setMd(device.getPhonemodel());
		request.setBr(device.getBrandName());

		if(StringUtils.isNotEmpty(device.getOsversion())){
			request.setOs(Integer.parseInt(device.getOsversion()));
		}
		
		if(StringUtils.isEmpty(device.getOsVerName())&&StringUtils.isNotEmpty(device.getOsversion())){
			request.setOsv(this.convOSVersion(device.getOsversion()));
		}else if(StringUtils.isNotEmpty(device.getOsVerName())){
			request.setOsv(device.getOsVerName());
		}

		
		String cat=qihooMobiles.get(device.getMobile());
		if(StringUtils.isNotEmpty(cat)){
			request.setCarrier(cat);
		}else{
			request.setCarrier("0");
			
		}
		
		request.setSw(device.getDeviceWidth());
		request.setSh(device.getDeviceHeight());
		request.setDip(Double.parseDouble(device.getDip()));
		//app
		request.setApppkg(app.getPkgName());
		request.setAppname(app.getAppName());
		request.setAppv(app.getVersionName());
		request.setAppvint(Integer.parseInt(app.getVersionCode()));
		
		//动态参数
		request.setSo(1);
		Integer net=qihooCtype.get(user.getNet());
		request.setNet(net==null?5:net);
		
		InterParam interParam=req.getInterParam();
		if(StringUtil.isEmpty(interParam.getKeyWord())&&StringUtils.isEmpty(interParam.getHotWord())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"key word search parameter is empty!");
		}
		String keyWord=interParam.getKeyWord();
		if(StringUtil.isEmpty(keyWord)){
			keyWord=interParam.getHotWord();
		}
		request.setSearchword(keyWord);
		request.setTag(keyWord);
		if(req.isSetPage()){
			request.setNum(req.getPage().getPageSize());
		}else{
			request.setNum(pageSize);//默认请求10个
		}
		request.setPage(1);
		request.setUa(user.getUa());
		request.setMac(device.getMac());
		
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			request.setLat(Double.parseDouble(user.getLat()));
			request.setLon(Double.parseDouble(user.getLon()));
		}
		return request;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		QihooType2KeywordTask task = new QihooType2KeywordTask();
		KeywordSearch360T2Request param = (KeywordSearch360T2Request) params;
		
		StringBuffer  urlBuff=new StringBuffer(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.QIHOO_TYPE2_APPSTORE_URL));
		
		urlBuff.append("?").append("from=").append(param.getFrom())
		.append("&ch=").append(param.getCh()).append("&adspaceid=").append(param.getAdspaceid())
		.append("&ip=").append(param.getIp()).append("&sdk_track=").append(param.getSdk_track())
		.append("&ts=").append(param.getTs());
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;utf-8");
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(urlBuff.toString());
		task.setHeaders(headers);
		return task;
	}
}
