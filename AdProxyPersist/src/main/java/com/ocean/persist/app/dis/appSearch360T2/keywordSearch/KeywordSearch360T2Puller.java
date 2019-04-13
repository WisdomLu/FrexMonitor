package com.ocean.persist.app.dis.appSearch360T2.keywordSearch;

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
import com.ocean.persist.app.dis.BaseAppPuller;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.PersistConstants;
@Component(value="KeywordSearch360T2Puller")
public class KeywordSearch360T2Puller  implements BaseAppPuller{

	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params,String ... exts)
			throws AppDisException {
		// TODO Auto-generated method stub
		if(params==null){
			return null;
		}
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		KeywordSearch360T2Request request=(KeywordSearch360T2Request)params;
		
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		StringBuffer  urlBuff=new StringBuffer(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.QIHOO_TYPE2_APPSTORE_URL));
		urlBuff.append("?").append("from=").append(request.getFrom())
		.append("&ch=").append(request.getCh()).append("&adspaceid=").append(request.getAdspaceid())
		.append("&ip=").append(request.getIp()).append("&sdk_track=").append(request.getSdk_track())
		.append("&ts=").append(request.getTs());
		
		
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinAppStore:QIHOO_TYPE2_APPSTORE KEYWORD_SEARCH  {} request info:{}",exts,requestBody);
		KeywordSearch360T2Response data;
		try {
			
			logger.info("joinAppStore:QIHOO_TYPE2_APPSTORE KEYWORD_SEARCH {} url:{}",exts,urlBuff.toString());
			String result = HttpClient.getInstance().post(urlBuff.toString(), requestBody.toString(),headers);
			String content=isLogOpen==1?result:PersistConstants.OMIT_CONTENT;
			logger.info("joinAppStore:QIHOO_TYPE2_APPSTORE KEYWORD_SEARCH {}  ad return result:{}",exts,content);
			data = JsonUtils.toBean(result, KeywordSearch360T2Response.class);
		} 
		catch (HttpInvokeException e) {
			throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
		}finally{
			logger.info("joinAppStore:QIHOO_TYPE2_APPSTORE KEYWORD_SEARCH {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
			
		}
		return data;
	}

	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}

}
