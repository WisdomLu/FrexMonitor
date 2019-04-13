package com.ocean.persist.api.proxy.adview;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
import com.ocean.persist.common.ProxyConstants;
@Component(value="adviewAdPuller")
public class AdviewAdPuller extends AdPullerBase {
	public AdPullResponse api(Parameter ps,String ... exts) throws AdPullException {
		long ts = System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>(2);
		// 请求头设置：请确保请求头带有Accept-Encoding，包含gzip。如果AdView以gzip格式返回，需要合作方进行解压缩处理。
		headers.put("Accept-Encoding", "identity;q=1.0,gzip;q=0.5,*;q=0");
		AdviewAdPullParams params = (AdviewAdPullParams) ps;
		params.setTp(encode(params.getTp()));
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ADVIEW_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		logger.info("joinDSP:av {} request info:{}",exts,url.toString());
		AdviewAdResponse data;
		try {
			String result = HttpClient.getInstance().get(url.toString(),headers);
			if(result.indexOf("native") > 0){
				result = result.replaceAll("native", "nat");
			}
			data = JsonUtils.toBean(result, AdviewAdResponse.class);
			logger.info("joinDSP:av {} return result:{}",exts,result);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException("ad pull api return empty!");
		}
		if(data.getRes() != AdviewAdResponse.success){
			throw new AdPullException("ad request failed,res=" + data.getRes() 
					+ ",mg=" + data.getMg());
		}
		logger.info("joinDSP:av {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}
	
	private String encode(String param){
		
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}


	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return AdviewAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
