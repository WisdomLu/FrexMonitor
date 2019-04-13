package com.ocean.persist.api.proxy.ryan;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.JsonUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
@Component(value="ryanAdPuller")
public class RyanAdPuller  extends AdPullerBase{

	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		// TODO Auto-generated method stub
		RyanAdPullParams paras = (RyanAdPullParams)params;
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		headers.put("x-openrtb-version", "2.3.2");//openRtb协议
		String requestBody = JsonUtils.toJson(paras);
		requestBody.replace("_native", "native");
		logger.info("bid id:{},joinDSP:re {} request info:{}",exts,paras.getId(),requestBody);
		RyanAdPullResponse data;
		try {
			String result =  HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.RYAN_URL), requestBody, headers);
			logger.info("bid id:{},joinDSP:re {} result:",exts,paras.getId(),result);
			data = JsonUtils.toBean(result, RyanAdPullResponse.class);
		} 
		catch (HttpInvokeException e) {
			// Ryan竞价失败，返回204表示没有广告
			if(e.getCode() == 204){
				logger.error("joinDSP:re {} ad pull api return empty!",exts, e);
				//System.out.println(requestBody);
				return null;
			}
			logger.error("joinDSP:re {} ad pull api error!",exts, e);
			throw new AdPullException(e.getCode(), e.getMessage());
			
		}catch(Exception e){
			logger.error("joinDSP:re {} ad pull api error!",exts, e);
			throw new AdPullException("ad pull api error!"+e.getMessage());
		}
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return RyanAdPullParams.class.isAssignableFrom(params.getClass());
	}

}
