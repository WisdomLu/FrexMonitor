package com.ocean.persist.app.dis.onemobi;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="OnemobiAppListSearchPuller")
public class OnemobiAppListSearchPuller  implements BaseAppPuller{
	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params, String... exts)
			throws AppDisException {

		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		long ts = System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>(2);
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.ONEMOBI_APPSTORE_URL));
		url.append("?");
		url.append(Bean2Utils.toHttpParams(params));
		String reqURL=url.toString();
		logger.info("joinAppStore:ONEMOBI_APPSEARCH {} request info:{}",exts,reqURL);
		OnemobiResponse data;
		try {
				String result =  HttpClient.getInstance().get(reqURL, headers);
		 		logger.info("joinAppStore:ONEMOBI_APPSEARCH  {}  app return  result:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);

				data = JsonUtils.toBean(result, OnemobiResponse.class);
			} 
			catch (HttpInvokeException e) {
				throw new AppDisException(e.getCode(), "app request error:"+e.getMessage());
			}finally{
				logger.info("joinAppStore:ONEMOBI_APPSEARCH  {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
			}

				return data;
	}

	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}
}


