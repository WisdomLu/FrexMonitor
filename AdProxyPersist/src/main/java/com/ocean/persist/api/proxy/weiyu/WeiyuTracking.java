package com.ocean.persist.api.proxy.weiyu;

public class WeiyuTracking {
	private int track_type	;/*int	是	5-下载开始
	6-安装完成
	7-下载完成
	8-安装开始
	9-应用激活*/
	private String url	;//string	是	上报的url
	public int getTrack_type() {
		return track_type;
	}
	public String getUrl() {
		return url;
	}
	public void setTrack_type(int track_type) {
		this.track_type = track_type;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
