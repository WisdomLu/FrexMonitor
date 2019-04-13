package com.ocean.persist.app.dis.fuxi.appasyn;

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

/** * @author Alex & E-mail:569246607@qq.com
@date   2018年5月10日 
@version 1.0 
*/
@Component(value="FuxiAppasynSearchPuller")
public class FuxiAppasynSearchPuller  implements BaseAppPuller{
private  final Logger logger = MyLogManager.getLogger();

public AppDisResponse api(AdDisParams params, String... exts)
		throws AppDisException {
	int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
	long ts = System.currentTimeMillis();
	StringBuilder url = new StringBuilder();
/*	url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.FUXI_APPASYN_URL));
	url.append("?").append(Bean2Utils.toHttpParams(params));*/
	logger.info("HAIQIBING_APPSTORE app asyn  search {} request param:{}",exts,url.toString());

	String requestBody = JsonUtils.toJson(params);
	logger.info("HAIQIBING_APPSTORE app asyn  search {} request info:{}",exts,requestBody);
	FuxiAppAsynResponse data;
	try {
				String result =  HttpClient.getInstance().get(url.toString());
				String formatResult=result.replaceAll("package", "_package");
				logger.info("HAIQIBING_APPSTORE app asyn  search  {}  app return  result:{}",exts,isLogOpen==1?formatResult:PersistConstants.OMIT_CONTENT);
				
				data = JsonUtils.toBean(formatResult, FuxiAppAsynResponse.class);
				
	} 
	catch (HttpInvokeException e) {
		throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
	}finally{
		logger.info("HAIQIBING_APPSTORE app asyn search  {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		
	}
	return data;
}

public boolean supports(AdDisParams params) throws AppDisException {
	// TODO Auto-generated method stub
	return false;
}
}