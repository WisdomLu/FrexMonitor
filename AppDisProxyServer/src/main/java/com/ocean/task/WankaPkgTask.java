package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.wanka.WankaAppReqBase;
import com.ocean.persist.app.dis.wanka.pkgsearch.WankaPkgResponse;


public class WankaPkgTask  extends AsynAbstractTask<WankaAppReqBase,WankaPkgResponse>{

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
	public WankaPkgResponse respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		WankaPkgResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(check(result,1), WankaPkgResponse.class);
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
	public WankaPkgResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
