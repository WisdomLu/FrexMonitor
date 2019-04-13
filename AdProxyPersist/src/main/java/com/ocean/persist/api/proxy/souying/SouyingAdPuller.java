package com.ocean.persist.api.proxy.souying;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="souyingAdPuller")
public class SouyingAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinDSP:sy {} request info:{}",exts,requestBody);
		SouyingAdResponse data;
		try {
			String result = HttpClient.getInstance()
					.post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.SOUYING_URL), requestBody);
			logger.info("joinDSP:sy {} reply result:{}",exts,result);
			data = JsonUtils.toBean(result, SouyingAdResponse.class);
			
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException("ad request fairled,return empty!");
		}
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return SouyingAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
