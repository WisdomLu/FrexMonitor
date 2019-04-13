package com.ocean.persist.api.proxy.inmobi;

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
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="inmobiAdPuller")
public class InmobiAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException{
		
//		InmobiAdPullParams paras = (InmobiAdPullParams)params;
	
		Map<String, String> headers = new HashMap<String, String>(5);
		headers.put("Content-Type", "application/json");
		
		String requestBody = JsonUtils.toJson(params);
		requestBody = requestBody.replace("native1", "native");
		logger.info("joinDSP:im {} request param:{}",exts,requestBody);
		InmobiAdResponse data;
		try {
			String result =  HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.INMOBI_URL), requestBody, headers);
			logger.info("joinDSP:im {} reply result:{}",exts,result);
			data = JsonUtils.toBean(result, InmobiAdResponse.class);
			
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), "ad request fairled:"+e.getMessage());
		}
		return data;
	}
	
	public boolean supports(Parameter params) throws AdPullException {
		
		return InmobiAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
