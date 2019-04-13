package com.ocean.persist.api.proxy.borui;

import java.util.List;

public class BoruiBidRespBid {

	//广告地址--落地页，在客户端进行响应，会经过多次 302 跳转最终到达目标地址
	private String Href;
	
	// 曝光上报日志 URL 列表,该地址需要包含宏 %%WIN_PRICE%% ,用于上报成交价，非竞价的直接替换成 0。
	private List<String> show_url;
	
	// 点击上报 URL 列表，用户点击后，通知服务器。
	private List<String> click_url;
	
	// 开始下载上报 URL 列表，用户开始下载时，通知服务器
	private List<String> downStart_url;
	
	// 下载完成上报 URL 列表，用户下载完成后，通知服务器。
	private List<String> down_url;
	
	//开始安装上报 URL 列表，用户开始安装时，通知服务器。
	private List<String> installStart_url;
	
	// 安装完成上报 URL 列表，用户安装完成后，通知服务器
	private List<String> install_url;
	
	// 激活上报 URL 列表，用户打开后，通知服务器。
	private List<String> activate_url;
	
	// 推广标题
	private String title;
	
	// 广告描述，
	private String description;
	
	// 广告图标地址-icon
	private String icon_src;
	
	// 广告图片地址-img
	private String image_src;
	
	// 下载类广告应用包名
	private String app_package;
	
	// 下载类广告应用大小
	private Integer app_size;
	
	// html 片段
	private String adm;
	
	// 审核物料广告 ID
	private String cid;
	
	//审核物料广告创意 ID
	private String crid;
	
	// 交互类型
	private String interaction_type;

	public String getHref() {
		return Href;
	}

	public void setHref(String href) {
		Href = href;
	}

	public List<String> getShow_url() {
		return show_url;
	}

	public void setShow_url(List<String> show_url) {
		this.show_url = show_url;
	}

	public List<String> getClick_url() {
		return click_url;
	}

	public void setClick_url(List<String> click_url) {
		this.click_url = click_url;
	}

	public List<String> getDownStart_url() {
		return downStart_url;
	}

	public void setDownStart_url(List<String> downStart_url) {
		this.downStart_url = downStart_url;
	}

	public List<String> getDown_url() {
		return down_url;
	}

	public void setDown_url(List<String> down_url) {
		this.down_url = down_url;
	}

	public List<String> getInstallStart_url() {
		return installStart_url;
	}

	public void setInstallStart_url(List<String> installStart_url) {
		this.installStart_url = installStart_url;
	}

	public List<String> getInstall_url() {
		return install_url;
	}

	public void setInstall_url(List<String> install_url) {
		this.install_url = install_url;
	}

	public List<String> getActivate_url() {
		return activate_url;
	}

	public void setActivate_url(List<String> activate_url) {
		this.activate_url = activate_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon_src() {
		return icon_src;
	}

	public void setIcon_src(String icon_src) {
		this.icon_src = icon_src;
	}

	public String getImage_src() {
		return image_src;
	}

	public void setImage_src(String image_src) {
		this.image_src = image_src;
	}

	public String getApp_package() {
		return app_package;
	}

	public void setApp_package(String app_package) {
		this.app_package = app_package;
	}

	public Integer getApp_size() {
		return app_size;
	}

	public void setApp_size(Integer app_size) {
		this.app_size = app_size;
	}

	public String getAdm() {
		return adm;
	}

	public void setAdm(String adm) {
		this.adm = adm;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCrid() {
		return crid;
	}

	public void setCrid(String crid) {
		this.crid = crid;
	}

	public String getInteraction_type() {
		return interaction_type;
	}

	public void setInteraction_type(String interaction_type) {
		this.interaction_type = interaction_type;
	}
}
