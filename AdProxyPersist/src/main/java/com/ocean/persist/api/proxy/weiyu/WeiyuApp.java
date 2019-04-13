package com.ocean.persist.api.proxy.weiyu;

import java.util.List;

public class WeiyuApp {
	private String appid;//	string	是	应用id
	private String appname	;//string	是	应用名称
	private String icon;//	string	是	应用图标
	private String versioncode;//	string	是	应用版本号
	private String versionname	;//string	是	应用版本号名称
	private String size;//	number	是	应用大小
	private String apk	;//string	是	应用包名
	private String href_download	;//string	是	应用下载链接
	private List<String> rpt_ss;//	string[]	是	曝光上报地址
	private List<String> rpt_cd	;//string[]	是	点击下载开始上报地址
	private List<String> rpt_dc	;//string[]	是	下载完成上报地址
	private List<String> rpt_ic	;//string[]	是	安装完成上报地址
	private List<String> rpt_ac	;//string[]	是	成功激活上报地址
	private String md5	;//string	否	应用包md5
	private int expiration_time	;//int	否	下载地址过期时间
	public String getAppid() {
		return appid;
	}
	public String getAppname() {
		return appname;
	}
	public String getIcon() {
		return icon;
	}
	public String getVersioncode() {
		return versioncode;
	}
	public String getVersionname() {
		return versionname;
	}
	public String getSize() {
		return size;
	}
	public String getApk() {
		return apk;
	}
	public String getHref_download() {
		return href_download;
	}
	public List<String> getRpt_ss() {
		return rpt_ss;
	}
	public List<String> getRpt_cd() {
		return rpt_cd;
	}
	public List<String> getRpt_dc() {
		return rpt_dc;
	}
	public List<String> getRpt_ic() {
		return rpt_ic;
	}
	public List<String> getRpt_ac() {
		return rpt_ac;
	}
	public String getMd5() {
		return md5;
	}
	public int getExpiration_time() {
		return expiration_time;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setApk(String apk) {
		this.apk = apk;
	}
	public void setHref_download(String href_download) {
		this.href_download = href_download;
	}
	public void setRpt_ss(List<String> rpt_ss) {
		this.rpt_ss = rpt_ss;
	}
	public void setRpt_cd(List<String> rpt_cd) {
		this.rpt_cd = rpt_cd;
	}
	public void setRpt_dc(List<String> rpt_dc) {
		this.rpt_dc = rpt_dc;
	}
	public void setRpt_ic(List<String> rpt_ic) {
		this.rpt_ic = rpt_ic;
	}
	public void setRpt_ac(List<String> rpt_ac) {
		this.rpt_ac = rpt_ac;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public void setExpiration_time(int expiration_time) {
		this.expiration_time = expiration_time;
	}
}
