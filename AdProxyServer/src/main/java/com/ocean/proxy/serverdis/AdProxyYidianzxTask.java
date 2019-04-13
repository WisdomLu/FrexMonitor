package com.ocean.proxy.serverdis;
import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.yidianzx.YidianzxReq;
import com.ocean.persist.api.proxy.yidianzx.YidianzxResp;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月2日 
      @version 1.0 
 */
public class AdProxyYidianzxTask  extends AsynAbstractTask<YidianzxReq,YidianzxResp>{

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
	public YidianzxResp respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		YidianzxResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, YidianzxResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	@Override
	public YidianzxResp respFormat(byte[] result) {
		// TODO Auto-generated method stub

		return null;
	}

}
