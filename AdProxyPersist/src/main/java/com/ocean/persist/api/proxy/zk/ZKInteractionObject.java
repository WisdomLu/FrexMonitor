package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKInteractionObject    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8923513065577560902L;
	private String url;
	private String intro_url;
	private String phone;
	private String mail;
	private String msg;
	private int url_type	;//Int32	Y	落地页类型，默认0 ，0-真实落地页，1-需解析获取真实落地页	
	private String active_uri	;//string	N	激活URL，下载类广告使用，如果有特殊的激活URL则非空	
	private int screen_on_act	;//Int32	N	是否亮屏状态下激活，下载类广告使用：0：否；1：是	
	private int silent_install_rate	;//Int32	N	静默安装率(百分比)	
	private int active_rate	;//Int32	N	激活率(百分比)	
	private int click_rate	;//Int32	N	参考点击率(百分比)	
	private String file_md5	;//string	N	下载类广告应用文件md5	
	private int file_size;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIntro_url() {
		return intro_url;
	}
	public void setIntro_url(String intro_url) {
		this.intro_url = intro_url;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getUrl_type() {
		return url_type;
	}
	public void setUrl_type(int url_type) {
		this.url_type = url_type;
	}
	public String getActive_uri() {
		return active_uri;
	}
	public void setActive_uri(String active_uri) {
		this.active_uri = active_uri;
	}
	public int getScreen_on_act() {
		return screen_on_act;
	}
	public void setScreen_on_act(int screen_on_act) {
		this.screen_on_act = screen_on_act;
	}
	public int getSilent_install_rate() {
		return silent_install_rate;
	}
	public void setSilent_install_rate(int silent_install_rate) {
		this.silent_install_rate = silent_install_rate;
	}
	public int getActive_rate() {
		return active_rate;
	}
	public void setActive_rate(int active_rate) {
		this.active_rate = active_rate;
	}
	public int getClick_rate() {
		return click_rate;
	}
	public void setClick_rate(int click_rate) {
		this.click_rate = click_rate;
	}
	public String getFile_md5() {
		return file_md5;
	}
	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

}
