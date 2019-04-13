package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.daluoma.pkgSearch.DaluomaPkgSearchReq;
import com.ocean.persist.app.dis.daluoma.pkgSearch.DaluomaPkgSearchResp;


public class DaluomaPkgTask  extends AsynAbstractTask<DaluomaPkgSearchReq,DaluomaPkgSearchResp>{

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
	public DaluomaPkgSearchResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		DaluomaPkgSearchResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, DaluomaPkgSearchResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public DaluomaPkgSearchResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
