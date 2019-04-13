package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.yitong.pkgSearch.YitongPkgSchReq;
import com.ocean.persist.app.dis.yitong.pkgSearch.YitongPkgSchResp;
public class YitongPkgTask  extends AsynAbstractTask<YitongPkgSchReq,YitongPkgSchResp>{

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
	public YitongPkgSchResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		YitongPkgSchResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, YitongPkgSchResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public YitongPkgSchResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
