package com.ocean.service.haiqibingInvokeHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.haiqibing.appAsyn.HaiqibingAppasynReq;
import com.ocean.persist.app.dis.haiqibing.appAsyn.HaiqibingAppasynResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.HaiqibingAppasynTask;

@Component(value="HaiqibingAppasynHandler")
public class HaiqibingAppasynHandler   extends BaseHandler{

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		HaiqibingAppasynReq request=parseAppasynParam(req);
		return (HaiqibingAppasynResp)this.invoke(request, HaiqibingAppasynResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}

	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	public HaiqibingAppasynReq parseAppasynParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<1){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		HaiqibingAppasynReq param=new HaiqibingAppasynReq();
		param.setChannel(req.getJoinParam().get(0).getValue());
		return param;
		
	}

	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		HaiqibingAppasynTask task = new HaiqibingAppasynTask();
		HaiqibingAppasynReq param = (HaiqibingAppasynReq) params;
		
		String url =SystemContext.getDynamicPropertyHandler().get(AppDisConstants.HAIQIBING_APPASYN_URL);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url);
		task.setHeaders(headers);
		return task;
	}
}
