package com.ocean.persist.api.proxy.youdao;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="youdaoAdPuller")
public class YoudaoAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YOUDAO_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		YoudaoAdResponse data;
		logger.info("joinDSP:yd {} request info:{}",exts,url.toString());
		try {
			String result = HttpClient.getInstance().get(url.toString());
			logger.info("joinDSP:yd {} ad return result:{}",exts,result);
			data = JsonUtils.toBean(result, YoudaoAdResponse.class);
			
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
		return YoudaoAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
