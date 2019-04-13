package com.ocean.persist.api.proxy.lingji;

import java.util.Collections;

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
@Component(value="lingjiAdPuller")
public class LingjiAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException{
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinDSP:lj {} request param:{}",exts,requestBody);
		String result;
		try {
			result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.LINGJI_MOB_URL), requestBody,
					Collections.singletonMap("Content-Type", "application/json"));
			logger.info("joinDSP:lj {} reply result:{}",exts,result);
			return JsonUtils.toBean(result, LingjiAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			// 灵集返回204表示没有广告
			if(e.getCode() == 204){
				logger.info("joinDSP:lj {} ad return empty,return code:204",exts,e);
				return null;
			}
			throw new AdPullException(e.getCode(), e.getMessage());
		}
	}
	public boolean supports(Parameter params) throws AdPullException {
		
		return LingjiAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
