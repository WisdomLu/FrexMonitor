package com.ocean.proxy.serverdis;


import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.ruishi.RuishiAdPullParams;
import com.ocean.persist.api.proxy.ruishi.RuishiAdPullResponse;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyRuishiTask extends AsynAbstractTask<RuishiAdPullParams,RuishiAdPullResponse>{

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
	public RuishiAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		RuishiAdPullResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, RuishiAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	@Override
	public RuishiAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub

		return null;
	}

}
