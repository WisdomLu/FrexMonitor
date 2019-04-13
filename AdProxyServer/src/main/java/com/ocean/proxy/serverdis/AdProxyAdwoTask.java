package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.adview.AdviewAdResponse;
import com.ocean.persist.api.proxy.adwo.AdwoAdPullResponse;
import com.ocean.persist.api.proxy.adwo.AdwoAdPullParams;
import com.ocean.persist.api.proxy.fmobi.FmobiAdpullParams;
import com.ocean.persist.api.proxy.fmobi.FmobiRespBody;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyAdwoTask extends AsynAbstractTask<AdwoAdPullParams,AdwoAdPullResponse>{

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
	public AdwoAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		AdwoAdPullResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				resp=JsonUtils.toBean(result, AdwoAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("adwo parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public AdwoAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}


}
