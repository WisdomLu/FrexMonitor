package com.ocean.forex.service.dataAsyn.jinshi;
import java.util.List;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.forex.common.FrexCurrency;
import com.ocean.forex.common.MonitorConstants;
import com.ocean.forex.common.MonitorException;
import com.ocean.forex.entity.ExchangeRate;
import com.ocean.forex.service.dataAsyn.DefaultDataAsynService;
import com.ocean.forex.service.dataAsyn.IDataAsynService;
import com.ocean.forex.service.dataAsyn.jinshi.persist.JinshiReq;
import com.ocean.forex.service.dataAsyn.jinshi.persist.JinshiResp;
import com.ocean.forex.service.dataAsyn.task.JinshiTask;
@Component(value="JinshiService")
public class JinshiDSServiceImpl extends DefaultDataAsynService implements IDataAsynService {
	@Override
	public List<ExchangeRate> getExchangeRate(FrexCurrency fc) throws MonitorException {
		// TODO Auto-generated method stub
		JinshiReq req=this.parseParam(fc);
		return parseResp((JinshiResp)this.invoke(req, JinshiResp.class,fc.getName()));
		
		
	}
	private JinshiReq parseParam(FrexCurrency fc){
		JinshiReq req=new JinshiReq();
		req.setType(fc.getName());
		req.set_(System.currentTimeMillis());
		return req;
	}
	private List<ExchangeRate> parseResp(JinshiResp resp) throws MonitorException{
		if(resp==null){
			//throw new MonitorException("jinshi return is empty!");
			return null;
		}
		if(resp.getAd()!=null&&CollectionUtils.isNotEmpty(resp.getAd().getEr())){
			return resp.getAd().getEr();
		}
		return null;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		JinshiTask task = new JinshiTask();
		JinshiReq param = (JinshiReq) params;
		StringBuffer  urlBuff=new StringBuffer(SystemContext.getDynamicPropertyHandler().get(MonitorConstants.JINSHI_URL));
		urlBuff.append("?").append(Bean2Utils.toHttpParams(param));
		
		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(urlBuff.toString());
		//task.setHeaders(headers);
		return task;
	}

}
