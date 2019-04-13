package com.ocean.persist.api.proxy.oneway;

public class OneWayImg {
	private String url	;//String	图片链接
	private int w	;//int	图片宽
	private int h;//	int	图片高
	public String getUrl() {
		return url;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setW(int w) {
		this.w = w;
	}
	public void setH(int h) {
		this.h = h;
	}
}
