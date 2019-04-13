package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.flying.FlyingAdPullParams;
import com.ocean.persist.api.proxy.flying.FlyingAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月2日 
      @version 1.0 
 */
public class AdProxyFlyingTask  extends AsynAbstractTask<FlyingAdPullParams,FlyingAdPullResponse>{

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
	public FlyingAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		FlyingAdPullResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, FlyingAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("flying {} parse response data error(Exception),msg:{}",this.hashCode,e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public FlyingAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
