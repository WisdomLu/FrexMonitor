package com.ocean.persist.api.proxy.jieku;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="jiekuAdPuller")
public class JiekuAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException{
		
//		InmobiAdPullParams paras = (InmobiAdPullParams)params;
		
		Map<String, String> headers = new HashMap<String, String>(5);
		headers.put("Content-Type", "application/json");
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinDSP:jk {} request param:{}",exts,requestBody);
		
		JiekuAdResponse data;
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.JIEKU_URL), requestBody, headers);
			logger.info("joinDSP:jk {} return result:{}",exts,result);
			data = JsonUtils.toBean(result, JiekuAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null || !data.getSuccess()){
			throw new AdPullException("ad request return error info:" + data);
		}
		return data;
	}
	
	public boolean supports(Parameter params) throws AdPullException {
		
		return JiekuAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
