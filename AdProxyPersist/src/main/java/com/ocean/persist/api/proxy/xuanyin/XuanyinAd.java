package com.ocean.persist.api.proxy.xuanyin;

import java.util.List;

public class XuanyinAd {
	private String ad_id;// 是 string 广告唯一id 
	private int type ;//是 int 广告类型 1:Banner、2:信息流、3:开屏 、4:插屏 
	private String html ;//否 string HTML代码，当前广告以html形式返回时有值，请使用webview渲染 
	private String banner_imgsrc;//_imgsrc 否 string banner类图片路径，type=1、3、4并且html非空时有值 
	private int width ;//否 int 图片宽度 单位px 
	private int height ;//否 int 图片高度 单位px 
	private String title ;//否 string 广告标题 
	private String desc ;//否 string 广告描述 
	private List<String> native_imgs;// ;//否 string array 原生信息流类图片地址，type=2时有值 
	private String icon ;//否 string 广告图标地址 
	private int close_control ;//是 int 广告关闭控制 0：不允许、1允许 
	private int min_showtime ;//是 int 广告最小展示时间,毫秒位单位,当close_control=1时生效 
	private int click_action ;//是 int 广告点击行为 0：未知、 1：页面跳转(打开WebUrl)、 2：下载(下载应用) 
	private String link ;//否 string 广告点击链接 (click_action=1为跳转链接， click_action=2为下载链接) 
	private String deeplink ;//否 string deeplink地址，此字段非空情况 需要优先处理deeplink，如果deeplink失败再使用link字段 
	private String app_name ;//否 string app名称 (只在click_action=2时可能有值)) 
	private String package_name ;//否 string app包名(只在click_action=2时可能有值) 
	private String app_icon ;//否 string app图标地址(只在click_action=2时可能有值) 
	private String app_size ;//否 string pp大小 单位kb(只在click_action=2时可能有值) 
	private List<String> exposure_tracking ;//是 string 广告曝光跟踪地址 
	private List<String> click_tracking ;//否 string 广告点击跟踪地址 
	private XuanyinExtendTracking extend_tracking ;//否 ExtendTracking array for object 其他扩展跟踪地址 
	public String getAd_id() {
		return ad_id;
	}
	public int getType() {
		return type;
	}
	public String getHtml() {
		return html;
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getTitle() {
		return title;
	}
	public String getDesc() {
		return desc;
	}
	public String getIcon() {
		return icon;
	}
	public int getClose_control() {
		return close_control;
	}
	public int getMin_showtime() {
		return min_showtime;
	}
	public int getClick_action() {
		return click_action;
	}
	public String getLink() {
		return link;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public String getApp_name() {
		return app_name;
	}
	public String getPackage_name() {
		return package_name;
	}
	public String getApp_icon() {
		return app_icon;
	}
	public String getApp_size() {
		return app_size;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setClose_control(int close_control) {
		this.close_control = close_control;
	}
	public void setMin_showtime(int min_showtime) {
		this.min_showtime = min_showtime;
	}
	public void setClick_action(int click_action) {
		this.click_action = click_action;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public void setApp_icon(String app_icon) {
		this.app_icon = app_icon;
	}
	public void setApp_size(String app_size) {
		this.app_size = app_size;
	}
	public String getBanner_imgsrc() {
		return banner_imgsrc;
	}
	public void setBanner_imgsrc(String banner_imgsrc) {
		this.banner_imgsrc = banner_imgsrc;
	}
	public List<String> getNative_imgs() {
		return native_imgs;
	}
	public void setNative_imgs(List<String> native_imgs) {
		this.native_imgs = native_imgs;
	}
	public List<String> getExposure_tracking() {
		return exposure_tracking;
	}
	public List<String> getClick_tracking() {
		return click_tracking;
	}
	public XuanyinExtendTracking getExtend_tracking() {
		return extend_tracking;
	}
	public void setExposure_tracking(List<String> exposure_tracking) {
		this.exposure_tracking = exposure_tracking;
	}
	public void setClick_tracking(List<String> click_tracking) {
		this.click_tracking = click_tracking;
	}
	public void setExtend_tracking(XuanyinExtendTracking extend_tracking) {
		this.extend_tracking = extend_tracking;
	}

}
