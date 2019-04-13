package com.ocean.persist.api.proxy.wanyueV2;

import java.util.List;

public class WanyueV2AdPullReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6665956522112932702L;
	private String appid;//	appid	string	接入方appid	是	
	private int timestamp;//		时间戳数值	number	单位，秒	是	
	private int randnum	;//	随机数	number		是	
	private String version;//		版本号	string		是	2.0.7
	private String extend_data;//		扩展参数	string	业务扩展参数	否	
	private String extra_data;//		回传参数	string	公用回传参数	否	
	private WanyueV2Device device;//		设备信息	object		是	
	private WanyueV2App app	;//	应用信息	object		是	
	private List<WanyueV2Adspace> adspace	;//	广告位信息	array	参见4.1.1.1.3 	是	

	public String getAppid() {
		return appid;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public int getRandnum() {
		return randnum;
	}
	public String getVersion() {
		return version;
	}
	public String getExtend_data() {
		return extend_data;
	}
	public String getExtra_data() {
		return extra_data;
	}
	public WanyueV2Device getDevice() {
		return device;
	}
	public WanyueV2App getApp() {
		return app;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public void setRandnum(int randnum) {
		this.randnum = randnum;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setExtend_data(String extend_data) {
		this.extend_data = extend_data;
	}
	public void setExtra_data(String extra_data) {
		this.extra_data = extra_data;
	}
	public void setDevice(WanyueV2Device device) {
		this.device = device;
	}
	public void setApp(WanyueV2App app) {
		this.app = app;
	}
	public List<WanyueV2Adspace> getAdspace() {
		return adspace;
	}
	public void setAdspace(List<WanyueV2Adspace> adspace) {
		this.adspace = adspace;
	}
}
