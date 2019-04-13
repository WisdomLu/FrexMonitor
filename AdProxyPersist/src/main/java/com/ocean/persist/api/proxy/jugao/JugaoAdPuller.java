package com.ocean.persist.api.proxy.jugao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
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
import com.ocean.persist.api.proxy.speed.SpeedAdPullParams;
import com.ocean.persist.api.proxy.speed.SpeedAdResponse;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017骞�4鏈�21鏃� 
      @version 1.0 
 */
@Component(value="jugaoAdPuller")
public class JugaoAdPuller  extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		// TODO Auto-generated method stub
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.JUGAO_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		JugaoAdResponse data;
		logger.info("joinDSP:jugao {} request info:{}",exts,url.toString());
		try {
			String result = HttpClient.getInstance().get(url.toString());
			data = JsonUtils.toBean(result, JugaoAdResponse.class);
			logger.info("joinDSP:jugao {} ad return result:{}",exts,result);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException(ErrorCode.AD_EMPTY,"ad request failed,return empty锛�");
		}
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return JugaoAdPullParams.class
				.isAssignableFrom(params.getClass());
	}

}
