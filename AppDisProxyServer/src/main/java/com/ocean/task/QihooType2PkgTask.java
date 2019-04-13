package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.appSearch360T2.pkgSearch.PkgSearch360T2Req;
import com.ocean.persist.app.dis.appSearch360T2.pkgSearch.PkgSearch360T2Resp;


public class QihooType2PkgTask  extends AsynAbstractTask<PkgSearch360T2Req,PkgSearch360T2Resp>{

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
	public PkgSearch360T2Resp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		PkgSearch360T2Resp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, PkgSearch360T2Resp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public PkgSearch360T2Resp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
