package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.daluoma.keywordSearch.DaluomaKeywordReq;
import com.ocean.persist.app.dis.daluoma.keywordSearch.DaluomaKeywordResp;

public class DaluomaKeywordTask  extends AsynAbstractTask<DaluomaKeywordReq,DaluomaKeywordResp>{

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
	public DaluomaKeywordResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		DaluomaKeywordResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, DaluomaKeywordResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public DaluomaKeywordResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
