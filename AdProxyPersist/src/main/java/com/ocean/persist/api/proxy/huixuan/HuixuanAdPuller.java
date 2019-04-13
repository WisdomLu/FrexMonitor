package com.ocean.persist.api.proxy.huixuan;

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
import com.ocean.persist.api.proxy.yijifen.YijifenAdResponse;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
@Component(value="huixuanAdPuller")
public class HuixuanAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params, String... exts)
			throws AdPullException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		String json = JsonUtils.toJson(params);
		json=json.replaceAll("_native", "native");
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		HuixuanAdPullResponse data;
		logger.info("joinDSP:huixuan {} request param:{}",exts,json);
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.HUIXUAN_URL), json,headers);
			logger.info("joinDSP:huixuan {} ad return result:{}",exts,result);
			result=result.replaceAll("native", "_native");
			data = JsonUtils.toBean(result, HuixuanAdPullResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), "ad request error:"+e.getMessage());
		}
		logger.info("joinDSP:huixuan {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return HuixuanAdPullParams.class
				.isAssignableFrom(params.getClass());
	}

}
