package com.ocean.persist.api.proxy.dianguan;

import java.util.List;

public class DianguanSnippet {
	private String url;
	private String c_url;
	private int width;
	private int  height;
	private List<String> imp;
	private List<String> clk;
	private String title;
	private String desc;
	
	//text_icon_snipet
	private String ext_urls;
	
	
	//video_sippet
	private String logo_url;
	private int duration;
	private List<DianguanStepimp> video_imp;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getC_url() {
		return c_url;
	}
	public void setC_url(String c_url) {
		this.c_url = c_url;
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
	public List<String> getImp() {
		return imp;
	}
	public void setImp(List<String> imp) {
		this.imp = imp;
	}
	public List<String> getClk() {
		return clk;
	}
	public void setClk(List<String> clk) {
		this.clk = clk;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getExt_urls() {
		return ext_urls;
	}
	public void setExt_urls(String ext_urls) {
		this.ext_urls = ext_urls;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<DianguanStepimp> getVideo_imp() {
		return video_imp;
	}
	public void setVideo_imp(List<DianguanStepimp> video_imp) {
		this.video_imp = video_imp;
	}

 }
