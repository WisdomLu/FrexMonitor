package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2AdPullReq;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2AdPullResp;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2SignParam;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyWangyueV2Task extends AsynAbstractTask<WanyueV2SignParam,WanyueV2AdPullResp>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		//又是一脑残设计
		StringBuilder sb=new StringBuilder();
/*		sb.append("{").append(param.getSign())
		  .append("=").append(param.getParamJson()+"}");*/
		sb.append(param.getSign())
		  .append("=").append(param.getParamJson());
		return  sb.toString();
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WanyueV2AdPullResp respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		WanyueV2AdPullResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(check(result), WanyueV2AdPullResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("wangxiang {} parse response data error(Exception),msg:{}",this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	private String  check(String data){
	
		return data.substring(data.indexOf("=")+1);
	}
	
	@Override
	public WanyueV2AdPullResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
