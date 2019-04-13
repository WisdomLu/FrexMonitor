package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Banner {
	private int width;//	宽度	number		否	
	private int height;//		高度	number		否	
	private String assets_type;//		素材类型	string	素材类型值为：png|jpg|bmp|gif|mp4	是	
	private String assets_url;//		素材地址	string		是	
	private int position	;//	展示位置	number	1顶部
/*	2底部
	3中部	否	*/
	private String html_snippet;//		HTML片段	String		否	
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
	public int getPosition() {
		return position;
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
	public void setPosition(int position) {
		this.position = position;
	}
	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}
}
