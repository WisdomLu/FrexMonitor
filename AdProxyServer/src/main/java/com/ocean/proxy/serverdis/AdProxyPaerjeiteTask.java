package com.ocean.proxy.serverdis;


import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.paerjiate.PaerjiateAdPullParams;
import com.ocean.persist.api.proxy.paerjiate.PaerjiateAdPullResponse;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyPaerjeiteTask extends AsynAbstractTask<PaerjiateAdPullParams,PaerjiateAdPullResponse>{

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
	public PaerjiateAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		PaerjiateAdPullResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, PaerjiateAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	@Override
	public PaerjiateAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub

		return null;
	}

}
