package com.ocean.persist.api.proxy.zk;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;
@Component(value="zkAdPuller")
public class ZKAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params, String... exts)
			throws AdPullException {
		// TODO Auto-generated method stub

		long ts = System.currentTimeMillis();
		String json = JsonUtils.toJson(params);
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		ZKAdPullResponse data;
		logger.info("joinDSP:zhangku {} request param:{}",exts,json);
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHANGKU_URL), json,headers);
			logger.info("joinDSP:zhangku {} ad return result:{}",exts,result);
			data = JsonUtils.toBean(result, ZKAdPullResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), "ad request error:"+e.getMessage());
		}
		logger.info("joinDSP:zhangku {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return false;
	}

}
