package com.ocean.proxy.serverdis;


import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.boyu.BoyuAdPullParams;
import com.ocean.persist.api.proxy.boyu.BoyuAdPullResponse;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyBoyuTask extends AsynAbstractTask<BoyuAdPullParams,BoyuAdPullResponse>{

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
	public BoyuAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		BoyuAdPullResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, BoyuAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	@Override
	public BoyuAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub

		return null;
	}

}
