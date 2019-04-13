package com.ocean.persist.api.proxy.onemob;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.PersistConstants;
import com.ocean.persist.common.ProxyConstants;
@Component(value="onemobAdPuller")
public class OnemobAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		long ts = System.currentTimeMillis();
/*		
 *      //=====get====
 *      StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ONEMOB_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		logger.info("joinDSP:om {} request param:{}",exts,url.toString());
		OnemobAdResponse data;
		try {
			String result = HttpClient.getInstance().get(url.toString());
			if("None".equals(result)){
				return null;
			}
			data = JsonUtils.toBean(result, OnemobAdResponse.class);
			logger.info("joinDSP:om {} reply result:{}",exts,result);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(),"HttpInvokeException,"+e.getMessage());
		}
		if(data == null){
			throw new AdPullException("ad request fairled,return empty！");
		}
		return data;*/
		
		
		//======post====
		Map<String, String> headers = new HashMap<String, String>(2);
		//headers.put("Content-Type", "application/json");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		
		StringBuilder body = new StringBuilder();
		body.append(Bean2Utils.toHttpParams(params));
		logger.info("joinDSP:om {} request param:{}",exts,body.toString());
		OnemobAdResponse data;


		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ONEMOB_URL),body.toString(),headers);
			logger.info("joinDSP:om {} ad return result:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);
			if(StringUtils.isEmpty(result)||"none".equals(result.toLowerCase().trim())||"no ad".equals(result.toLowerCase().trim())){
				return null;
			}
			data = JsonUtils.toBean(result, OnemobAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			
			throw new AdPullException(e.getCode(), "ad request error:"+e.getMessage());
		}
		logger.info("joinDSP:om {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		return OnemobAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
