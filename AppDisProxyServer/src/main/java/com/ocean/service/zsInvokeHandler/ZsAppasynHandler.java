package com.ocean.service.zsInvokeHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
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
import com.ocean.persist.app.dis.zs.appasyn.ZsAppasynReply;
import com.ocean.persist.app.dis.zs.appasyn.ZsAppasynRequest;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.zsInvokeHandler.base.ZsBaseHandler;
import com.ocean.task.ZsAppasynTask;

@Component(value = "ZsT2AppasynHandler")
public class ZsAppasynHandler extends ZsBaseHandler {
	
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		ZsAppasynRequest request = parseAppasynParam(req);
		return (ZsAppasynReply) this.invoke(request, ZsAppasynReply.class,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}

	@Override
	public boolean validate(AppDisRecomReq req) {
		return false;
	}

	public ZsAppasynRequest parseAppasynParam(AppDisRecomReq req)
			throws AppDisException {
		ZsAppasynRequest request = new ZsAppasynRequest();
		if (CollectionUtils.isEmpty(req.getJoinParam())
				|| req.getJoinParam().size() < 2) {
			throw new AppDisException(ErrorCode.PARAM_ERROR,
					"get join params empty!");

		}
		UserInfo user = req.getUserInfo();
		DeviceInfo device = req.getDevice();
		AppInfo app=req.getAppInfo();

		/*if (!validate(req)) {
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,
					"parameter is Error!");
		}*/
		String id=(req.getJoinParam().get(0).getValue());
		String key=(req.getJoinParam().get(1).getValue());
		long timestamp=System.currentTimeMillis();
		
		request.setId(id);
		request.setTimestamp(System.currentTimeMillis());
		
		StringBuilder str=new StringBuilder();
		str.append(id).append(key).append(timestamp);
		request.setToken(((DigestUtils.md5Hex(str.toString()))));
		
		request.setIp(user.getClientIp());
		
		String cat = zSMobiles.get(device.getMobile());
		if (StringUtils.isNotEmpty(cat)) {
			request.setCarrior(Integer.parseInt(cat));
		} else {
			request.setCarrior(Integer.parseInt("0"));
		}
	
		Integer net = zSCtype.get(user.getNet());
		request.setNetwork_type((net == null ? 0 :net));
		
		request.setImei(device.getImei());
		request.setMac(converMac(device.getMac()));
		request.setImsi(device.getImsi());
		if (StringUtils.isNotEmpty(device.getSerialNo())) {
			request.setSerial_no(device.getSerialNo());
		} else {
			request.setSerial_no(device.getImsi());
		}

		request.setAndroid_id(device.getAdid());
		request.setVendor(device.getBrandName());//手机产商 ok
		request.setBrand(device.getBrandName());//手机品牌?
		request.setModel(device.getPhonemodel());//型号 ok
		request.setDevice_type(1);//?设备类型---0:未知; 1:手机; 2:平板; 3:智能TV
		request.setDpi(device.getDpi());
		request.setScreen_size(device.getDeviceHeight()+"x"+device.getDeviceWidth());
		request.setOrientation(1);
		request.setUa(user.getUa());
		request.setVersion(device.getOsVerName());//?操作系统版本号
		request.setVersion_int(device.getOsversion());//?操作系统版本号, 采集自Build.VERSION.SDK_INT
		request.setUuid(UUID.randomUUID().toString());
		request.setApp_id(app.getPkgName());
		request.setApp_name(app.getAppName());
		request.setApp_version(app.getVersionCode());
		request.setGeo_type(1);//?1;全球卫星定位系统坐标系2;国家测绘局坐标系3;百度坐标系 optional
		request.setGeo_latitude(Float.valueOf(user.getLat()));
		request.setGeo_longitude(Float.valueOf(user.getLon()));
		request.setGeo_time(System.currentTimeMillis());
//		request.setGeo_location_accuracy(1);//?经纬度精度半径，单位为米 optional
		request.setSupport_redirect(0);//?是否支持302重定向0：否，1：是
		return request;

	}

	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		ZsAppasynTask task = new ZsAppasynTask();
		ZsAppasynRequest param = (ZsAppasynRequest) params;

		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(
				AppDisConstants.ZS_ASYN_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		// task.setHeaders(headers);
		return task;
	}
}
