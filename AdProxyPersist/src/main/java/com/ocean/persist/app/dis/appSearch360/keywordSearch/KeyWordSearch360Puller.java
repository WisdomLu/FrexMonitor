package com.ocean.persist.app.dis.appSearch360.keywordSearch;

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

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
@Component(value="keyWordSearch360Puller")
public class KeyWordSearch360Puller  implements BaseAppPuller{
	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params,String ... exts)
			throws AppDisException {
		// TODO Auto-generated method stub
		if(params==null){
			return null;
		}
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		KeywordSearch360Request request=(KeywordSearch360Request)params;
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		StringBuffer  urlBuff=new StringBuffer(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.QIHOO_APPSTORE_URL));
		urlBuff.append("?").append("from=").append(request.getFrom())
		.append("&ch=").append(request.getCh()).append("&adspaceid=").append(request.getAdspaceid())
		.append("&ip=").append(request.getIp());
		
		
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		String requestBody = JsonUtils.toJson(params);
		logger.info("joinAppStore:QIHOO_APPSTORE keywordSearch  {} request info:{}",exts,requestBody);
		KeywordSearch360Respose data;
		try {
			
			logger.info("joinAppStore:QIHOO_APPSTORE keywordSearch {} url:{}",exts,urlBuff.toString());
			String result = HttpClient.getInstance().post(urlBuff.toString(), requestBody.toString(),headers);
			String content=isLogOpen==1?result:PersistConstants.OMIT_CONTENT;
			logger.info("joinAppStore:QIHOO_APPSTORE keywordSearch {}  ad return result:{}",exts,content);
			data = JsonUtils.toBean(result, KeywordSearch360Respose.class);
		} 
		catch (HttpInvokeException e) {
			throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
		}finally{
			logger.info("joinAppStore:QIHOO_APPSTORE keywordSearch {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
			
		}
		return data;
	}

	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}

}
