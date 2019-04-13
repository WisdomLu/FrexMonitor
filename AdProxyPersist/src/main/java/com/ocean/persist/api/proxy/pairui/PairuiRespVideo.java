package com.ocean.persist.api.proxy.pairui;

public class PairuiRespVideo {

	// optional integer 广告素材宽
	private Integer w;

	// optional integer 广告素材高
	private Integer h;

	// required String 创意地址
	private String curl;
	
	// optional  integer 视频播放时长，单位秒
	private Integer duration;

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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
