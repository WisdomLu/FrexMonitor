package com.ocean.persist.app.dis.haiqibing.pkgSearch;

import com.ocean.persist.app.dis.AdDisParams;

public class HaiqibingPkgSchReq   implements AdDisParams{

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String imei;// String 是手机的imei(deviceId)
	private String androidId;//  String 是手机的androidId
	private String macAddress ;// String 是手机mac地址
	private String manufacture ;// String 是手机的厂商
	private String mode ;// String 是手机的机型
	private String channel ;// String 是渠道标示
	private String packageName ;// String 是应用包名
	private String ip ;// String 是手机的IP地址
	private String serialno;//  String 是设备序列号[新增]
	private String brand ;// String 是设备品牌[新增]
	private String carrier;//  String 是运营商编码 如：46001[新增]
	private double dip;//  double 是屏幕密度[新增]
	private int sw ;// int 是屏幕宽度[新增]
	private int sh ;// int 是屏幕高度[新增]
	private String userAgent;//  String 是设备的user-agent[新增]
	private int os ;// int 是手机的系统版本号(例如：25)[新增]
	private String osv ;// String 是手机的系统版本名称(例如：8.0)[新增]
	public String getImei() {
		return imei;
	}
	public String getAndroidId() {
		return androidId;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public String getManufacture() {
		return manufacture;
	}
	public String getMode() {
		return mode;
	}
	public String getChannel() {
		return channel;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getIp() {
		return ip;
	}
	public String getSerialno() {
		return serialno;
	}
	public String getBrand() {
		return brand;
	}
	public String getCarrier() {
		return carrier;
	}
	public double getDip() {
		return dip;
	}
	public int getSw() {
		return sw;
	}
	public int getSh() {
		return sh;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public int getOs() {
		return os;
	}
	public String getOsv() {
		return osv;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public void setDip(double dip) {
		this.dip = dip;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
}
