package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.quyuansu.randomSearch.RandomSearchQuyuansuReq;
import com.ocean.persist.app.dis.quyuansu.randomSearch.RandomSearchQuyuansuResp;
import com.ocean.persist.app.dis.quyuansu.rcmdSearch.RcmdSearchQuyuansuReq;
import com.ocean.persist.app.dis.quyuansu.rcmdSearch.RcmdSearchQuyuansuResp;


public class QuyuansuRandomTask  extends AsynAbstractTask<RandomSearchQuyuansuReq,RandomSearchQuyuansuResp>{

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
	public RandomSearchQuyuansuResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		RandomSearchQuyuansuResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, RandomSearchQuyuansuResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public RandomSearchQuyuansuResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
