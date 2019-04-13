package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengAdPullParams;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyYuanshenTask extends AsynAbstractTask<YuanshengAdPullParams,YuanshengAdPullResponse>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		return  check(JsonUtils.toJson(param),0);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YuanshengAdPullResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		YuanshengAdPullResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(this.check(result,1), YuanshengAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("yuansheng {} parse response data error(Exception),msg:{}",this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	private String check(String str,int type){
		if(type==1){
			return str.replaceAll("native", "_native");
		}else{
			return str.replaceAll("_native","native");
		}
		
	}
	@Override
	public YuanshengAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
