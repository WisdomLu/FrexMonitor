package com.ocean.persist.app.dis.wxcpd.asyn;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.PersistConstants;
@Component(value="WxAsynAppSearchPuller")
public class WxAsynAppSearchPuller {
	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params, String... exts)
			throws AppDisException {
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		long ts = System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
	
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinAppStore:WANGXIANG_APPSTORE Asyn request info:{}",exts,requestBody);
		WxAsynAppResponse data;
		try {
					String result =  HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.WANGXIANG_APPSTORE_ASYN_URL), requestBody, headers);
					logger.info("joinAppStore:WANGXIANG_APPSTORE Asyn  {}  app return  result:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);
	
					data = JsonUtils.toBean(result, WxAsynAppResponse.class);
				
		} 
		catch (HttpInvokeException e) {
			throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
		}
		logger.info("joinAppStore:WANGXIANG_APPSTORE Asyn  {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
    }

	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}

}
