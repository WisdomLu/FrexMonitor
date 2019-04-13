package com.ocean.persist.api.proxy.diankai;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.inveno.util.StringUtil;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月24日 
      @version 1.0 
 */
@Component(value="diankaiAdPuller")
public class DiankaiAdPuller  extends AdPullerBase{
	public AdPullResponse api(Parameter params, String... exts)
			throws AdPullException {
		// TODO Auto-generated method stub
			long ts = System.currentTimeMillis();
			//String json = JsonUtils.toJson(params);
			Map<String, String> headers = new HashMap<String, String>(2);
			headers.put("Content-Type", "application/json");
			
			StringBuilder url = new StringBuilder();
			url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.DIANKAI_URL));
			url.append("?").append(Bean2Utils.toHttpParams(params));
			logger.info("joinDSP:diankai {} request param:{}",exts,url.toString());
			DiankaiAdPullResponse data;
			try {
				String result = HttpClient.getInstance().get(url.toString(), headers);
				logger.info("joinDSP:diankai {} ad return result:{}",exts,result);
/*				if(!check(result)){
					throw new AdPullException(ErrorCode.INTER_ERROR, "ad request error,error code:"+result);
				}*/
				
				//data = JsonUtils.toBean(resultFormat(result), DiankaiAdPullResponse.class);
				data = JsonUtils.toBean(result, DiankaiAdPullResponse.class);
			} 
			catch (HttpInvokeException e) {
				throw new AdPullException(e.getCode(), "ad request error:"+e.getMessage());
			}
			logger.info("joinDSP:diankai {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
			return data;
	}
/*	private String resultFormat(String result){
		StringBuffer buff=new StringBuffer();
		return buff.append("{ads:").append(result).append("}").toString();
		
	}*/
    private boolean check(String result){
    	if(StringUtil.isEmpty(result)||result.trim().length()==1){
    		return false;
    	}
    	return true;
    }
	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return false;
	}

}
