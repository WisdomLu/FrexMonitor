package com.ocean.persist.api.proxy.icloud;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
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
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月12日 
      @version 1.0 
 */
@Component(value="iCloudAdPuller")
public class ICloudAdPuller  extends AdPullerBase{
	public AdPullResponse api(Parameter params, String... exts)
			throws AdPullException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		String json = JsonUtils.toJson(params);
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		ICloudAdPullResponse data;
		logger.info("joinDSP:icloud {} request param:{}",exts,json);
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ICLOUD_URL), json,headers);
			logger.info("joinDSP:icloud {} ad return result:{}",exts,result);
			//String resultForm=result.replace("\"1001\"", "\"ad\"").replace("\'0\'", "\'ad\'");
			JSONObject ob=JSONObject.parseObject(result);
			logger.info("joinDSP:icloud {} ad parse return result:{}",exts,ob.get(exts[0]).toString());
			data = JsonUtils.toBean(ob.get(exts[0]).toString(), ICloudAdPullResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), "ad request error:"+e.getMessage());
		}
		logger.info("joinDSP:icloud {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return false;
	}

}
