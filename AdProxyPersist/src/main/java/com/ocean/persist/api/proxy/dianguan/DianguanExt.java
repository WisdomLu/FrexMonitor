package com.ocean.persist.api.proxy.dianguan;

public class DianguanExt {
	private String appname;// optional string
	private String ppmd5 ;//optional string app 下载类的md5 值
	private String apppackage;// optional string app 下载类应用包名
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getPpmd5() {
		return ppmd5;
	}
	public void setPpmd5(String ppmd5) {
		this.ppmd5 = ppmd5;
	}
	public String getApppackage() {
		return apppackage;
	}
	public void setApppackage(String apppackage) {
		this.apppackage = apppackage;
	}
}
