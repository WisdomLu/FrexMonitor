package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.yrcpd_t2.pkgsearch.YouranT2PkgSearchReq;
import com.ocean.persist.app.dis.yrcpd_t2.pkgsearch.YouranT2PkgSearchResp;


public class YouranT2PkgTask  extends AsynAbstractTask<YouranT2PkgSearchReq,YouranT2PkgSearchResp>{

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
	public YouranT2PkgSearchResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		YouranT2PkgSearchResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, YouranT2PkgSearchResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public YouranT2PkgSearchResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
