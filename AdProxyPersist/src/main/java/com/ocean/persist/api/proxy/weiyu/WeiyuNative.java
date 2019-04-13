package com.ocean.persist.api.proxy.weiyu;

import java.util.List;

public class WeiyuNative {
	private String title	;//string	否	标题	 
	private String desc;//	string	否	描述	 
	private List<WeiyuImg> icon	;//object[]	否	logo/头像	 
	private List<WeiyuImg> img	;//object[]	否	大图，参照下面img对象	 
	private String btnname	;//string	否	按钮文字	 
	private int rating	;//int	否	评定星级	 
	private int down_count	;//int	否	下载次数	 
	private String ldp	;//string	否	落地页
	//有些点击监测链接中有点击监测宏，需要媒体替换，不替换将没有收益！详情见下。	 
	private String app_download_url	;//string	否	下载地址
	//有些点击监测链接中有点击监测宏，需要媒体替换，不替换将没有收益！详情见下。	 
	private String deeplink	;//string	否	deeplink链接，如果有deeplink则优先调用，调用时需要上报 conv_tracking 中相应的监测链接。具体逻辑见下方 ”Deeplink调用逻辑”	
	public String getTitle() {
		return title;
	}
	public String getDesc() {
		return desc;
	}
	public List<WeiyuImg> getIcon() {
		return icon;
	}
	public List<WeiyuImg> getImg() {
		return img;
	}
	public String getBtnname() {
		return btnname;
	}
	public int getRating() {
		return rating;
	}
	public int getDown_count() {
		return down_count;
	}
	public String getLdp() {
		return ldp;
	}
	public String getApp_download_url() {
		return app_download_url;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setIcon(List<WeiyuImg> icon) {
		this.icon = icon;
	}
	public void setImg(List<WeiyuImg> img) {
		this.img = img;
	}
	public void setBtnname(String btnname) {
		this.btnname = btnname;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setDown_count(int down_count) {
		this.down_count = down_count;
	}
	public void setLdp(String ldp) {
		this.ldp = ldp;
	}
	public void setApp_download_url(String app_download_url) {
		this.app_download_url = app_download_url;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
}
