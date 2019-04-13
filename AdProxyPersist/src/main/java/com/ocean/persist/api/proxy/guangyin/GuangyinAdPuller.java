package com.ocean.persist.api.proxy.guangyin;

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
@Component(value="guangyinAdPuller")
public class GuangyinAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/x-protobuf");
		headers.put("x-protobuf-version", "v2_9");
		
		OpenrtbV29.BidRequest param = (OpenrtbV29.BidRequest) params;
		logger.info("joinDSP:gy {} request param:{}",exts,param);
		byte[] bytes;
		try {
			bytes = HttpClient.getInstance().postBytes(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.GUANGYIN_URL), param.toByteArray(), headers);
		} 
		catch (HttpInvokeException e) {
			logger.error("joinDSP:gy {} request api error ",exts,e);
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		OpenrtbV29.BidResponse ss;
		try {
			ss = OpenrtbV29.BidResponse.parseFrom(bytes);
		} 
		catch (InvalidProtocolBufferException e) {
			logger.error("joinDSP:gy {} return info parse error:{}",exts,e);
			throw new AdPullException("return info parse error:"+e.getMessage());
		}
		logger.info("joinDSP:gy reply result:{}",ss);
		if(ss == null){
			throw new AdPullException("ad pull api return empty");
		}
		// 0 无错误 1 竞价出错/拒绝竞价  2无效的请求
//		if(ss.getNbr() != null){
//			System.out.println("code=" + ss.getNbr());
//		}
		return ss;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return OpenrtbV29.BidRequest.class
				.isAssignableFrom(params.getClass());
	}
}
