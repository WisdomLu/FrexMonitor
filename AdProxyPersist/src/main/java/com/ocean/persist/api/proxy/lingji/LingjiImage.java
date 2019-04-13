package com.ocean.persist.api.proxy.lingji;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;


public class LingjiImage  {

	private static final long serialVersionUID = 1L;
	
	private String url;// main img图片url地址
	private Integer width;// image图片高度
	private Integer height;// image图片宽度
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
