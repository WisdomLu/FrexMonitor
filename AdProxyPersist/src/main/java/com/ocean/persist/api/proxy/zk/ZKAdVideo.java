package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAdVideo    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3239100188459822859L;
	private String coveImgUrl;
	private String videoUrl;
	private int width;
	private int height;
	private String format;
	private int duration;
	public String getCoveImgUrl() {
		return coveImgUrl;
	}
	public void setCoveImgUrl(String coveImgUrl) {
		this.coveImgUrl = coveImgUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
