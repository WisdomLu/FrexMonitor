package com.ocean.persist.api.proxy.yincheng;

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
import com.ocean.persist.api.proxy.yincheng.YcmModel.MobadsRequest;
import com.ocean.persist.common.ProxyConstants;
@Component(value="yinchengAdPuller")
public class YinchengAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/x-protobuf");
		headers.put("x-protobuf-version", "v2_9");
		
		YcmModel.MobadsRequest param = (YcmModel.MobadsRequest) params;
		logger.info("joinDSP:yincheng {} request param:{}",exts,UniversalUtils.replaceBlank(param.toString()));
		byte[] bytes;
		try {
			bytes = HttpClient.getInstance().postBytes(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YINCHENG_URL), param.toByteArray(), headers);
		} 
		catch (HttpInvokeException e) {
			logger.error("joinDSP:yincheng {} request api error ",exts,e);
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		YcmModel.MobadsResponse ss;
		try {
			ss = YcmModel.MobadsResponse.parseFrom(bytes);
		} 
		catch (InvalidProtocolBufferException e) {
			logger.error("joinDSP:yincheng {} return info parse error:{}",exts,e);
			throw new AdPullException("return ad info parse error:"+e.getMessage());
		}
		logger.info("joinDSP:yincheng {} reply result:{}",exts,UniversalUtils.replaceBlank(ss.toString()));
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
		return MobadsRequest.class
				.isAssignableFrom(params.getClass());
	}
}
