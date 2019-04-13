package com.ocean.proxy.serverdis;
import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.adview.AdviewAdPullParams;
import com.ocean.persist.api.proxy.adview.AdviewAdResponse;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class AdProxyAdviewTask extends AsynAbstractTask<AdviewAdPullParams,AdviewAdResponse>{

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
	public AdviewAdPullParams getParam() {
		// TODO Auto-generated method stub
		return  this.param;
	}

	@Override
	public void setParam(AdviewAdPullParams param) {
		// TODO Auto-generated method stub
		 this.param=param;
	}

	@Override
	public AdviewAdResponse respFormat(String result) {
		// TODO Auto-generated method stub
		AdviewAdResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				resp=JsonUtils.toBean(result, AdviewAdResponse.class);
			}
			
		}catch (Throwable e) {
			LOGGER.error("adview parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public AdviewAdResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}
}
