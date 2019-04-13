package com.ocean.persist.api.proxy.huzhong;

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
import com.ocean.persist.api.proxy.helian.HelianAdPullResponse;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
@Component(value="huzhongAdPuller")
public class HuzhongAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params, String... exts)
			throws AdPullException {
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.HUZHONG_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		logger.info("joinDSP:huzhong {} request param:{}",exts,url.toString());
		HuzhongAdPullResponse data;
		try {
			String result = HttpClient.getInstance().get(url.toString());
			if(StringUtils.isEmpty(result)){
				return null;
			}
			String resultForm=result.replace("\"0\"", "\"type\"").replace("\'0\'", "\'type\'");//sb,tmd太不专业了，竟然用一个数字作参数名
			data = JsonUtils.toBean(resultForm, HuzhongAdPullResponse.class);
			logger.info("joinDSP:huzhong {} reply result:{}",exts,result);
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
