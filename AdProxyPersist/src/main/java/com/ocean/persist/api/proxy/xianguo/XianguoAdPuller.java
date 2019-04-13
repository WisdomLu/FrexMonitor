package com.ocean.persist.api.proxy.xianguo;

import java.io.Serializable;
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
@Component(value="xianguoAdPuller")
public class XianguoAdPuller extends AdPullerBase{
	private static final long serialVersionUID = 4037551402514038978L;
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		long ts = System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/x-protobuf");
		headers.put("Accept", "application/x-protobuf");
		
		XianguoAdPullParams par = (XianguoAdPullParams) params;
		logger.info("joinDSP:xg {} request info:{}",exts,UniversalUtils.replaceBlank(par.toString()));
		byte[] bytes;
		try {
			bytes = HttpClient.getInstance().postBytes(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.XIANGUO_URL), par.getBidRequest().toByteArray(), headers);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		XinGuoV204.BidResponse ss;
		
		try {
			ss = XinGuoV204.BidResponse.parseFrom(bytes);
			logger.info("joinDSP:xg {} reply result:{}",exts,UniversalUtils.replaceBlank(ss.toString()));
		} 
		catch (InvalidProtocolBufferException e) {
			logger.info("joinDSP:xg {} return ad parse error:{}",exts,e);
			throw new AdPullException("return ad parse error:"+e.getMessage());
		}
		
		if(ss == null){
			throw new AdPullException("ad request fairled,return null!");
		}
		XianguoAdResponse response = new XianguoAdResponse();
		response.setResponse(ss);
		logger.info("joinDSP:xg {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return response;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return XianguoAdPullParams.class.isAssignableFrom(params.getClass());
	}
}
