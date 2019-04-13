package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2RespApp {
	private String app_name	;//应用名称	string		否	
	private String pkg_name;//	应用包名	string		否	
	private String category	;//分类	string		否	
	private int size	;//大小	number		否	
	private String icon;//	ICON	string		否	
	private String version	;//版本	string		否	
	public String getApp_name() {
		return app_name;
	}
	public String getPkg_name() {
		return pkg_name;
	}
	public String getCategory() {
		return category;
	}
	public int getSize() {
		return size;
	}
	public String getIcon() {
		return icon;
	}
	public String getVersion() {
		return version;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public void setPkg_name(String pkg_name) {
		this.pkg_name = pkg_name;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
