package com.ocean.proxy.serverdis;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.pairui.PairuiBidReq;
import com.ocean.persist.api.proxy.pairui.PairuiResp;

public class AdProxyPairuiTask extends AsynAbstractTask<PairuiBidReq, PairuiResp>{
	
	private static final Logger logger = LoggerFactory.getLogger(AdProxyPairuiTask.class);
	
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
	public PairuiResp respFormat(String result) throws Exception {
		disLogger.info("Pairui Task {} return source data:{} ", hashCode, result);
		PairuiResp resp = null;
		try {
			resp = JsonUtils.toBean(result, PairuiResp.class);
		} catch(Throwable e) {
			disLogger.error("Pairui resp convert json error", e);
		} finally{
			this.setResponse(resp);
		}
		return this.getResponse();
	}

	@Override
	public PairuiResp respFormat(byte[] result) {
		try {
			return JsonUtils.toBean(new String(result, "utf-8"), PairuiResp.class);
		} catch (UnsupportedEncodingException e) {
			logger.error("unexpected error:", e);
			return null;
		}
	}
}
