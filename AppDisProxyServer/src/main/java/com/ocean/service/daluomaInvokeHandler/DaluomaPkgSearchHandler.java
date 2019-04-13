package com.ocean.service.daluomaInvokeHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
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
import com.ocean.persist.app.dis.daluoma.pkgSearch.DaluomaPkgSearchReq;
import com.ocean.persist.app.dis.daluoma.pkgSearch.DaluomaPkgSearchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.qihooInvokeHandler.base.QihooBaseHandler;
import com.ocean.task.DaluomaPkgTask;
@Component(value="DaluomaPkgSearchHandler")
public class DaluomaPkgSearchHandler extends QihooBaseHandler{
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		DaluomaPkgSearchReq request=parseKeyWordSearchParam(req);
		//return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		return (DaluomaPkgSearchResp)this.invoke(request, DaluomaPkgSearchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
		
	}
	private DaluomaPkgSearchReq parseKeyWordSearchParam(AppDisRecomReq req) throws AppDisException{
		DaluomaPkgSearchReq request=new DaluomaPkgSearchReq();
		if(CollectionUtils.isEmpty(req.getJoinParam())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,req.getJoinSource().name()+": please check third plat applying paramters is  empty or not");
			
		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();

		if(!validate(req)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"parameter is error!");
		}
		for(int i=0;i<req.getJoinParam().size();i++){
			ExtData ext=req.getJoinParam().get(i);
			if(i==0){
				request.setOrganization(Integer.parseInt(ext.getValue()));
			}

		}

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
		
		if(StringUtils.isNotEmpty(device.getOsVerName())){
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

		//动态参数
		request.setSo(1);
		Integer net=qihooCtype.get(user.getNet());
		request.setNet(net==null?5:net);
		
		InterParam interParam=req.getInterParam();

		String pkgName=interParam.getPkgName();
		if(StringUtil.isEmpty(pkgName)){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"pkg search api parameter is empty!");
			
		}
		String[] pnArr=pkgName.split(",");
		request.setPnames(Arrays.asList(pnArr));
		request.setUa(user.getUa());
		return request;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		DaluomaPkgTask task = new DaluomaPkgTask();
		DaluomaPkgSearchReq param = (DaluomaPkgSearchReq) params;
		
		StringBuffer  urlBuff=new StringBuffer(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.DALUOMA_PKG_URL));
		urlBuff.append("?").append("organization=").append(param.getOrganization())
		.append("&ip=").append(param.getIp());
		
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
