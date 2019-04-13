package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

public class MexImage  {

	private static final long serialVersionUID = 1L;
	
//	private String url;// main img图片url地址
	private Integer w;// image图片高度
	private Integer h;// image图片宽度
	
/*	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}*/

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
