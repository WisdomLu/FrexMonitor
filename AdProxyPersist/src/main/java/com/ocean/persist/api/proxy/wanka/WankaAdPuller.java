package com.ocean.persist.api.proxy.wanka;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;
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
@Component(value="wankaAdPuller")
public class WankaAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		long ts = System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/x-protobuf");
		headers.put("Accept", "application/x-protobuf");
		headers.put("id", SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANKA_ID));
		headers.put("token", SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANKA_TOKEN));
		
		WKSSP.WKSSPRequest param = (WKSSP.WKSSPRequest) params;
		logger.info("joinDSP:wk {} request info:{}",exts,param);
		byte[] bytes;
		try {
			bytes = HttpClient.getInstance().postBytes(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANKA_URL), param.toByteArray(), headers);
		} 
		catch (HttpInvokeException e) {
			logger.error("joinDSP:wk {} request api error ",exts,e);
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		WKSSP.WKSSPResponse ss;
		try {
			ss = WKSSP.WKSSPResponse.parseFrom(bytes);
		} 
		catch (InvalidProtocolBufferException e) {
			logger.error("joinDSP:wk {} return info parse error:{}",exts,e);
			throw new AdPullException("return info parse error:"+e.getMessage());
		}
		logger.info("joinDSP:wk reply result:{}",ss);
		if(ss == null){
			throw new AdPullException("ad pull api return empty");
		}
		if(ss.getCode() != 0){
			throw new AdPullException("ad pull api return empty,return code:" + ss.getCode());
		}
		logger.info("joinDSP:wk {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return ss;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return WKSSP.WKSSPRequest.class
				.isAssignableFrom(params.getClass());
	}
}
