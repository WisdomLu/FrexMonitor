package com.ocean.persist.api.proxy.youken;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月6日 
      @version 1.0 
 */
public class YoukenApp {
	private String app_id	;//string		是	应用ID，ssp平台提供的app_id
	private String app_version	;//string		是	应用版本
	private String package_name	;//String		是	应用包名
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
