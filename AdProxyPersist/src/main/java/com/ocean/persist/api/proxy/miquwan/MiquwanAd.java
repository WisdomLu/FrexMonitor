package com.ocean.persist.api.proxy.miquwan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月31日 
      @version 1.0 
 */
public class MiquwanAd extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6591453517507322366L;
	private int ad_type;
	private int width;
	private int height;
	private String creative_type;
	private String htmlStr;
	private String img_url;
	private String click_url;
	private int ac_type;
	private int click_position;
	private String app_package;
	private String app_size;
	private String title;
	private String description;
	private List<String> impress_notice_urls;
	private List<String> click_notice_urls;
	private List<String> before_impress_notice_urls;
	private List<String> install_notice_urls;
	private String img_sm;
	private String img_url2;
	private String img_url3;
	private String img_url4;
	private List<String> download_start_notice_urls;
	private List<String> download_notice_urls;
	public int getAd_type() {
		return ad_type;
	}
	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
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
	public String getCreative_type() {
		return creative_type;
	}
	public void setCreative_type(String creative_type) {
		this.creative_type = creative_type;
	}
	public String getHtmlStr() {
		return htmlStr;
	}
	public void setHtmlStr(String htmlStr) {
		this.htmlStr = htmlStr;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getClick_url() {
		return click_url;
	}
	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}
	public int getAc_type() {
		return ac_type;
	}
	public void setAc_type(int ac_type) {
		this.ac_type = ac_type;
	}
	public int getClick_position() {
		return click_position;
	}
	public void setClick_position(int click_position) {
		this.click_position = click_position;
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
	public List<String> getImpress_notice_urls() {
		return impress_notice_urls;
	}
	public void setImpress_notice_urls(List<String> impress_notice_urls) {
		this.impress_notice_urls = impress_notice_urls;
	}
	public List<String> getClick_notice_urls() {
		return click_notice_urls;
	}
	public void setClick_notice_urls(List<String> click_notice_urls) {
		this.click_notice_urls = click_notice_urls;
	}
	public List<String> getBefore_impress_notice_urls() {
		return before_impress_notice_urls;
	}
	public void setBefore_impress_notice_urls(
			List<String> before_impress_notice_urls) {
		this.before_impress_notice_urls = before_impress_notice_urls;
	}
	public List<String> getInstall_notice_urls() {
		return install_notice_urls;
	}
	public void setInstall_notice_urls(List<String> install_notice_urls) {
		this.install_notice_urls = install_notice_urls;
	}
	public String getImg_sm() {
		return img_sm;
	}
	public void setImg_sm(String img_sm) {
		this.img_sm = img_sm;
	}
	public String getImg_url2() {
		return img_url2;
	}
	public void setImg_url2(String img_url2) {
		this.img_url2 = img_url2;
	}
	public String getImg_url3() {
		return img_url3;
	}
	public void setImg_url3(String img_url3) {
		this.img_url3 = img_url3;
	}
	public String getImg_url4() {
		return img_url4;
	}
	public void setImg_url4(String img_url4) {
		this.img_url4 = img_url4;
	}
	public List<String> getDownload_start_notice_urls() {
		return download_start_notice_urls;
	}
	public void setDownload_start_notice_urls(
			List<String> download_start_notice_urls) {
		this.download_start_notice_urls = download_start_notice_urls;
	}
	public List<String> getDownload_notice_urls() {
		return download_notice_urls;
	}
	public void setDownload_notice_urls(List<String> download_notice_urls) {
		this.download_notice_urls = download_notice_urls;
	}
}
