package com.ocean.persist.api.proxy.maiguang;

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
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月30日 
      @version 1.0 
 */
@Component(value="maiguangAdpuller")
public class MaiguangAdpuller  extends AdPullerBase{
	public AdPullResponse api(Parameter params, String... exts)
			throws AdPullException {
		// TODO Auto-generated method stub
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.MAIGUANG_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		logger.info("joinDSP:maiguang {} request param:{}",exts,url.toString());
		MaiguangAdpullResponse data;
		try {
			String result = HttpClient.getInstance().get(url.toString());
			if(StringUtils.isEmpty(result)){
				return null;
			}
			data = JsonUtils.toBean(result, MaiguangAdpullResponse.class);
			logger.info("joinDSP:maiguang {} reply result:{}",exts,result);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(),"HttpInvokeException,"+e.getMessage());
		}
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return false;
	}

}
