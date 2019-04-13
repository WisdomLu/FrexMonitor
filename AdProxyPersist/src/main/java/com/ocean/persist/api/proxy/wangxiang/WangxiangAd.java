package com.ocean.persist.api.proxy.wangxiang;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangAd {
	private String title;//	推广标题（将弃用），中文为UTF-8编码，下载类为app名（优先从brand_name取值），非下载类为广告标题（优先从ad_title取值）
	private String ad_title	;//广告推广标题，中文为UTF-8编码
	private String brand_name;//	广告品牌名称，下载类则为app名称（如手机XXX），非下载类则为品牌名称（如 XXX）
	private String description;//	广告描述，中文为UTF-8编码
	private String image_src;//	广告图片地址 
	private int material_width	;//图片的宽度；如果是图文或文本，则不会填充此字段
	private int material_height;//	表示图片的高度；如果是图文或文本，则不会填充此字段
	private String icon_src;//	广告图标地址，单个广告可能有多张图标返回。返回的图标尺寸为标准尺寸：90*90像素
	private int creative_type;/*	创意类型：
	0.  无创意类型；
	1.	纯文字（TEXT）。一般由title、description构成；
	2.	纯图片（IMAGE）。一般由单张或多张image_src构成；
	3.	图文（混合广告）。一般由单张icon_src和title、description构成；
	4.	不返回创意类型
	5.	 HTML
	6.	视频广告
	7.	奖励视频广告*/
	private int interaction_type;/*	交互类型：
	0.  无动作；
	1.  打开网页； 
	2.  点击下载； 
	3.  android应用市场下载，
	4.  ios appstore下载；
	5.  下载
	6.  html
	7.  下载*/
	private String app_package;//	下载类广告包名；对于下载类广告，可以使用此字段判断当前设备是否已经安装此应用，如已安装，在用户对当前广告发生点击行为后，可以执行打开应用操作，并在后台通过click_url汇报此次点击，不再进行302跳转
	private String app_size	;//	下载类广告 应用文件大小
	//win_notice_url	展示回调地址（弃用）
	//click_url	点击行为地址（弃用）
	private List<String> arrDownloadTrackUrl;//		开始下载回调地址（一维数组）
	private List<String> arrDownloadedTrakUrl;//		下载完成回调地址（一维数组）
	private List<String> arrIntallTrackUrl	;//	开始安装回调地址（一维数组）
	private List<String> arrIntalledTrackUrl	;//	安装完成回调地址（一维数组）
	private String strLinkUrl;//		落地页
	private List<String> other_notice_url;//		展示回调地址（一维数组）
	private List<String> other_click_url	;//	点击回调地址（一维数组）
	private String mob_adlogo	;//	广告商图标
	private String mob_adtext;//		广告图标
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAd_title() {
		return ad_title;
	}
	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage_src() {
		return image_src;
	}
	public void setImage_src(String image_src) {
		this.image_src = image_src;
	}
	public int getMaterial_width() {
		return material_width;
	}
	public void setMaterial_width(int material_width) {
		this.material_width = material_width;
	}
	public int getMaterial_height() {
		return material_height;
	}
	public void setMaterial_height(int material_height) {
		this.material_height = material_height;
	}
	public String getIcon_src() {
		return icon_src;
	}
	public void setIcon_src(String icon_src) {
		this.icon_src = icon_src;
	}
	public int getCreative_type() {
		return creative_type;
	}
	public void setCreative_type(int creative_type) {
		this.creative_type = creative_type;
	}
	public int getInteraction_type() {
		return interaction_type;
	}
	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}
	public String getApp_package() {
		return app_package;
	}
	public void setApp_package(String app_package) {
		this.app_package = app_package;
	}
	public String getApp_size() {
		return app_size;
	}
	public void setApp_size(String app_size) {
		this.app_size = app_size;
	}
	public List<String> getArrDownloadTrackUrl() {
		return arrDownloadTrackUrl;
	}
	public void setArrDownloadTrackUrl(List<String> arrDownloadTrackUrl) {
		this.arrDownloadTrackUrl = arrDownloadTrackUrl;
	}
	public List<String> getArrDownloadedTrakUrl() {
		return arrDownloadedTrakUrl;
	}
	public void setArrDownloadedTrakUrl(List<String> arrDownloadedTrakUrl) {
		this.arrDownloadedTrakUrl = arrDownloadedTrakUrl;
	}
	public List<String> getArrIntallTrackUrl() {
		return arrIntallTrackUrl;
	}
	public void setArrIntallTrackUrl(List<String> arrIntallTrackUrl) {
		this.arrIntallTrackUrl = arrIntallTrackUrl;
	}
	public List<String> getArrIntalledTrackUrl() {
		return arrIntalledTrackUrl;
	}
	public void setArrIntalledTrackUrl(List<String> arrIntalledTrackUrl) {
		this.arrIntalledTrackUrl = arrIntalledTrackUrl;
	}
	public String getStrLinkUrl() {
		return strLinkUrl;
	}
	public void setStrLinkUrl(String strLinkUrl) {
		this.strLinkUrl = strLinkUrl;
	}
	public List<String> getOther_notice_url() {
		return other_notice_url;
	}
	public void setOther_notice_url(List<String> other_notice_url) {
		this.other_notice_url = other_notice_url;
	}
	public List<String> getOther_click_url() {
		return other_click_url;
	}
	public void setOther_click_url(List<String> other_click_url) {
		this.other_click_url = other_click_url;
	}
	public String getMob_adlogo() {
		return mob_adlogo;
	}
	public void setMob_adlogo(String mob_adlogo) {
		this.mob_adlogo = mob_adlogo;
	}
	public String getMob_adtext() {
		return mob_adtext;
	}
	public void setMob_adtext(String mob_adtext) {
		this.mob_adtext = mob_adtext;
	}

}
