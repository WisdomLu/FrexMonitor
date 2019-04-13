package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.zk.ZKAdPullParams;
import com.ocean.persist.api.proxy.zk.ZKAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年9月6日 
      @version 1.0 
 */
public class AdProxyZhangKuTask  extends AsynAbstractTask<ZKAdPullParams,ZKAdPullResponse>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		String paramStr=JsonUtils.toJson(param);
		//LOGGER.info("joinDSP:zhangku {} request param:{}",hashCode,paramStr);
		return  paramStr;
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZKAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		
		ZKAdPullResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				resp=JsonUtils.toBean(result, ZKAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("zhangku {} parse respose data  error(Exception):{},msg:{}",this.getHashCode(),result,e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}

		return this.getResponse();
	}

	@Override
	public ZKAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
