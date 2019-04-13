package com.ocean.persist.api.proxy.wanka_v1;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
public class WankaMaterial {
	private String title;/*
	string
	是
	信息流创意标题*/
	private String sub_title;/*
	string
	是
	信息流创意子标题*/
	private String source;/*
	string
	是
	信息流创意来源*/
	private List<String> imgurl;/*
	
	array of string
	是
	信息流创意图片地址*/
	private String icon_action_url;
	private String  logourl;
	private String bg_color;
	private String text_color;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public String getIcon_action_url() {
		return icon_action_url;
	}
	public void setIcon_action_url(String icon_action_url) {
		this.icon_action_url = icon_action_url;
	}
	public String getLogourl() {
		return logourl;
	}
	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}
	public String getBg_color() {
		return bg_color;
	}
	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}
	public String getText_color() {
		return text_color;
	}
	public void setText_color(String text_color) {
		this.text_color = text_color;
	}
	public List<String> getImgurl() {
		return imgurl;
	}
	public void setImgurl(List<String> imgurl) {
		this.imgurl = imgurl;
	}
}
