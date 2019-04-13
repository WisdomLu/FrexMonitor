package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.flying.FlyingAdPullParams;
import com.ocean.persist.api.proxy.flying.FlyingAdPullResponse;
import com.ocean.persist.api.proxy.youxiao.YouxiaoAdPullParams;
import com.ocean.persist.api.proxy.youxiao.YouxiaoAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月2日 
      @version 1.0 
 */
public class AdProxyYouxiaoTask  extends AsynAbstractTask<YouxiaoAdPullParams,YouxiaoAdPullResponse>{

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
	public YouxiaoAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		YouxiaoAdPullResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, YouxiaoAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("youxiao {} parse response data error(Exception),msg:{}",this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public YouxiaoAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
