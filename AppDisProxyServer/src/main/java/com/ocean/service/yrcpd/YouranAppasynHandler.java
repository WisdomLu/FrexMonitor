package com.ocean.service.yrcpd;

import java.util.List;

import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.yrcpd.appasyn.YouranAppasynApp;
import com.ocean.persist.app.dis.yrcpd.appasyn.YouranAppasynReq;
import com.ocean.persist.app.dis.yrcpd.appasyn.YouranAppasynResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.YouranAppasynTask;

@Component(value="YouranAppasynHandler")
public class YouranAppasynHandler   extends BaseHandler{

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		YouranAppasynReq request=parseAppasynParam(req);
		return (YouranAppasynResp)this.invoke(request, YouranAppasynResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}

	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	public YouranAppasynReq parseAppasynParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		YouranAppasynReq param=new YouranAppasynReq();
		param.setApp_id(req.getJoinParam().get(0).getValue());
		param.setHz_id(req.getJoinParam().get(1).getValue());
		return param;
		
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		YouranAppasynTask task = new YouranAppasynTask();
		YouranAppasynReq param = (YouranAppasynReq) params;
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YOURAN_LIST_ASYN_URL));
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
