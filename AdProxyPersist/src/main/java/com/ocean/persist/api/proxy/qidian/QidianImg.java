package com.ocean.persist.api.proxy.qidian;

public class QidianImg {
	private String url;
	private double width;
	private double height ;
	private String desc ;
	public String getUrl() {
		return url;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public String getDesc() {
		return desc;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
