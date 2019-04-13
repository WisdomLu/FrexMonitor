package com.ocean.persist.app.dis.appSearch360T2.appRcmdSearch;

import com.ocean.persist.app.dis.appSearch360T2.BaseSearch360T2Request;

public class AppRcmd360T2Request extends BaseSearch360T2Request{
	private String recom_appname;//	string	应用名称	去哪儿	Y	
	private String recom_apppkg;//	string	包名	com.Qunar	N	
	public String getRecom_appname() {
		return recom_appname;
	}
	public void setRecom_appname(String recom_appname) {
		this.recom_appname = recom_appname;
	}
	public String getRecom_apppkg() {
		return recom_apppkg;
	}
	public void setRecom_apppkg(String recom_apppkg) {
		this.recom_apppkg = recom_apppkg;
	}
}
