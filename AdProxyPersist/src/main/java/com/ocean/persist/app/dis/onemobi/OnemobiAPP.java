package com.ocean.persist.app.dis.onemobi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月8日 
      @version 1.0 
 */
public class OnemobiAPP extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2150093090900845632L;
	private String appid;//	是	String	应用ID
	private String icon;//	是	String	应用icon
	private String appname;//	是	String	应用名称
	private String apk	;//是	String	应用包名
	private String size;//	是	String	应用大小
	private String versioncode	;//是	String	应用版本号
	private String versionname	;//是	String	应用版本名称
	private String href_download	;//是	String	应用下载链接
	private List<String> rpt_ss	;//是	Array	应用展示曝光上报
	private List<String> rpt_cd	;//是	Array	点击开始下载上报
	private List<String> rpt_dc	;//是	Array	下载完成上报
	private List<String> rpt_ic	;//是	Array	安装完成上报
	private List<String> rpt_ac	;//是	Array	成功激活上报
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getApk() {
		return apk;
	}
	public void setApk(String apk) {
		this.apk = apk;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}
	public String getVersionname() {
		return versionname;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public String getHref_download() {
		return href_download;
	}
	public void setHref_download(String href_download) {
		this.href_download = href_download;
	}
	public List<String> getRpt_ss() {
		return rpt_ss;
	}
	public void setRpt_ss(List<String> rpt_ss) {
		this.rpt_ss = rpt_ss;
	}
	public List<String> getRpt_cd() {
		return rpt_cd;
	}
	public void setRpt_cd(List<String> rpt_cd) {
		this.rpt_cd = rpt_cd;
	}
	public List<String> getRpt_dc() {
		return rpt_dc;
	}
	public void setRpt_dc(List<String> rpt_dc) {
		this.rpt_dc = rpt_dc;
	}
	public List<String> getRpt_ic() {
		return rpt_ic;
	}
	public void setRpt_ic(List<String> rpt_ic) {
		this.rpt_ic = rpt_ic;
	}
	public List<String> getRpt_ac() {
		return rpt_ac;
	}
	public void setRpt_ac(List<String> rpt_ac) {
		this.rpt_ac = rpt_ac;
	}
}
