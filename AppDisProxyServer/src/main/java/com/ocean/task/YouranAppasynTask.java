package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.yrcpd.appasyn.YouranAppasynReq;
import com.ocean.persist.app.dis.yrcpd.appasyn.YouranAppasynResp;


public class YouranAppasynTask  extends AsynAbstractTask<YouranAppasynReq,YouranAppasynResp>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		return JsonUtils.toJson(param);
		
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YouranAppasynResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		YouranAppasynResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, YouranAppasynResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public YouranAppasynResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
