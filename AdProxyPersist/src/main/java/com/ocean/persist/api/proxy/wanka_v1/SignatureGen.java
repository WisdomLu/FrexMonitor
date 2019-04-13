package com.ocean.persist.api.proxy.wanka_v1;

import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
public class SignatureGen {
	public static final Logger LOGGER = MyLogManager.getLogger();
	public static String generateToken(String method,String host,String path,String apiSecret,String req,long timestamp) throws Exception {
		    LOGGER.debug("{}:{}:{}:{}", method, host, path, apiSecret, req);
			StringBuilder tokenBuilder = new StringBuilder();
			tokenBuilder.append(method).append("\n");
			tokenBuilder.append(host).append("\n");
			tokenBuilder.append(path).append("\n");
			tokenBuilder.append("reqjson=").append(java.net.URLEncoder.encode(req, "UTF-8")).append("&");
			tokenBuilder.append("token=").append(apiSecret);
			//tokenBuilder.append("apiSecret=").append(apiSecret);
			tokenBuilder.append("&").append("timestamp=").append(timestamp);
			java.security.MessageDigest mDigest = java.security.MessageDigest.getInstance("MD5");
			String signature = org.apache.commons.codec.binary.Hex.encodeHexString(mDigest.digest(tokenBuilder.toString().getBytes("utf-8"))); 
			return signature;

		}
}
