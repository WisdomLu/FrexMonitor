package com.ocean.proxy.serverdis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.security.SecurityEncode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.borui.BoruiBidReq;
import com.ocean.persist.api.proxy.borui.BoruiBidResp;

public class AdProxyBoruiTask extends AsynAbstractTask<BoruiBidReq, BoruiBidResp>{
	
	private static final Logger logger = LoggerFactory.getLogger(AdProxyBoruiTask.class);
	
	private static final org.apache.logging.log4j.Logger disLogger = MyLogManager.getDisLoggerV2();
	
	private static final int AES_KEY_LENGTH = 16;
	
	private static final String AES_KEY_SUFFIX = "0";
	
//	private static final String AES_ALGORITHM = "AES/CBC/NoPadding";
	
	private static final String AES_ALGORITHM = "AES/CBC/PKCS7Padding";

	@Override
	public String reqFormat() {
		return  JsonUtils.toJson(param);
	}

	private String getAesKey(BoruiBidReq param) {
		String sspId = param.getConfig().getSsp_id() + "";
		String mediumId = param.getConfig().getMedium_id() + "";
		String key = sspId + mediumId;
		if(key.length() > AES_KEY_LENGTH) {
			return key.substring(0, AES_KEY_LENGTH);
		} 
		if(key.length() < AES_KEY_LENGTH) {
			StringBuilder sb = new StringBuilder(key);
			for(int i=0; i < AES_KEY_LENGTH - key.length(); ++i) {
				sb.append(AES_KEY_SUFFIX);
			}
			return sb.toString();
		}
		return key;
	}

	@Override
	public byte[] reqByteFormat() {
		String json = JsonUtils.toJson(param);
		logger.info("borui {} req info:{}", this.getHashCode(),json);
		try {
			//AES加密
			String aeskey = getAesKey(param);
			byte[] reqbytes = json.getBytes("utf-8");
			reqbytes = SecurityEncode.AESEncode(reqbytes, aeskey, aeskey, AES_ALGORITHM);

			//gzip
			reqbytes = SecurityEncode.gzip(reqbytes);
			return reqbytes;
		} catch (Throwable e) {
			logger.error("borui req encrypt error:", e);
			return null;
		}
	}

	@Override
	public BoruiBidResp respFormat(String result) throws Exception {
		return null;
	}

	@Override
	public BoruiBidResp respFormat(byte[] result) {
		BoruiBidResp resp = null;
		try{
//			result = SecurityEncode.degzip(result);
//			String aeskey = getAesKey(param);
//			result = SecurityEncode.AESDecode(result, aeskey, aeskey, AES_ALGORITHM);
			String json = new String(result, "utf-8");
			disLogger.info("Borui Task {} return source data:{} ", hashCode, json);
			resp = JsonUtils.toBean(json, BoruiBidResp.class);
		} catch (Throwable e) {
			LOGGER.error("borui response data error(Exception),msg:{}",e.getMessage(),e);
		} finally{
			this.setResponse(resp);
		}
		return this.getResponse();
	}

}
