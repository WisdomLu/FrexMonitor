package com.ocean.persist.app.dis.yitong.applist;

import org.apache.commons.lang3.StringUtils;
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

@Component(value="ListSearchYitongPuller_v2")
public class ListSearchYitongPuller_v2 implements BaseAppPuller{
	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params, String... exts)
			throws AppDisException {
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		long ts = System.currentTimeMillis();
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YITONG_LIST_URL_V2));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		logger.info("YITONG_APPSTORE:GET_APP_LIST v2  search {} request param:{}",exts,url.toString());
	
		String requestBody = JsonUtils.toJson(params);
		logger.info("YITONG_APPSTORE:GET_APP_LIST v2 search {} request info:{}",exts,requestBody);
		ListSearchYitongResp_v2 data=null;
		try {
					String result =  HttpClient.getInstance().get(url.toString());
					logger.info("YITONG_APPSTORE:GET_APP_LIST  search  v2 {}  app return  result:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);
					
					if(StringUtils.isNotEmpty(result)){
					
						data = JsonUtils.toBean(result.replaceAll("\"package\"","\"pkgName\"" ), ListSearchYitongResp_v2.class);
					}
					
					
				
					
		} 
		catch (HttpInvokeException e) {
			throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
		}finally{
			logger.info("YITONG_APPSTORE:GET_APP_LIST search v2  {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
			
		}
		return data;
	}
	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}
}
