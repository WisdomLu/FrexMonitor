package com.ocean.persist.api.proxy.vamaker;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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
@Component(value="vamakerAdPuller")
public class VamakerAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException {
		
		VamakerAdPullParams paras = (VamakerAdPullParams) params;
		// 参数连接
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.VAMAKER_URL));
		url.append("?_a=").append(paras.get_a());
		append(url, "_pgn", paras.get_pgn());
		append(url, "_nt", paras.get_nt());
		append(url, "_o", paras.get_o());
		append(url, "_os", paras.get_os());
		append(url, "_osv", paras.get_osv());
		append(url, "_dev", paras.get_dev());
		append(url, "_md", paras.get_md());
		append(url, "_mc", paras.get_mc());
		append(url, "_aid", paras.get_aid());
		append(url, "_aaid", paras.get_aaid());
		append(url, "_w", paras.get_w());
		append(url, "_h", paras.get_h());
		append(url, "_lon", paras.get_lon());
		append(url, "_lat", paras.get_lat());
		append(url, "_mpn", paras.get_mpn());
		append(url, "_gd", paras.get_gd());
		append(url, "_bd", paras.get_bd());
		append(url, "_ip", paras.get_ip());
		append(url, "_z", paras.get_z());
		append(url, "_k", paras.get_k());
		append(url, "_debug", paras.get_debug());
		append(url, "_adw", paras.get_adw());
		append(url, "_adh", paras.get_adh());
		append(url, "_t", paras.get_t());
		append(url, "_idfa", paras.get_idfa());
		// imei 需要md5
		String imei = paras.get_imei();
		if(!StringUtils.isEmpty(imei)){
			imei = DigestUtils.md5Hex(paras.get_imei());
		}
		append(url, "_imei", imei);
		logger.info("joinDSP:wlk {} request param:{}",exts,url);
		String result;
		try {
			result = HttpClient.getInstance().get(url.toString());
			logger.info("joinDSP:wlk {} reply result:{}",exts,result);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		// 视频广告
		if(paras.getIsVideo()){
			return new VamakerVideoAdResponse(result); 
		}
		// 非视频广告
		VamakerAdResponse data = JsonUtils.toBean(result, VamakerAdResponse.class);
		if(data == null){
			throw new AdPullException("ad pull api return empty");
		}
		return data;
	}

	private void append(StringBuilder sb,String pn, Object param){
	
		if(param != null){
			sb.append("&").append(pn).append("=").append(param);
		}
	}
	
	public boolean supports(Parameter params) throws AdPullException {
		return VamakerAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
