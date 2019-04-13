package com.ocean.persist.api.proxy.toutiao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ocean.core.common.UniversalUtils;
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
@Component(value="toutiaoAdPuller")
public class ToutiaoAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/octet-stream");
		//headers.put("Accept", "application/x-protobuf");
		
		AppLianmengApiv18.BidRequest param = (AppLianmengApiv18.BidRequest) params;
		logger.info("joinDSP:tt {} request info:{}",exts,UniversalUtils.replaceBlank(param.toString()));
		byte[] bytes;
		try {
			bytes = HttpClient.getInstance().postBytes(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.TOUTIAO_URL), param.toByteArray(), headers);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		AppLianmengApiv18.BidResponse ss;
		try {
			ss = AppLianmengApiv18.BidResponse.parseFrom(bytes);
			logger.info("joinDSP:tt {} result:{}",exts,UniversalUtils.replaceBlank(ss.toString()));
		} 
		catch (InvalidProtocolBufferException e) {
			throw new AdPullException("return ad parse error:{}"+e.getMessage());
		}
		if(ss == null){
			throw new AdPullException("ad request fairled,ad return empty!");
		}
		return ss;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return AppLianmengApiv18.BidRequest.class
				.isAssignableFrom(params.getClass());
	}
}
