package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2BannerText {
	private String assets_url;//	图片素材地址	string	
	private String title;//	标题	string	
	private String sub_title;//	副标题	string	
	private int position	;//展示位置	number	1顶部
/*	2底部
	3中部*/
	public String getAssets_url() {
		return assets_url;
	}
	public String getTitle() {
		return title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public int getPosition() {
		return position;
	}
	public void setAssets_url(String assets_url) {
		this.assets_url = assets_url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
