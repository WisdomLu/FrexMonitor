package com.ocean.persist.app.dis.yitong.applist;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.BaseAppPuller;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.PersistConstants;

@Component(value="ListSearchYitongPuller")
public class ListSearchYitongPuller implements BaseAppPuller{
	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params, String... exts)
			throws AppDisException {
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		long ts = System.currentTimeMillis();
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YITONG_LIST_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		logger.info("YITONG_APPSTORE:GET_APP_LIST  search {} request param:{}",exts,url.toString());
	
		String requestBody = JsonUtils.toJson(params);
		logger.info("YITONG_APPSTORE:GET_APP_LIST search {} request info:{}",exts,requestBody);
		ListSearchYitongResp data;
		try {
					String result =  HttpClient.getInstance().get(url.toString());
					
					logger.info("YITONG_APPSTORE:GET_APP_LIST search  {}  app return  result:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);
					
					data = JsonUtils.toBean(result, ListSearchYitongResp.class);
					
		} 
		catch (HttpInvokeException e) {
			throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
		}
		logger.info("YITONG_APPSTORE:GET_APP_LIST search  {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}
	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}
}
