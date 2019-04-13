package com.ocean.persist.api.proxy.zhongcheng;

import java.util.HashMap;
import java.util.Map;

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
@Component(value="zhongchengAdPuller")
public class ZhongchengAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		
		ZhongchengAdPullParams zcparams = (ZhongchengAdPullParams) params;
//		API-VER 	2.0 	S2S 
//		REQ-TYPE 	server 	S2S 
//		SSP-NAME 	填写 ssp 平台的名称全拼 	S2S 
//		ENCRYPTION                   	none 	S2S（根据加密情况） 
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("API-VER", zcparams.getApi_ver());
		headers.put("REQ-TYPE", "server");
		headers.put("SSP-NAME", "zookingsoft");
		headers.put("ENCRYPTION", "none");
		
		ZhongchengAdResponse data;
		String requestBody = null;
		try {
			requestBody = JsonUtils.toJson(zcparams);
			logger.info("joinDSP:zc {} request param:{}",exts,requestBody);
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHONGCHENG_URL), requestBody, headers);
			logger.info("joinDSP:zc {} reply result:{}",exts,result);
			data = JsonUtils.toBean(result, ZhongchengAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException(" ad request fairled,ad return empty!");
		}
		int code = data.getRes_code();
		if(code != 10200){
			throw new AdPullException(" ad request fairled,error code:"+code+"error message:" + data.getMsg_cn());
		}
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return ZhongchengAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
