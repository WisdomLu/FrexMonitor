package com.ocean.persist.api.proxy.jieku;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuAdm {

	private static final long serialVersionUID = 1L;
	
	private String Id;// required string 广告ID
	private String Type;// required int 物料类型 0. TEXT 1. IMAGE 2. TEXT_ICON 3. VIDEO
	private Integer Interaction_type;// required int 广告交互类型 0. NO_INTERACT 1. SURFING(打开网页) 2. DOWNLOAD(下载)
	private String Title;// optional string 广告标题
	private String Description1;// optional string 广告描述1
	private String Description2;// optional string 广告描述2
	private String Image_url;// optional string 图⽚片物料URL
	private JiekuSize Image_size;// optional size object 图片尺寸
	private String Logo_url;// optional string 图标物料URL
	private JiekuSize Logo_size;// optional size object 图标大小
	private String Video_src;// optional string 视频广告物料地址(可能是302地址，视频广告这个地址肯定存在)
	private Integer Video_duration;// optional int 视频播放时⻓长，单位秒
	private String Click_url;// required string 点击地址(需要拼上点击坐标，见2.5)
	private List<String> Impression_log_url;// required array of string 展现日志URL
	private List<JiekuAdVideo> Video_impression_url;// 视频广告播放监控(视频广告必须实现)
	private List<String> Click_monitor_url;// optional array of string 点击监控URL
	private String AppName;// optional string 应⽤用名称
	private String Package;// optional string 应⽤用包名
	private List<String> AppDownload;// optional array of string app广告下载完成监控
	private List<String> AppInstall;// optional array of string app广告安装完成监控
	private List<String> AppActive;// optional array of string app广告激活监控
	private String AdName;// optional string 推广名称(仅限于原生广告)
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Integer getInteraction_type() {
		return Interaction_type;
	}

	public void setInteraction_type(Integer interaction_type) {
		Interaction_type = interaction_type;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription1() {
		return Description1;
	}

	public void setDescription1(String description1) {
		Description1 = description1;
	}

	public String getDescription2() {
		return Description2;
	}

	public void setDescription2(String description2) {
		Description2 = description2;
	}

	public String getImage_url() {
		return Image_url;
	}

	public void setImage_url(String image_url) {
		Image_url = image_url;
	}

	public JiekuSize getImage_size() {
		return Image_size;
	}

	public void setImage_size(JiekuSize image_size) {
		Image_size = image_size;
	}

	public String getLogo_url() {
		return Logo_url;
	}

	public void setLogo_url(String logo_url) {
		Logo_url = logo_url;
	}

	public JiekuSize getLogo_size() {
		return Logo_size;
	}

	public void setLogo_size(JiekuSize logo_size) {
		Logo_size = logo_size;
	}

	public String getVideo_src() {
		return Video_src;
	}

	public void setVideo_src(String video_src) {
		Video_src = video_src;
	}

	public Integer getVideo_duration() {
		return Video_duration;
	}

	public void setVideo_duration(Integer video_duration) {
		Video_duration = video_duration;
	}

	public String getClick_url() {
		return Click_url;
	}

	public void setClick_url(String click_url) {
		Click_url = click_url;
	}

	public List<String> getImpression_log_url() {
		return Impression_log_url;
	}

	public void setImpression_log_url(List<String> impression_log_url) {
		Impression_log_url = impression_log_url;
	}

	public List<JiekuAdVideo> getVideo_impression_url() {
		return Video_impression_url;
	}

	public void setVideo_impression_url(List<JiekuAdVideo> video_impression_url) {
		Video_impression_url = video_impression_url;
	}

	public List<String> getClick_monitor_url() {
		return Click_monitor_url;
	}

	public void setClick_monitor_url(List<String> click_monitor_url) {
		Click_monitor_url = click_monitor_url;
	}

	public String getAppName() {
		return AppName;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}

	public String getPackage() {
		return Package;
	}

	public void setPackage(String package1) {
		Package = package1;
	}

	public List<String> getAppDownload() {
		return AppDownload;
	}

	public void setAppDownload(List<String> appDownload) {
		AppDownload = appDownload;
	}

	public List<String> getAppInstall() {
		return AppInstall;
	}

	public void setAppInstall(List<String> appInstall) {
		AppInstall = appInstall;
	}

	public List<String> getAppActive() {
		return AppActive;
	}

	public void setAppActive(List<String> appActive) {
		AppActive = appActive;
	}

	public String getAdName() {
		return AdName;
	}

	public void setAdName(String adName) {
		AdName = adName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
