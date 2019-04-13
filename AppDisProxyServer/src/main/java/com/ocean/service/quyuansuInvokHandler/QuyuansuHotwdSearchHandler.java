package com.ocean.service.quyuansuInvokHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
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
import com.ocean.persist.app.dis.quyuansu.hotwordSearch.QuyuansuHotwdSchReq;
import com.ocean.persist.app.dis.quyuansu.hotwordSearch.QuyuansuHotwdSchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.quyuansuInvokHandler.base.QuyuansuBaseHandler;
import com.ocean.task.QuyuansuHotwdTask;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="QuyuansuHotwdSearchHandler")
public class QuyuansuHotwdSearchHandler   extends QuyuansuBaseHandler{
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		QuyuansuHotwdSchReq request=parseHotwordSearchParam(req);
		return (QuyuansuHotwdSchResp)this.invoke(request, QuyuansuHotwdSchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	public AppDisResponse invok(AppDisRecomReq proxyReq,QuyuansuHotwdSchReq req) throws AppDisException {
		// TODO Auto-generated method stub
		return (QuyuansuHotwdSchResp)this.invoke(req, QuyuansuHotwdSchResp.class, proxyReq.getUserInfo().getUid(),String.valueOf(proxyReq.getAppSpaceInfo().getAdSpaceId()));
		
	}
	public QuyuansuHotwdSchReq parseHotwordSearchParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		String valStr=strValidate(req);
		if(!VALIDATE_OK.equals(valStr)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,valStr+" parameter is Empty!");
		}


		QuyuansuHotwdSchReq param =new QuyuansuHotwdSchReq();
		param.setId(req.getJoinParam().get(0).getValue());
		param.setTimestamp(System.currentTimeMillis());
		param.setApiToken(DigestUtils.md5Hex(param.getId()+req.getJoinParam().get(1).getValue()+param.getTimestamp()));
		//param.setApiToken(req.getJoinParam().get(1).getValue());
		
		param.setVersionName(req.getAppInfo().getVersionName());
		param.setVersionCode(Integer.parseInt(req.getAppInfo().getVersionCode()));
		param.setIp(user.getClientIp());
		param.setMac(device.getMac());
		param.setImei(device.getImei());
		param.setImsi(device.getImsi());
		try {
			param.setModel(URLEncoder.encode(device.getBrandName()+"_"+device.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setManufacture(device.getBrandName());
		param.setApi_level(device.getOsversion());
		param.setOsv(device.getOsVerName());
		param.setAndroidId(device.getAdid());
		if(StringUtils.isEmpty(device.getSerialNo())){
			param.setSerialno(device.getImei());
		}else{
			param.setSerialno(device.getSerialNo());
		}
		param.setSw(device.getDeviceWidth());
		param.setSh(device.getDeviceHeight());
		param.setDip(Double.parseDouble(device.getDip()));
		param.setSo(1);
		param.setNet(getNetwork(user.getNet()));
		try {
			param.setUa(URLEncoder.encode(user.getUa(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InterParam interParam=req.getInterParam();
		if(StringUtil.isEmpty(interParam.getKeyWord())&&StringUtils.isEmpty(interParam.getHotWord())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"key word search parameter is empty!");
		}
		String keyWord=interParam.getHotWord();
		if(StringUtil.isEmpty(keyWord)){
			keyWord=interParam.getKeyWord();
		}
		param.setTag(keyWord);
		
		if(req.isSetPage()){
			param.setNum(req.getPage().getPageSize());
		}else{
			param.setNum(pageSize);//默认请求10个
		}
		param.setPage(1);
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
		return 5;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		QuyuansuHotwdTask task = new QuyuansuHotwdTask();
		QuyuansuHotwdSchReq param = (QuyuansuHotwdSchReq) params;
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.QUYUANSU_HOTWD_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		//task.setHeaders(headers);
		return task;
	}
}
