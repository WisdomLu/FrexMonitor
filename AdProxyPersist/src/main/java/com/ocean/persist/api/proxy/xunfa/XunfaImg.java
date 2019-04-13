package com.ocean.persist.api.proxy.xunfa;

public class XunfaImg {
	private String type;//	图片类型	否
	private String url;//	地址	否
	private String w	;//宽，没设置为 0	否
	private String h	;//高，没设置为 0	否
	public String getType() {
		return type;
	}
	public String getUrl() {
		return url;
	}
	public String getW() {
		return w;
	}
	public String getH() {
		return h;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setW(String w) {
		this.w = w;
	}
	public void setH(String h) {
		this.h = h;
	}
}
