package com.ocean.persist.api.proxy.pairui;

public class PairuiRespBanner {
	
	// optional integer  广告素材宽
	private Integer w;
	
	// optional integer  广告素材高
	private Integer h;
	
	// required String 创意地址 
	private String curl;

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}
}
