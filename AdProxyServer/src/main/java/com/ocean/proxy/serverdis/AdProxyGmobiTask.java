package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.gmobi.GmobiAdPullParams;
import com.ocean.persist.api.proxy.gmobi.GmobiAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月16日 
      @version 1.0 
 */
public class AdProxyGmobiTask extends AsynAbstractTask<GmobiAdPullParams,GmobiAdPullResponse>{

	@Override
	public GmobiAdPullParams getParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParam(GmobiAdPullParams param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		return  JsonUtils.toJson(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GmobiAdPullResponse respFormat(String result) throws Exception {

		GmobiAdPullResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, GmobiAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("gmobi parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
		

	}

	@Override
	public GmobiAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
