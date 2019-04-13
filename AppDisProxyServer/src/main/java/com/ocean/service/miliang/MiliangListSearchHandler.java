package com.ocean.service.miliang;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;
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
import com.ocean.persist.app.dis.miliang.listSearch.MiliangListSchReq;
import com.ocean.persist.app.dis.miliang.listSearch.MiliangListSchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.MiliangApplistTask;

@Component(value="MiliangListSearchHandler")
public class MiliangListSearchHandler extends BaseHandler{

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		MiliangListSchReq request=this.parseListSearchParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,MiliangListSchReq request) throws AppDisException {
		// TODO Auto-generated method stub
		return (MiliangListSchResp)this.invoke(request, MiliangListSchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
    public MiliangListSchReq parseListSearchParam(AppDisRecomReq req)throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		MiliangListSchReq param=new MiliangListSchReq();
		param.setSid(Integer.parseInt(req.getJoinParam().get(0).getValue()));
		param.setAid(Integer.parseInt(req.getJoinParam().get(1).getValue()));
		param.setImsi(device.getImsi());
		param.setMac(device.getMac());
		param.setAdrid(device.getAdid());
		param.setOpid(getOp(device.getMobile()));
		param.setImei(device.getImei());
		param.setOsv(device.getOsVerName());
		try {
			param.setDv(URLEncoder.encode(device.getBrandName(), CHARSET_CODER));
			param.setDm(URLEncoder.encode(device.getPhonemodel(), CHARSET_CODER));
			param.setUseragent(URLEncoder.encode(user.getUa(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setNt(getNetwork(user.getNet()));
		param.setSw(device.getDeviceWidth());
		param.setSw(device.getDeviceHeight());
		param.setW(req.getAppSpaceInfo().getSpaceWidth());
		param.setH(req.getAppSpaceInfo().getSpaceHeight());
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			param.setLat(Double.parseDouble(user.getLat()));
			param.setLon(Double.parseDouble(user.getLon()));
		}
		param.setIp(user.getClientIp());
		param.setDensity(device.getDip());
		param.setDpi(device.getDpi());
		param.setTm(System.currentTimeMillis());
		try{
			param.setOsapilevel(Integer.parseInt(device.getOsversion()));
		}catch(Exception e){
			
		}
		param.setSn(device.getSerialNo());
		param.setLac(String.valueOf(user.getLac()));
		param.setCellularid(String.valueOf(user.getCid()));
    	return param;
    }


	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 2;
		}else if(NETWORK_2G.equals(net)){
			return 4;
		}
		else if(NETWORK_3G.equals(net)){
			return 5;
		}
		else if(NETWORK_4G.equals(net)){
			return 6;
		}
		return 0;
	}
	private String  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return "46000";
		}else if(MOBILE_CUCC.equals(mobile)){
			return "46001";
		}else if(MOBILE_CTCC.equals(mobile)){
			return "46003";
		}
		return "";
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		MiliangApplistTask task = new MiliangApplistTask();
		MiliangListSchReq param = (MiliangListSchReq) params;
		
	
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.MILIANG_LIST_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}
}
