package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Interstitial {
	private String icon;//	Icon地址	string		否	
	private String title;//	标题	string		否	
	private int width;//	宽度	number		否	
	private int height;//	高度	number		否	
	private String assets_type	;//素材类型	string	素材类型值为：png|jpg|bmp|gif|mp4	是	
	private String assets_url	;//素材地址	string		是	
	private String text	;//文本内容	string		否	
	private String button_text	;//按钮文本	string		否	
	private String html_snippet	;//HTML片段	String		否	
	public String getIcon() {
		return icon;
	}
	public String getTitle() {
		return title;
	}
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
	public String getButton_text() {
		return button_text;
	}
	public String getHtml_snippet() {
		return html_snippet;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public void setButton_text(String button_text) {
		this.button_text = button_text;
	}
	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}
}
