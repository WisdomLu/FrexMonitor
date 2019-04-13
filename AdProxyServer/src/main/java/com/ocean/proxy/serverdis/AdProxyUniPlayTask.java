package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.uniplay.UniPlayAdPullParams;
import com.ocean.persist.api.proxy.uniplay.UniPlayAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年9月7日 
      @version 1.0 
 */
public class AdProxyUniPlayTask  extends AsynAbstractTask<UniPlayAdPullParams,UniPlayAdPullResponse>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		String paramStr=JsonUtils.toJson(param);
		LOGGER.info("joinDSP:uniplay {} request param:{}",hashCode,paramStr);
		return  paramStr;
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UniPlayAdPullResponse respFormat(String result) throws Exception {
		UniPlayAdPullResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, UniPlayAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("uniplay parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public UniPlayAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
