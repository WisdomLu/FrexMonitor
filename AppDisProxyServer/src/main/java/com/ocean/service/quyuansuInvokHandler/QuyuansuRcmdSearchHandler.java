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
import com.ocean.persist.app.dis.quyuansu.rcmdSearch.RcmdSearchQuyuansuReq;
import com.ocean.persist.app.dis.quyuansu.rcmdSearch.RcmdSearchQuyuansuResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.quyuansuInvokHandler.base.QuyuansuBaseHandler;
import com.ocean.task.QuyuansuRcmdTask;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="QuyuansuRcmdSearchHandler")
public class QuyuansuRcmdSearchHandler   extends QuyuansuBaseHandler{
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		RcmdSearchQuyuansuReq request=parseRcmdSearchParam(req);
		//return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());
		return (RcmdSearchQuyuansuResp)this.invoke(request, RcmdSearchQuyuansuResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	public AppDisResponse invok(AppDisRecomReq proxyReq,RcmdSearchQuyuansuReq req) throws AppDisException {
		// TODO Auto-generated method stub
		//return puller.api(req,proxyReq.getUserInfo().getUid(),String.valueOf(proxyReq.getAppSpaceInfo().getAdSpaceId()),proxyReq.getJoinSource().toString());
		return (RcmdSearchQuyuansuResp)this.invoke(req, RcmdSearchQuyuansuResp.class, proxyReq.getUserInfo().getUid(),String.valueOf(proxyReq.getAppSpaceInfo().getAdSpaceId()));
		
	}
	public RcmdSearchQuyuansuReq parseRcmdSearchParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		String valStr=strValidate(req);
		if(!VALIDATE_OK.equals(valStr)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,valStr+" parameter is Empty!");
		}


		RcmdSearchQuyuansuReq param =new RcmdSearchQuyuansuReq();
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
		if(user.isSetLac()&&user.isSetCid()){
			param.setInfo_la(user.getLac());
			param.setInfo_ci(user.getCid());
		}
		if(req.isSetPage()){
			param.setNum(req.getPage().getPageSize());
		}else{
			param.setNum(pageSize);//默认请求10个
		}
		String pkgName=req.getInterParam().getPkgName();
		InterParam interParam=req.getInterParam();
		if(StringUtils.isNotEmpty(pkgName)){
			param.setRecom_apppkg(pkgName);//多个用逗号隔开

		}else if(StringUtil.isNotEmpty(interParam.getKeyWord())||StringUtils.isNotEmpty(interParam.getHotWord())){
			String keyWord=StringUtil.isNotEmpty(interParam.getKeyWord())?interParam.getKeyWord():interParam.getHotWord();
			param.setRecom_appname(keyWord);
		}
	
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
		QuyuansuRcmdTask task = new QuyuansuRcmdTask();
		RcmdSearchQuyuansuReq param = (RcmdSearchQuyuansuReq) params;
		
		StringBuilder url = new StringBuilder();
	     url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.QUYUANSU_RCMD_URL))
		.append("?").append(Bean2Utils.toHttpParams(params));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		//task.setHeaders(headers);
		return task;
	}
}
