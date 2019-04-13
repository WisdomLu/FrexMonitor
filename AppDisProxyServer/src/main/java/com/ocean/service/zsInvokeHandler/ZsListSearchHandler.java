package com.ocean.service.zsInvokeHandler;

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
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.zs.listsearch.ZsListSearchReply;
import com.ocean.persist.app.dis.zs.listsearch.ZsListSearchRequest;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.zsInvokeHandler.base.ZsBaseHandler;
import com.ocean.task.ZsListSearchTask;
@Component(value = "ZsListSearchHandler")
public class ZsListSearchHandler extends ZsBaseHandler {

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		ZsListSearchRequest request = parseListSearchParam(req);
		return (ZsListSearchReply) this.invoke(request, ZsListSearchReply.class,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}

	private ZsListSearchRequest parseListSearchParam(AppDisRecomReq req)
			throws AppDisException {
		ZsListSearchRequest request = new ZsListSearchRequest();
		if (CollectionUtils.isEmpty(req.getJoinParam())) {
			throw new AppDisException(
					AppDisErrorCode.RC_PARAM_ERROR,
					req.getJoinSource().name()
							+ ": please check third plat applying paramters is  empty or not");

		}
		UserInfo user = req.getUserInfo();
		DeviceInfo device = req.getDevice();
		AppInfo app=req.getAppInfo();

		if (!validate(req)) {
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,
					"parameter is Error!");
		}
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
		request.setVersion(device.getOsVerName());//操作系统版本号
		request.setVersion_int(device.getOsversion());//操作系统版本号, 采集自Build.VERSION.SDK_INT
		request.setUuid(UUID.randomUUID().toString());
		request.setApp_id(app.getPkgName());
		request.setApp_name(app.getAppName());
		request.setApp_version(app.getVersionCode());
		request.setGeo_type(1);//?1;全球卫星定位系统坐标系2;国家测绘局坐标系3;百度坐标系 optional
		request.setGeo_latitude(Float.valueOf(user.getLat()));
		request.setGeo_longitude(Float.valueOf(user.getLon()));
		request.setGeo_time(System.currentTimeMillis());//定位所在地的时间精确到毫秒  optional
//		request.setGeo_location_accuracy(1);//?经纬度精度半径，单位为米 optional
		request.setSupport_redirect(0);//?是否支持302重定向0：否，1：是
		return request;
	}


	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		ZsListSearchTask task = new ZsListSearchTask();
		ZsListSearchRequest param = (ZsListSearchRequest) params;

		StringBuffer url= new StringBuffer(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.ZS_PKG_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param));
		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);

		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		return task;
	}
}
