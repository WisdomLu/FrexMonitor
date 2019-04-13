package com.ocean.persist.api.proxy.pairui;

public class PairuiRespImg {
	
	// required  String  图片地址
	private String url;
	
	// required integer 图片高度
	private Integer w;
	
	// required integer 图片宽度
	private Integer h;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
}
