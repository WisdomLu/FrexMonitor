package com.ocean.persist.api.proxy.zhuoyi;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.MobadsRequest;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.MobadsResponse;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月6日 
      @version 1.0 
 */
@Component(value="zhuoyiAdPuller")
public class ZhuoyiAdPuller  extends AdPullerBase{
	public AdPullResponse api(Parameter params, String... exts)
			throws AdPullException {
		// TODO Auto-generated method stub
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/x-protobuf");
		headers.put("x-protobuf-version", "v2_9");
		long ts = System.currentTimeMillis();
		MobadsRequest param = (MobadsRequest) params;
		logger.info("joinDSP:zhuoyi {} request param:{}",exts,UniversalUtils.replaceBlank(param.toString()));
		byte[] bytes;
		try {
			bytes = HttpClient.getInstance().postBytes(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHUOYI_URL), param.toByteArray(), headers);
		} 
		catch (HttpInvokeException e) {
			logger.error("joinDSP:zhuoyi {} request api error ",exts,e);
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		MobadsResponse ss;
		try {
			ss = MobadsResponse.parseFrom(bytes);
			logger.info("joinDSP:zhuoyi {} reply result:{}",exts,UniversalUtils.replaceBlank(ss.toString()));	
		} 
		catch (InvalidProtocolBufferException e) {
			logger.error("joinDSP:zhuoyi {} return info parse error:{}",exts,e);
			throw new AdPullException("return ad info parse error:"+e.getMessage());
		}

		logger.info("joinDSP:zhuoyi {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return ss;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return false;
	}

}
