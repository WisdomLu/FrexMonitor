package com.ocean.persist.app.dis.wxcpd.keywordSearch;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.JsonParseException;
import com.ocean.core.common.JsonUtils;
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
@Component(value="KeywordSearchWxPuller")
public class KeywordSearchWxPuller   implements BaseAppPuller{
	private  final Logger logger = MyLogManager.getLogger();
	private static final String RESULT_CODE="code";
	private static final String RESULT_MSG="msg";
	private static final String RESULT_SOURCE="source";
	private static final String RESULT_SOURCE_REPLACE="localSource";
	public AppDisResponse api(AdDisParams params, String... exts)
			throws AppDisException {
		
		
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		long ts = System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
	
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinAppStore:WANGXIANG_APPSTORE WX_KEYWORD_SEARCH {} request info:{}",exts,requestBody);
		KeywordSearchWxResp data=null;
		try {
					String result =  HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.WANGXIANG_KEYWORD_URL), requestBody, headers);

					logger.info("joinAppStore:WANGXIANG_APPSTORE WX_KEYWORD_SEARCH {}  result:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);
					
					if(StringUtils.isNotEmpty(result)){
						JSONObject json=JSONObject.fromObject(result);
						if(json.getInt(RESULT_CODE)!=200){
							json.remove(RESULT_MSG);
							result=json.toString();
							logger.info("joinAppStore:WANGXIANG_APPSTORE WX_KEYWORD_SEARCH {}  result after format:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);
							
						}	
						data = JsonUtils.toBean(result.replaceAll(RESULT_SOURCE, RESULT_SOURCE_REPLACE), KeywordSearchWxResp.class);
					}
					
					
		}catch (HttpInvokeException e) {
			throw new AppDisException(e.getCode(), "app request error:"+e.getMessage());
		}catch(JsonParseException e){
			throw new AppDisException("app result parse error:"+e.getMessage());
		}
		logger.info("joinAppStore:WANGXIANG_APPSTORE WX_KEYWORD_SEARCH   {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}

	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}

}
