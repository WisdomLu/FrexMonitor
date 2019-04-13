package com.ocean.persist.api.proxy.adhub;

import java.util.HashMap;
import java.util.Map;

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
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="adHubAdPuller")
public class AdHubAdPuller extends AdPullerBase{
	
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/x-protobuf");
		headers.put("x-protobuf-version", "v2_9");
		long ts = System.currentTimeMillis();
		AdRequest.SdkRequest param = (AdRequest.SdkRequest) params;
		logger.info("joinDSP:adhub {} request param:{}",exts,UniversalUtils.replaceBlank(param.toString()));
		byte[] bytes;
		try {
			bytes = HttpClient.getInstance().postBytes(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ADHUB_URL), param.toByteArray(), headers);
		} 
		catch (HttpInvokeException e) {
			logger.error("joinDSP:adhub {} request api error ",exts,e);
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		AdResponseOuterClass.ServerResponse ss;
		try {
			ss = AdResponseOuterClass.ServerResponse.parseFrom(bytes);
			logger.info("joinDSP:adhub {} reply result:{}",exts,UniversalUtils.replaceBlank(ss.toString()));	
		} 
		catch (InvalidProtocolBufferException e) {
			logger.error("joinDSP:adhub {} return info parse error:{}",exts,e);
			throw new AdPullException("return ad info parse error:"+e.getMessage());
		}
		
		// 0 无错误 1 竞价出错/拒绝竞价  2无效的请求
//		if(ss.getNbr() != null){
//			System.out.println("code=" + ss.getNbr());
//		}
		logger.info("joinDSP:adhub {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return ss;
	}


	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return false;
	}








}
