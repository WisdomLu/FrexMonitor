package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.appSearch360T2.appRcmdSearch.AppRcmd360T2Request;
import com.ocean.persist.app.dis.appSearch360T2.appRcmdSearch.AppRcmd360T2Response;


public class QihooType2RecmdTask  extends AsynAbstractTask<AppRcmd360T2Request,AppRcmd360T2Response>{

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
	public AppRcmd360T2Response respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		AppRcmd360T2Response resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, AppRcmd360T2Response.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public AppRcmd360T2Response respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
