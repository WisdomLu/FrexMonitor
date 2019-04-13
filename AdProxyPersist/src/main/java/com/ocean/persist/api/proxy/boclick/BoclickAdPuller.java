package com.ocean.persist.api.proxy.boclick;

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
@Component(value="boclickAdPuller")
public class BoclickAdPuller extends AdPullerBase {
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {

		BoclickAdPullParams paras = (BoclickAdPullParams) params;
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.BOCLICK_URL));
		url.append("?zoneid=").append(paras.getZoneid());
		url.append("&width=").append(paras.getWidth());
		url.append("&height=").append(paras.getHeight());
		url.append("&media=").append(paras.getMedia());
		url.append("&layerstyle=").append(paras.getLayerstyle());
		url.append("&phone_model=").append(paras.getPhone_model());
		url.append("&software_name=").append(paras.getSoftware_name());
		url.append("&unid=").append(paras.getUnid());
		logger.info("joinDSP:bd {} request param:{}",exts,url.toString());
		BoclickAdResponse data;
		try {
			String result = HttpClient.getInstance().get(url.toString());
			logger.info("joinDSP:bd {} return result:{}",exts,result);
			data = JsonUtils.toBean(result, BoclickAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException("ad pull api return empty!");
		}
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return BoclickAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
