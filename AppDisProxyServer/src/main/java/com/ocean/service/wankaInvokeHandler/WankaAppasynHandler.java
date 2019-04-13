package com.ocean.service.wankaInvokeHandler;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.ocean.persist.app.dis.wanka.appasyn.WankaAppAsynReq;
import com.ocean.persist.app.dis.wanka.appasyn.WankaAsynResponse;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.WankaAppasynTask;
@Component(value="WankaAppasynHandler")
public class WankaAppasynHandler   extends BaseHandler{

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		WankaAppAsynReq request=parseAppasynParam(req);
		return (WankaAsynResponse)this.invoke(request, WankaAsynResponse.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}

	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	public static WankaAppAsynReq parseAppasynParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<3){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		WankaAppAsynReq param=new WankaAppAsynReq();
		param.setFrom_client("server");
		param.setApp_id(req.getJoinParam().get(0).getValue());
		param.setChannel_id(req.getJoinParam().get(1).getValue());
		param.setPn(1);
		if(req.isSetPage()){
			param.setRn(req.getPage().getPageSize());
		}else{
			param.setRn(pageSize);//默认请求10个
		}

		param.setTimestamp(String.valueOf(System.currentTimeMillis()/1000));
		StringBuffer sb=new StringBuffer();
		sb.append("app_id=").append(param.getApp_id()).append("&channel_id=").append(param.getChannel_id())
		.append("&from_client=").append(param.getFrom_client()).append("&pn=").append(param.getPn()).append("&rn=").append(param.getRn()).append("&timestamp=").append(param.getTimestamp())
		.append(req.getJoinParam().get(2).getValue());
		param.setSign(DigestUtils.md5Hex(sb.toString()).toLowerCase());
		return param;
		
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		WankaAppasynTask task = new WankaAppasynTask();
		WankaAppAsynReq param = (WankaAppAsynReq) params;
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.WANGKA_APP_ASYN_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);

		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		return task;
	}
}
