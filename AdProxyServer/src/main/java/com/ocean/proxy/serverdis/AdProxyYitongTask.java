package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.yitong.AbstractYitongResponse;
import com.ocean.persist.api.proxy.yitong.YitongAdPullEmptyResponse;
import com.ocean.persist.api.proxy.yitong.YitongAdPullParams;
import com.ocean.persist.api.proxy.yitong.YitongAdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyYitongTask extends AsynAbstractTask<YitongAdPullParams,AbstractYitongResponse>{

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
	public AbstractYitongResponse respFormat(String result) throws Exception {
		AbstractYitongResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				
				resp=formatResult(result);
			}
		}catch (Throwable e) {
			LOGGER.error("yitong parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();

	}
	private AbstractYitongResponse formatResult(String result){
		result=UniversalUtils.replaceBlank(result);
		if(result.startsWith("[")){
			StringBuilder b=new StringBuilder();
			b.append("{ads:").append(result).append("}");
			return JsonUtils.toBean(b.toString(),YitongAdPullResponse.class);
		}else{
			return JsonUtils.toBean(result,YitongAdPullEmptyResponse.class);
		}

	}
/*	private String formatResult(String result){
		result=UniversalUtils.replaceBlank(result);
		if(result.startsWith("[")){
			StringBuilder b=new StringBuilder();
			b.append("{ads:").append(result).append("}");
			return b.toString();
		}else{
			return result;
		}

	}*/
	@Override
	public AbstractYitongResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}


}
