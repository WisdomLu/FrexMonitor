package com.ocean.persist.api.proxy.wanka_v1;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class WankaApp {
	private String app_id;/*
	string
	是
	应用 ID，玩咖 DAP平台提供的 app_id*/
	private String app_version;/*
	string
	是
	应用版本，格式：a.b.c
	(a,b,c取值范围均为int值取值范围)*/
	private String package_name;/*
	string
	是
	应用包名*/
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
}
