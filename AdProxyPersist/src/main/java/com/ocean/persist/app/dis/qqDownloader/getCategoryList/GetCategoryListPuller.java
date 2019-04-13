package com.ocean.persist.app.dis.qqDownloader.getCategoryList;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.BaseAppPuller;
import com.ocean.persist.app.dis.qqDownloader.AbstractQQDownloaderPuller;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getAppList.GetAppListPuller;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
@Component(value="getCategoryListPuller")
public class GetCategoryListPuller extends AbstractQQDownloaderPuller{
	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params,String ... exts) throws AppDisException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		String requestBody = JsonUtils.toJson(getRequestParams((QQDownloaderRequest)params));
		GetCategoryListResponse data;
		logger.info("joinAppStore:TENCENT_QQDOWNLOADER  getCategoryDetailList {} request info:{}",exts,requestBody);
		try {
			String url=getUrl((QQDownloaderRequest)params);
			logger.info("joinAppStore:TENCENT_QQDOWNLOADER  getCategoryDetailList {} url:{}",exts,url);
			String result = HttpClient.getInstance().post(url, requestBody.toString(),headers);
			logger.info("joinAppStore:TENCENT_QQDOWNLOADER  getCategoryDetailList {} ad return result:{}",exts,result);
			data = JsonUtils.toBean(result, GetCategoryListResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
		}finally{
			logger.info("joinAppStore:TENCENT_QQDOWNLOADER  getCategoryDetailList {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
			
		}
		return data;
	}


   
	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return true;
	}
}
