package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2App {
	private String app_pkg_name	;//应用包名	string		是	
	private String app_version	;//应用版本	String		是	
	public String getApp_pkg_name() {
		return app_pkg_name;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_pkg_name(String app_pkg_name) {
		this.app_pkg_name = app_pkg_name;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
}
