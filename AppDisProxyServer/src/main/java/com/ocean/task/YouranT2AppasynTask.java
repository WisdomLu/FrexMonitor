package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.yrcpd_t2.appasyn.YouranT2AppasynReq;
import com.ocean.persist.app.dis.yrcpd_t2.appasyn.YouranT2AppasynResp;


public class YouranT2AppasynTask  extends AsynAbstractTask<YouranT2AppasynReq,YouranT2AppasynResp>{

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
	public YouranT2AppasynResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		YouranT2AppasynResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, YouranT2AppasynResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public YouranT2AppasynResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
