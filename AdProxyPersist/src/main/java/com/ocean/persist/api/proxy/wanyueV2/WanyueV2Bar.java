package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Bar {
	private int width	;//宽度	number		否	
	private int height	;//高度	number		否	
	private String assets_type	;//素材类型	string	素材类型值为：png|jpg|bmp|gif|mp4	是	
	private String assets_url	;//素材地址	string		是	
	private String text	;//文本内容	String		否	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getAssets_type() {
		return assets_type;
	}
	public String getAssets_url() {
		return assets_url;
	}
	public String getText() {
		return text;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setAssets_type(String assets_type) {
		this.assets_type = assets_type;
	}
	public void setAssets_url(String assets_url) {
		this.assets_url = assets_url;
	}
	public void setText(String text) {
		this.text = text;
	}
}
