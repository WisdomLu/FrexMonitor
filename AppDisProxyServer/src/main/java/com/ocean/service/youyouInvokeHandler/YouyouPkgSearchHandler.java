package com.ocean.service.youyouInvokeHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
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
import com.ocean.persist.app.dis.youyou.pkgSearch.YouyouPkgSearchRequest;
import com.ocean.persist.app.dis.youyou.pkgSearch.YouyouPkgSearchRespose;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.youyouInvokeHandler.base.YouyouBaseHandler;
import com.ocean.task.YouyouPkgSearchTask;
@Component
public class YouyouPkgSearchHandler extends YouyouBaseHandler {


	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		YouyouPkgSearchRequest request = parsePkgSearchParam(req);
		return (YouyouPkgSearchRespose) this.invoke(request, YouyouPkgSearchRespose.class,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
	}

	private YouyouPkgSearchRequest parsePkgSearchParam(AppDisRecomReq req)
			throws AppDisException {
		YouyouPkgSearchRequest request = new YouyouPkgSearchRequest();
		if (CollectionUtils.isEmpty(req.getJoinParam())) {
			throw new AppDisException(
					AppDisErrorCode.RC_PARAM_ERROR,
					req.getJoinSource().name()
							+ ": please check third plat applying paramters is  empty or not");

		}
		UserInfo user = req.getUserInfo();
		DeviceInfo device = req.getDevice();

		if (!validate(req)) {
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,
					"parameter is Error!");
		}
		
		request.setToken(req.getJoinParam().get(0).getValue());
		long time=System.currentTimeMillis()/1000;
		request.setTimestamp(time);
		Random r = new Random();
		request.setNonce(Long.toString(r.nextInt(1000000)));
		
		StringBuilder str=new StringBuilder();
		str.append("timestamp=").append(request.getTimestamp()).append("&token=").append(request.getToken()).append("&").append(req.getJoinParam().get(1).getValue());
		request.setSign((DigestUtils.md5Hex(str.toString())).toUpperCase());
		
		request.setIp(user.getClientIp());
		request.setMd(device.getPhonemodel());
		request.setImei(device.getImei());
		request.setAndroidid(device.getAdid());//
		if (StringUtils.isNotEmpty(device.getSerialNo())) {
			request.setSerialno(device.getSerialNo());
		} else {
			request.setSerialno(device.getImsi());
		}

		request.setBr(device.getBrandName());
		if (StringUtils.isNotEmpty(device.getOsversion())) {
			request.setOs(Integer.parseInt(device.getOsversion()));
		}

		if (StringUtils.isEmpty(device.getOsVerName())
				&& StringUtils.isNotEmpty(device.getOsversion())) {
			request.setOsv(this.convOSVersion(device.getOsversion()));
		} else if (StringUtils.isNotEmpty(device.getOsVerName())) {
			request.setOsv(device.getOsVerName());
		}

		String cat = youyouMobiles.get(device.getMobile());
		if (StringUtils.isNotEmpty(cat)) {
			request.setCarrier(Integer.parseInt(cat));
		} else {
			request.setCarrier(Integer.parseInt("0"));
		}

		request.setSw(device.getDeviceWidth());
		request.setSh(device.getDeviceHeight());
		request.setDip(Double.parseDouble(device.getDip()));
		request.setSo(1);

		Integer net = youyouCtype.get(user.getNet());
		request.setNet(net == null ? 5 : net);

		request.setUa(user.getUa());
		request.setMac(device.getMac());

		// 动态参数
		Vector<String> appList = new Vector<String>();
		appList.add(req.getInterParam().getPkgName());
		request.setAppList(appList);
		return request;
	}


	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		YouyouPkgSearchTask task = new YouyouPkgSearchTask();
		YouyouPkgSearchRequest param = (YouyouPkgSearchRequest) params;

		StringBuffer urlBuff = new StringBuffer(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YOUYOU_PKG_URL));
		urlBuff.append("?token=").append(param.getToken()).append("&timestamp=").append(param.getTimestamp())
		.append("&nonce=").append(param.getNonce()).append("&sign=").append(param.getSign());
		
		param.setTimestamp(null);
		param.setToken(null);
		param.setNonce(null);
		param.setSign(null);
		
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
