package com.ocean.persist.api.proxy.vamaker;

import org.dom4j.DocumentException;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.common.util.vast.VastAd;
import com.ocean.persist.common.util.vast.VastParser;

public class VamakerVideoAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	
	private String result;// 视频广告返回值
	
	public VamakerVideoAdResponse(String result){
		
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public VastAd toVastData() throws DocumentException{
		
		return VastParser.parse(result);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
