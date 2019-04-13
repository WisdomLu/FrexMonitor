package com.ocean.persist.api.proxy.taylor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
@Component(value="taylorAdPuller")
public class TaylorAdPuller extends AdPullerBase{
	public AdPullResponse api(Parameter params,String ... exts) throws AdPullException{
		TaylorAdPullParams paras = (TaylorAdPullParams) params;
		// md5(appid+key+lid+time)
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(paras.getAppid());
		stringBuilder.append(paras.getAppkey());
		stringBuilder.append(paras.getLid());
		stringBuilder.append(paras.getTime());
		String token = DigestUtils.md5Hex(stringBuilder.toString());
		paras.setToken(token);
		
		paras.setAppkey(null);
		// 请求参数
		String requestBody = JsonUtils.toJson(paras);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>(2);
		nvps.add(new BasicNameValuePair("data", requestBody)); 
		logger.info("joinDSP:tl {} request param:{}",exts,requestBody);
		TaylorAdResponse data;
		try {
			String result = HttpClient.getInstance().post(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.TAYLORMEDIA_URL), nvps);
			logger.info("joinDSP:tl {} reply result:{}",exts,result);
			data = JsonUtils.toBean(result, TaylorAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException("ad pull api return empty");
		}
		int code = data.getCode();
		// 没有广告
		if(code == TaylorAdResponse.noad){
			throw new AdPullException("ad pull api return empty,return code:"+code);
		}
		// 不成功
		if(code != TaylorAdResponse.success){
			throw new AdPullException(code, "ad pull api return error,error msg:" + data.getMsg());
		}
		return data;	
	}
	
/*	public static void main(String[] args) throws AdPullException {
		
		TaylorParameterInterface params = new TaylorAdPullParams();
		params.setHeight(320);
		params.setWidth(640);
		params.setImei("86801402470743");
		params.setAppid("49E926A0E8F54665AC48E8A805819D4E");
		params.setAppname("yiqio");
		params.setVer("1.7.4");
		params.setAppversion("1.7.4");
		params.setModel("HUAWEI");
		params.setIp("183.16.192.223");
		params.setOsversion("6.0");
		params.setLanguage("zh-CN");
		// Android: 1, IOS: 2
		params.setImsi("46000");
		params.setNetwork("wifi");
		params.setOs(String.valueOf(1));
		params.setAndroidid("9774D56D682E549C");
		// 广告位
		params.setLid("3B8652DACFE04D039DD74E70FE978172");
		params.setApppackagename("com.zk.test");
		
		params.setMac("04:02:1F:2F:3F:7D");
		
		// 屏幕宽高
		params.setScreenheight(1920);
		params.setScreenwidth(1080);
		params.setTime(String.valueOf(System.currentTimeMillis()));
		params.setWifissid("");
		params.setUa("Mozilla/5.0(Linux;Android4.0.4;GT-I9220 Build/IMM76D)");
		params.setAppid("F984A2200C6342B7B7A1DAE3529BFEA6");
		params.setAppkey("F45D762BC3BA6C038A4CAEEF418282AB");
		TaylorAdPuller puller = new TaylorAdPuller("http://api.taylormedia.com.cn/v10/getad");
		System.out.println(puller.api(params));
	}*/
	
	public boolean supports(Parameter params) throws AdPullException {
		
		return TaylorAdPullParams.class.isAssignableFrom(params.getClass());
	}
}
