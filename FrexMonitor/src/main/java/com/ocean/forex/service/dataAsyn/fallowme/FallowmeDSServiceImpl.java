package com.ocean.forex.service.dataAsyn.fallowme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
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
import com.ocean.forex.service.dataAsyn.fallowme.persist.FallowmeData;
import com.ocean.forex.service.dataAsyn.fallowme.persist.FallowmeReq;
import com.ocean.forex.service.dataAsyn.fallowme.persist.FallowmeResp;
import com.ocean.forex.service.dataAsyn.task.FllowmeTask;
@Component(value="FallowmeDSServiceImpl")
public class FallowmeDSServiceImpl extends DefaultDataAsynService implements IDataAsynService {
	private static final String FM_OK="ok";
	@Override
	public List<ExchangeRate> getExchangeRate(FrexCurrency fc)
			throws MonitorException {
		// TODO Auto-generated method stub
		FallowmeReq req=this.parseParam(fc);
		return parseResp((FallowmeResp)this.invoke(req, FallowmeResp.class,fc.getName()));
		
	}
	private FallowmeReq parseParam(FrexCurrency fc){
		FallowmeReq req=new FallowmeReq();
		req.setResolution(1);
		req.setSymbol(fc.getFmName());
		int to=(int)(System.currentTimeMillis()/1000);
		req.setTo(to);
		req.setFrom(to-1000*60*10);
		req.setBrokerId(6);
		return req;
	}
	private List<ExchangeRate> parseResp(FallowmeResp resp) throws MonitorException{
		if(resp==null){
			//throw new MonitorException("fallowme return is empty!");
			return null;
		}
		if(resp.getData()!=null&&!FM_OK.equals(resp.getData().getSt())){
			return null;
		}
		FallowmeData data=resp.getData();
		int size=data.getC().size();
		List<ExchangeRate> list=new ArrayList<ExchangeRate>(size);
		for(int i=0;i<size;i++){
			ExchangeRate er=new ExchangeRate();
			er.setTimestamp(data.getT().get(i));
			er.setPrivce(data.getC().get(i));
			list.add(er);
		}
		
		return list;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		FllowmeTask task = new FllowmeTask();
		FallowmeReq param = (FallowmeReq) params;
		StringBuffer  urlBuff=new StringBuffer(SystemContext.getDynamicPropertyHandler().get(MonitorConstants.FALLOWME_URL));
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
