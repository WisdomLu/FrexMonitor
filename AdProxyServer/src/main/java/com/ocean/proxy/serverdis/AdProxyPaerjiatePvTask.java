package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.paerjiate_t2.ParerjiateT2AdPullParams;
import com.ocean.persist.api.proxy.paerjiate_t2.ParerjiateT2AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyPaerjiatePvTask extends AsynAbstractTask<ParerjiateT2AdPullParams,ParerjiateT2AdPullResponse>{

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
	public ParerjiateT2AdPullResponse respFormat(String result) throws Exception {
		ParerjiateT2AdPullResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, ParerjiateT2AdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("firebird parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();

	}

	@Override
	public ParerjiateT2AdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}


}
