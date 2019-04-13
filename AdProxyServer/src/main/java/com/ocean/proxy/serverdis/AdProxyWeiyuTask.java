package com.ocean.proxy.serverdis;
import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.weiyu.WeiyuAdPullParams;
import com.ocean.persist.api.proxy.weiyu.WeiyuAdResponse;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class AdProxyWeiyuTask extends AsynAbstractTask<WeiyuAdPullParams,WeiyuAdResponse>{

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
	public WeiyuAdResponse respFormat(String result) {
		// TODO Auto-generated method stub
		WeiyuAdResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				resp=JsonUtils.toBean(result, WeiyuAdResponse.class);
			}
			
		}catch (Throwable e) {
			LOGGER.error("{} parse response data error(Exception),msg:{}",this.getClass(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public WeiyuAdResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}
}
