package com.ocean.persist.api.proxy.souying;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;


public class SouyingAdMaterial  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String Id;// 广告id
	private Integer Type;// 素材类型: 1 图片
	private String Interaction_type;// Html广告素材内容
	private String Title;// native广告内容
	private String Description1;// 广告描述1
	private String Description2;// 广告描述2
	private String Image_url;// 图⽚素材URL
	private String Logo_url;// 图标素材URL
	private String Click_url;// 点击地址
	private List<String> Impression_log_url;// 展示监控URL
	private List<String> Click_monitor_url;// 点击监控URL
	private String AppName;// 应⽚名称
	private String Package;// 应⽚包名
	private  List<String> AppDownload;// app类广告下载完成监控url
	private  List<String> AppInstall;// app类广告安装完成监控url
	private  List<String> AppActive;// app类广告激活监控url
	private SouyingAdImageSize Image_size;// 素材图片尺寸
	private String Timestamp;// 时间戳
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Integer getType() {
		return Type;
	}
	public void setType(Integer type) {
		Type = type;
	}
	public String getInteraction_type() {
		return Interaction_type;
	}
	public void setInteraction_type(String interaction_type) {
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
	public String getLogo_url() {
		return Logo_url;
	}
	public void setLogo_url(String logo_url) {
		Logo_url = logo_url;
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
	public SouyingAdImageSize getImage_size() {
		return Image_size;
	}
	public void setImage_size(SouyingAdImageSize image_size) {
		Image_size = image_size;
	}
	public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
}
