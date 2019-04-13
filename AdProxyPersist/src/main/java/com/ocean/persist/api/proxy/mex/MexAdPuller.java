package com.ocean.persist.api.proxy.mex;

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
@Component(value="mexAdPuller")
public class MexAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException{
		
		/*MexAdPullParams paras = (MexAdPullParams)params;*/
		
		Map<String, String> headers = new HashMap<String, String>(5);
/*		headers.put("X-Forwarded-For", paras.getDevice().getIp());
		headers.put("User Agent", paras.getDevice().getUa());*/
		headers.put("Content-Type", "application/json");
		
		String requestBody = JsonUtils.toJson(params);
		requestBody = requestBody.replace("native1", "native");
		logger.info("joinDSP:myc {} request param:{}",exts,requestBody);
		
		MexAdResponse data;
		try {
			String result =HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.MEX_URL), requestBody, headers);
			logger.info("joinDSP:myc {} return result:{}",exts,result);
			data = JsonUtils.toBean(result, MexAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		//Integer status = data.getStatus();
		// 1 
//		0 	OK 
//		1 	版本号错误 
//		2 	应用 ID 或广告位 ID 错误 
//		3 	无法验证 token 
//		4 	展示信息缺失 
//		5 	原生广告布局 ID 错误 
//		6 	广告位已被暂停使用 
/*		if(status == null || status != 0){
			throw new AdPullException(status, "ad return error!");
		}*/
		return data;
	}
	
	public boolean supports(Parameter params) throws AdPullException {
		
		return MexAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
