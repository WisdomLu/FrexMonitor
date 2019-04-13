package com.ocean.persist.app.dis.baiduAppSearch.keywordSearch;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.Xml2JsonUtil;
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
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="keywordSearchBaiduPuller")
public class KeywordSearchBaiduPuller  implements BaseAppPuller{
	private  final Logger logger = MyLogManager.getLogger();
	public AppDisResponse api(AdDisParams params, String... exts)
			throws AppDisException {
		// TODO Auto-generated method stub
				if(params==null){
					return null;
				}
				int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
				KeywordSearchBaiduRequest request=(KeywordSearchBaiduRequest)params;
				// TODO Auto-generated method stub
				long ts = System.currentTimeMillis();
				StringBuffer  urlBuff=new StringBuffer(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.BAIDU_APPSTORE_URL));
				
				urlBuff.append("&").append(Bean2Utils.toHttpParams(params));
				
				logger.info("joinAppStore:BAIDU_APPSEARCH keywordSearch  {} request info:{}",exts,urlBuff);
				KeywordSearchBaiduReply data;
				try {
					String result = HttpClient.getInstance().get(urlBuff.toString());
					logger.info("joinAppStore:BAIDU_APPSEARCH keywordSearch {}  ad return xml result:{}",exts,isLogOpen==1?result:PersistConstants.OMIT_CONTENT);
					//xml转json
					String formatStr=Xml2JsonUtil.xml2JSON(result);
					String content=isLogOpen==1?formatStr:PersistConstants.OMIT_CONTENT;
					logger.info("joinAppStore:BAIDU_APPSEARCH keywordSearch {}  ad return json result:{}",exts,content);
					data = JsonUtils.toBean(formatStr.replace("\"package\"", "\"_package\""), KeywordSearchBaiduReply.class);
				} 
				catch (HttpInvokeException e) {
					throw new AppDisException(e.getCode(), "ad request error:"+e.getMessage());
				}
				logger.info("joinAppStore:BAIDU_APPSEARCH keywordSearch {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
				return data;
	}

	public boolean supports(AdDisParams params) throws AppDisException {
		// TODO Auto-generated method stub
		return false;
	}
}


