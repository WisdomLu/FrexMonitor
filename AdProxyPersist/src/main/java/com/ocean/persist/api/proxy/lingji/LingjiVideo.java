package com.ocean.persist.api.proxy.lingji;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

public class LingjiVideo  {

	private static final long serialVersionUID = 1L;
	
	private String url;// 贴片URL地址
	private String cover_img_url;// 视频信息流图片覆盖地址
	private Integer w;// 贴片宽度
	private Integer h;// 贴片高度
	private Integer duration;// 时长
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCover_img_url() {
		return cover_img_url;
	}

	public void setCover_img_url(String cover_img_url) {
		this.cover_img_url = cover_img_url;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
