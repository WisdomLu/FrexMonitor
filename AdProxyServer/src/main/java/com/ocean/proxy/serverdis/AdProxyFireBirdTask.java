package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.firebird.FireBirdAdPullParams;
import com.ocean.persist.api.proxy.firebird.FireBirdAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyFireBirdTask extends AsynAbstractTask<FireBirdAdPullParams,FireBirdAdPullResponse>{

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
	public FireBirdAdPullResponse respFormat(String result) throws Exception {
		FireBirdAdPullResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, FireBirdAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("firebird parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();

	}

	@Override
	public FireBirdAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}


}
