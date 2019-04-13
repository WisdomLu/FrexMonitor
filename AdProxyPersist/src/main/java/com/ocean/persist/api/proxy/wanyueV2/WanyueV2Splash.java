package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Splash {
	private int width;//	宽度	number		否	
	private int height;//	高度	number		否	
	private String assets_type;//	素材类型	string	素材类型值为：png|jpg|bmp|gif|mp4	是	
	private String assets_url	;//素材地址	string		是	
	private String html_snippet	;//HTML片段	String		否	
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
	public String getHtml_snippet() {
		return html_snippet;
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
	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}
}
