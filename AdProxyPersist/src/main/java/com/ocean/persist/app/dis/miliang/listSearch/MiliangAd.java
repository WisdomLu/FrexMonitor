package com.ocean.persist.app.dis.miliang.listSearch;

import java.util.List;

public class MiliangAd {
	private int interaction_type;// int 交 互 类型 3.app 下载
	private String image_url;// string 图 片 物料 URL
	private String Icon_url ;//String 图 片 IConUrl
	private String title ;//string 广 告 标题
	private String desc;
	private String pkgname;
	private MiliangTrack click_url ;
	private List<MiliangTrack>  impression_log_url ;
	private List<MiliangTrack>  click_monitor_url ;
	private List<MiliangTrack>  download_start_url ;
	private List<MiliangTrack>  download_complete_url;
	private List<MiliangTrack>  install_start_url ;
	private List<MiliangTrack>  install_complete_url;
	private List<MiliangTrack>  start_app_url;
	private int width ;
	private int height ;
	public int getInteraction_type() {
		return interaction_type;
	}
	public String getImage_url() {
		return image_url;
	}
	public String getIcon_url() {
		return Icon_url;
	}
	public String getTitle() {
		return title;
	}
	public MiliangTrack getClick_url() {
		return click_url;
	}
	public List<MiliangTrack> getImpression_log_url() {
		return impression_log_url;
	}
	public List<MiliangTrack> getClick_monitor_url() {
		return click_monitor_url;
	}
	public List<MiliangTrack> getDownload_start_url() {
		return download_start_url;
	}
	public List<MiliangTrack> getDownload_complete_url() {
		return download_complete_url;
	}
	public List<MiliangTrack> getInstall_start_url() {
		return install_start_url;
	}
	public List<MiliangTrack> getInstall_complete_url() {
		return install_complete_url;
	}
	public List<MiliangTrack> getStart_app_url() {
		return start_app_url;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public void setIcon_url(String icon_url) {
		Icon_url = icon_url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setClick_url(MiliangTrack click_url) {
		this.click_url = click_url;
	}
	public void setImpression_log_url(List<MiliangTrack> impression_log_url) {
		this.impression_log_url = impression_log_url;
	}
	public void setClick_monitor_url(List<MiliangTrack> click_monitor_url) {
		this.click_monitor_url = click_monitor_url;
	}
	public void setDownload_start_url(List<MiliangTrack> download_start_url) {
		this.download_start_url = download_start_url;
	}
	public void setDownload_complete_url(List<MiliangTrack> download_complete_url) {
		this.download_complete_url = download_complete_url;
	}
	public void setInstall_start_url(List<MiliangTrack> install_start_url) {
		this.install_start_url = install_start_url;
	}
	public void setInstall_complete_url(List<MiliangTrack> install_complete_url) {
		this.install_complete_url = install_complete_url;
	}
	public void setStart_app_url(List<MiliangTrack> start_app_url) {
		this.start_app_url = start_app_url;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDesc() {
		return desc;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
}
