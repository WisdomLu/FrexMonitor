package com.ocean.persist.api.proxy.ruangaoyun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.AdPullerBase;
import com.ocean.persist.common.ProxyConstants;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
@Component(value="ruanGaoYunAdPuller")
public class RuanGaoYunAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		RuanGaoYunAdPullParam paras = (RuanGaoYunAdPullParam)params;
		String requestBody = JsonUtils.toJson(paras);               
		logger.info("joinDSP:ruangaoyun {} request info:{}",exts,requestBody);
		RuanGaoYunAdPullResponse data;
		try {
			String result =  HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.RUANGAOYUN_URL), requestBody);
			logger.info("joinDSP:ruangaoyun {} ad api pull  return result:{}",exts,result);
			data = JsonUtils.toBean(result, RuanGaoYunAdPullResponse.class);
		} 
		catch (HttpInvokeException e) {

			logger.error("joinDSP:ruangaoyun {} ad pull api error,message:{}",exts,e.getMessage(), e);
			throw new AdPullException(e.getCode(), e.getMessage());
			
		}catch(Exception e){
			logger.error("joinDSP:ruangaoyun {} ad pull api error!",exts, e);
			throw new AdPullException("ad pull api error!"+e.getMessage());
		}
		logger.info("joinDSP:ruangaoyun {} pull api  time cost:{} ms",exts,System.currentTimeMillis() - ts);
		return data;
	}

	public boolean supports(Parameter params) throws AdPullException {
		// TODO Auto-generated method stub
		return RuanGaoYunAdPullParam.class
				.isAssignableFrom(params.getClass());
	}

}
