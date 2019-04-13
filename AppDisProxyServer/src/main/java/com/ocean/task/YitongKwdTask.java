package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.yitong.kwd.KwdSearchYitongReq;
import com.ocean.persist.app.dis.yitong.kwd.KwdSearchYitongResp;
public class YitongKwdTask  extends AsynAbstractTask<KwdSearchYitongReq,KwdSearchYitongResp>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		//LOGGER.info("{} {} befor parse to http params data:{}",this.getClass().getName(),this.getHashCode(),JsonUtils.toJson(param));
		
		return Bean2Utils.toHttpParams(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KwdSearchYitongResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		KwdSearchYitongResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, KwdSearchYitongResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public KwdSearchYitongResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
