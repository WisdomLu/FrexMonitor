package com.ocean.persist.api.proxy.wanyueV2;

import java.util.List;

public class WanyuV2Creative {
	private int id	;//创意ID	number	ID	是	
	private int index	;//索引	number	索引	是	
	private WanyueV2RespApp app	;//推广应用	object	推广APP	否	
	private int show_time	;//展示时长	number	广告展示多久后自动消失，单位秒	否	
	private int valid_time	;//有效时间	number	广告有效期，对过期广告的操作视为无效。单位秒	否	
	private WanyueV2Splash splash	;//开屏	object	开屏对象	否	
	private WanyueV2Banner banner	;//横幅图片	object	Banner对象	否	
	private WanyueV2BannerText banner_text	;//横幅图文	object	Banner 文本对象	否	
	private WanyueV2Interstitial interstitial;//	插屏	object	插屏对象	否	
	private WanyueV2Bar status_bar;//	状态栏	object	状态栏	否	
	private List<WanyueV2Native> _native	;//原生	array	原生对象	否	
	private List<WanyueV2Interaction> interaction	;//交互	array	交互	是	
	private List<WanyueV2Track> track;//	监测	array	监测	是	
	private String ad_proc;//	广告特殊处理方式	string	广告需要特殊处理的请看 6.3, 如果没有特殊说明请忽略  	是	
	public int getId() {
		return id;
	}
	public int getIndex() {
		return index;
	}
	public WanyueV2RespApp getApp() {
		return app;
	}
	public int getShow_time() {
		return show_time;
	}
	public int getValid_time() {
		return valid_time;
	}
	public WanyueV2Splash getSplash() {
		return splash;
	}
	public WanyueV2Banner getBanner() {
		return banner;
	}
	public WanyueV2BannerText getBanner_text() {
		return banner_text;
	}
	public WanyueV2Interstitial getInterstitial() {
		return interstitial;
	}
	public WanyueV2Bar getStatus_bar() {
		return status_bar;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setApp(WanyueV2RespApp app) {
		this.app = app;
	}
	public void setShow_time(int show_time) {
		this.show_time = show_time;
	}
	public void setValid_time(int valid_time) {
		this.valid_time = valid_time;
	}
	public void setSplash(WanyueV2Splash splash) {
		this.splash = splash;
	}
	public void setBanner(WanyueV2Banner banner) {
		this.banner = banner;
	}
	public void setBanner_text(WanyueV2BannerText banner_text) {
		this.banner_text = banner_text;
	}
	public void setInterstitial(WanyueV2Interstitial interstitial) {
		this.interstitial = interstitial;
	}
	public void setStatus_bar(WanyueV2Bar status_bar) {
		this.status_bar = status_bar;
	}
	public List<WanyueV2Native> get_native() {
		return _native;
	}
	public List<WanyueV2Interaction> getInteraction() {
		return interaction;
	}
	public List<WanyueV2Track> getTrack() {
		return track;
	}
	public String getAd_proc() {
		return ad_proc;
	}
	public void set_native(List<WanyueV2Native> _native) {
		this._native = _native;
	}
	public void setInteraction(List<WanyueV2Interaction> interaction) {
		this.interaction = interaction;
	}
	public void setTrack(List<WanyueV2Track> track) {
		this.track = track;
	}
	public void setAd_proc(String ad_proc) {
		this.ad_proc = ad_proc;
	}
}
