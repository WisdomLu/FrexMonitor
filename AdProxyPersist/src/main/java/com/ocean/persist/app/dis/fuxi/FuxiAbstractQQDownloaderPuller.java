package com.ocean.persist.app.dis.fuxi;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.BaseAppPuller;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public abstract class FuxiAbstractQQDownloaderPuller  implements BaseAppPuller{
	  private static final String PARAM_BODY="body"; 
	  private static final String PARAM_HEAD="head"; 
	  public String getUrl(QQDownloaderRequest attri) throws AppDisException{
		   //String url=SystemContext.getDynamicPropertyHandler().get(AppDisConstants.FUXI_QQDOWNLOADER_URL);
		  String url="";
		  if(StringUtils.isEmpty(url)){
			   throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR, "param error,fuxi qqdownloader getcategorylist_url is empty");
		   }
		   return MessageFormat.format(url, attri.getInterName(),sign(attri));

	  }
	  public String sign(QQDownloaderRequest attri){
		  Map<String,Object>  map=getRequestParams(attri);
		  return DigestUtils.md5Hex(JsonUtils.toJson(map)+attri.getBusinessKey());
	  }
	  public Map<String,Object> getRequestParams(QQDownloaderRequest attri){
		  Map<String,Object>  map=new HashMap<String,Object>();
		  map.put(PARAM_BODY, attri.getBody());
		  map.put(PARAM_HEAD, attri.getHead());
		  return map;
	  }

}
