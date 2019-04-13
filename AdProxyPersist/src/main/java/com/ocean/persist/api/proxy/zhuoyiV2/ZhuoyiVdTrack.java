package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiVdTrack {
	private int tracking_event;
	/*	required	int	VIDEO_AD_START(101000) 视频开始播放
	VIDEO_AD_CLOSE(102001) 广告被关闭
	VIDEO_AD_CLICK(102002) 广告被点击
	VIDEO_AD_SKIP(102003) 广告跳过*/
	private String tracking_url;//	string	监测地址 url
	public int getTracking_event() {
		return tracking_event;
	}
	public String getTracking_url() {
		return tracking_url;
	}
	public void setTracking_event(int tracking_event) {
		this.tracking_event = tracking_event;
	}
	public void setTracking_url(String tracking_url) {
		this.tracking_url = tracking_url;
	}
}
