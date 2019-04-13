package com.ocean.proxy.serverdis;
import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2AdPullReq;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2AdPullResp;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class AdProxyZhuoyiTask extends AsynAbstractTask<ZhuoyiV2AdPullReq,ZhuoyiV2AdPullResp>{

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
	public ZhuoyiV2AdPullResp respFormat(String result) {
		// TODO Auto-generated method stub
		ZhuoyiV2AdPullResp resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				resp=JsonUtils.toBean(result, ZhuoyiV2AdPullResp.class);
			}
			
		}catch (Throwable e) {
			LOGGER.error("{} parse response data error(Exception),msg:{}",this.getClass(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public ZhuoyiV2AdPullResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}
}
