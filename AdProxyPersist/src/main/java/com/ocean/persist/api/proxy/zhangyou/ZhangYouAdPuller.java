package com.ocean.persist.api.proxy.zhangyou;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
@Component(value="zhangYouAdPuller")
public class ZhangYouAdPuller extends AdPullerBase{
	private static final long serialVersionUID = -1684399347270768299L;
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		ZhangYouAdPullParams paras = (ZhangYouAdPullParams)params;
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		if(exts.length>1){
			headers.put("X-Forwarded-For", exts[0]);//客户端ip
		}else{
			throw new AdPullException(ErrorCode.PARAM_ERROR, "ad request client ip/ua is empty!");
		}

		headers.put("User-Agent", exts[1]);


		
		String requestBody = JsonUtils.toJson(paras);
		requestBody=requestBody.replace("_native", "native");
		                     
		logger.info("joinDSP:zhangyou {} request info:{}",exts,requestBody);
		ZhangYouAdPullResponse data;
		try {
			String result =  HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHANGYOU_URL), requestBody, headers);
			
			result=result.replace("native", "_native");
			logger.info("joinDSP:zhangyou {} ad api pull  return result:{}",exts,result);
			data = JsonUtils.toBean(result, ZhangYouAdPullResponse.class);
		} 
		catch (HttpInvokeException e) {
			// Ryan竞价失败，返回204表示没有广告
			if(e.getCode() == 204){
				logger.error("joinDSP:zhangyou {} ad pull api return empty!",exts, e);
				//System.out.println(requestBody);
				return null;
			}
			logger.error("joinDSP:zhangyou {} ad pull api error",exts, e);
			throw new AdPullException(e.getCode(), "ad pull api error"+e.getMessage());
			
		}catch(Exception e){
			logger.error("joinDSP:zhangyou {} ad pull api error!",exts, e);
			throw new AdPullException("ad pull api error!"+e.getMessage());
		}
		logger.info("joinDSP:zhangyou {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return false;
	}
}
