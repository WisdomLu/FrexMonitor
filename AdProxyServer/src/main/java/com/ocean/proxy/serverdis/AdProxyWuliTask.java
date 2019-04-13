package com.ocean.proxy.serverdis;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.wuli.WuliReq;
import com.ocean.persist.api.proxy.wuli.WuliResp;

public class AdProxyWuliTask extends AsynAbstractTask<WuliReq, WuliResp>{
	
	private static final Logger logger = LoggerFactory.getLogger(AdProxyWuliTask.class);
	
	private static final org.apache.logging.log4j.Logger disLogger = MyLogManager.getDisLoggerV2();
	
	@Override
	public String reqFormat() {
		return  JsonUtils.toJson(param);
	}


	@Override
	public byte[] reqByteFormat() {
		String json = JsonUtils.toJson(param);
		try {
			return json.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("unexpected error:", e);
			return null;
		}
	}

	@Override
	public WuliResp respFormat(String result) throws Exception {
		disLogger.info("wuli Task {} return source data:{} ", hashCode, result);
		WuliResp resp = null;
		try {
			resp = JsonUtils.toBean(result, WuliResp.class);
		} catch(Throwable e) {
			disLogger.error("wuli resp convert json error", e);
		} finally{
			this.setResponse(resp);
		}
		return this.getResponse();
	}

	@Override
	public WuliResp respFormat(byte[] result) {
		try {
			return JsonUtils.toBean(new String(result, "utf-8"), WuliResp.class);
		} catch (UnsupportedEncodingException e) {
			logger.error("unexpected error:", e);
			return null;
		}
	}
}
