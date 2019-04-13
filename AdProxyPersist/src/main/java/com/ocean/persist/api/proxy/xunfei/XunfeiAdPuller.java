package com.ocean.persist.api.proxy.xunfei;

import java.util.Collections;







import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="xunfeiAdPuller")
public class XunfeiAdPuller extends AdPullerBase{
	// api成功返回
	private static final int success_code = 70200;
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinDSP:xf {} request info:{}",exts,requestBody);
		// 返回数据
		XunfeiAdResponse response;
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.XUNFEI_URL), requestBody,
					Collections.singletonMap("X-protocol protocol -ver", SystemContext.getDynamicPropertyHandler().get(ProxyConstants.XUNFEI_VERSION)));
			// 是否有该元素 如果有表示下发的广告为原生广告或json格式的普通广告 
			// 否则为视频广告
//			if(JsonUtils.exists(result, "batch_ma")){
//				response = JsonUtils.toBean(result, XunfeiNativeAd.class);
//				response.setMatetype(XunfeiAdResponse.NATIVE_AD);
//			}
//			else{
//				response = JsonUtils.toBean(result, XunfeiVideoAd.class);
//				response.setMatetype(XunfeiAdResponse.VIDEO_AD);
//			}
			response = JsonUtils.toBean(result, XunfeiAdResponse.class);
			logger.info("joinDSP:xf {} reply result:{}",exts,result);
		} catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		int code = response.getRc();
		// 70204为没有广告
		if(code == 70204){
			logger.error("joinDSP:xf {} ad pull api return empty,return code:{}",exts,code);
			return null;
		}
		if(code != success_code){
//			throw new AdPullException(code, "ad request failed：" + response.getInfo_en());
			throw new AdPullException(code, "ad request failed：" + response.getInfo());
		}
		return response;
	}

	public boolean supports(Parameter params) throws AdPullException {
		
		return XunfeiAdPullParams.class
				.isAssignableFrom(params.getClass());
	}

}
