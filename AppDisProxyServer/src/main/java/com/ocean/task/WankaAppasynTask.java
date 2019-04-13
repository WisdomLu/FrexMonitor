package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.wanka.WankaAppReqBase;
import com.ocean.persist.app.dis.wanka.appasyn.WankaAsynResponse;


public class WankaAppasynTask  extends AsynAbstractTask<WankaAppReqBase,WankaAsynResponse>{

	@Override
	public String reqFormat() {
		return check(JsonUtils.toJson(param),0);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WankaAsynResponse respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		WankaAsynResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(check(result,1), WankaAsynResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	private String check(String str,int type){
		if(type==1){
			return str.replaceAll("package", "_package");
		}else{
			return str.replaceAll("_package","package");
		}
		
	}
	@Override
	public WankaAsynResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
