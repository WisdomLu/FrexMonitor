package com.ocean.persist.api.proxy.yijifen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.security.SecurityEncode;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="yijifenAdPuller")
public class YijifenAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException{
		if(exts.length<2){
			throw new AdPullException(ErrorCode.PARAM_ERROR, "adId or version is empty!");
		}
		long ts = System.currentTimeMillis();
		YijifenAdPullParams paras = (YijifenAdPullParams)params;
		
		String json = JsonUtils.toJson(params);
		//logger.info("joinDSP:yjf request json:{}",json);
		json = SecurityEncode.encoderByDES(json, paras.getToken());

		StringBuilder requestBody = new StringBuilder();
		requestBody.append("q=").append(json);
		try {
			logger.info("joinDSP:yjf request {}, decode  info:{}",exts,SecurityEncode.decoderByDES(json, paras.getToken()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		requestBody.append("&k=").append(exts[0]);
		requestBody.append("&v=").append(exts[1]);
		//logger.info("yijifen request info:{}",requestBody);
		YijifenAdResponse data;
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YIJIFEN_URL), requestBody.toString());
			logger.info("joinDSP:yjf {} ad return result:{}",exts,result);
			data = JsonUtils.toBean(result, YijifenAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), "yijifen ad request error:"+e.getMessage());
		}
		logger.info("joinDSP:yjf {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}
	
	public boolean supports(Parameter params) throws AdPullException {
		
		return YijifenAdPullParams.class
				.isAssignableFrom(params.getClass());
	}


}
