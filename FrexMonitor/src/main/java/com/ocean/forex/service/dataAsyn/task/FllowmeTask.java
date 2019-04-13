package com.ocean.forex.service.dataAsyn.task;
import org.apache.commons.lang3.StringUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.forex.service.dataAsyn.fallowme.persist.FallowmeReq;
import com.ocean.forex.service.dataAsyn.fallowme.persist.FallowmeResp;

public class FllowmeTask  extends AsynAbstractTask<FallowmeReq,FallowmeResp>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
	
		return Bean2Utils.toHttpParams(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FallowmeResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		FallowmeResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				
				resp=JsonUtils.toBean(result, FallowmeResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	
	@Override
	public FallowmeResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
