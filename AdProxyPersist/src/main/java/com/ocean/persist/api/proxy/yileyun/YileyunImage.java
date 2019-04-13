package com.ocean.persist.api.proxy.yileyun;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunImage {
	private String url;/*
	string
	Required
	图片地址*/
	private int width;/*
	uint32
	Required
	宽度*/
	private int	height;/*
	uint32
	Required
	高度*/
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
}
