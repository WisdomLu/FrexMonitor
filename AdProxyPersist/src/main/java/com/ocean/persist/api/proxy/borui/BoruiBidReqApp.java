package com.ocean.persist.api.proxy.borui;

public class BoruiBidReqApp {

	//App 包名
	private String App_packagename;
	
	//App 名称
	private String appname;
	
	//app 分类
	private String cat;

	public String getApp_packagename() {
		return App_packagename;
	}

	public void setApp_packagename(String app_packagename) {
		App_packagename = app_packagename;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}
}
