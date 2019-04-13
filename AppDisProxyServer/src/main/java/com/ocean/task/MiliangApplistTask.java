package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.miliang.listSearch.MiliangListSchReq;
import com.ocean.persist.app.dis.miliang.listSearch.MiliangListSchResp;



public class MiliangApplistTask  extends AsynAbstractTask<MiliangListSchReq,MiliangListSchResp>{

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
	public MiliangListSchResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		MiliangListSchResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, MiliangListSchResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public MiliangListSchResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
