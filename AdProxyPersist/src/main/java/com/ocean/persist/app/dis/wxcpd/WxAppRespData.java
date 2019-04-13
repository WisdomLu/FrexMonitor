package com.ocean.persist.app.dis.wxcpd;

import java.util.List;

public class WxAppRespData {
	
	private String fileUrl;
	private String pkg;//": "com.ppsmGame.newOne.uc",
	private List<WxTracking> app_tracking;
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public List<WxTracking> getApp_tracking() {
		return app_tracking;
	}
	public void setApp_tracking(List<WxTracking> app_tracking) {
		this.app_tracking = app_tracking;
	}
	
}
