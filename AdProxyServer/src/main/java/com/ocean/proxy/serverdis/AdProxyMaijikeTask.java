package com.ocean.proxy.serverdis;
import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.maijike.MaijikeAdPullReq;
import com.ocean.persist.api.proxy.maijike.MaijikeAdPullResp;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class AdProxyMaijikeTask extends AsynAbstractTask<MaijikeAdPullReq,MaijikeAdPullResp>{

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
	public MaijikeAdPullResp respFormat(String result) {
		// TODO Auto-generated method stub
		MaijikeAdPullResp resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				resp=JsonUtils.toBean(result, MaijikeAdPullResp.class);
			}
			
		}catch (Throwable e) {
			LOGGER.error("{} parse response data error(Exception),msg:{}",this.getClass(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public MaijikeAdPullResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}
}
