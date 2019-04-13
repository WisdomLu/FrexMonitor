package com.ocean.persist.api.proxy.speed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
@Component(value="speedAdPuller")
public class SpeedAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.SPEED_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		SpeedAdResponse data;
		logger.info("joinDSP:speed {} request info:{}",exts,url.toString());
		try {
			String result = HttpClient.getInstance().get(url.toString());
			data = JsonUtils.toBean(result, SpeedAdResponse.class);
			logger.info("joinDSP:speed {} ad return result:{}",exts,result);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException(ErrorCode.AD_EMPTY,"ad request failed,return empty！");
		}
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return SpeedAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
