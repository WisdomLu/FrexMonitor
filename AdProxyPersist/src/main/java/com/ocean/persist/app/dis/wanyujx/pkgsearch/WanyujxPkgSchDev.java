package com.ocean.persist.app.dis.wanyujx.pkgsearch;

public class WanyujxPkgSchDev {
	private String idfa ;//是IOS 设备唯一标识码
	private String idfaMd5  ;//可替补idfa 建议优先使用idfa，

	private String imei  ;//是安卓设备唯一标识码
	private String imeiMd5  ;//
	private String mac  ;//
	private String androidId  ;//是安卓设备ID
	private String model ;// 是设备型号
	private String vendor  ;//是设备厂商
	private int screenWidth  ;//是设备屏幕宽度
	private int screenHeight  ;//是设备屏幕高度
	private int osType  ;//是操作系统类型1— ANDRIOD 2— IOS
	private String osVersion  ;//是操作系统版本格式：a.b.c
	private int deviceType  ;//是设备类型1— 手机	2— 平板
	private String ua ;// 是User-agent
	private int ppi;/* 是屏幕大小(单位ppi,
	每英寸所有的像素)
	Int 类型*/
	private int screenOrientation  ;//
	private String brand  ;//是手机品牌
	private String imsi  ;//否imsi 如不填写可能会影
	public String getIdfa() {
		return idfa;
	}
	public String getIdfaMd5() {
		return idfaMd5;
	}
	public String getImei() {
		return imei;
	}
	public String getImeiMd5() {
		return imeiMd5;
	}
	public String getMac() {
		return mac;
	}
	public String getAndroidId() {
		return androidId;
	}
	public String getModel() {
		return model;
	}
	public String getVendor() {
		return vendor;
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public int getOsType() {
		return osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public String getUa() {
		return ua;
	}
	public int getPpi() {
		return ppi;
	}
	public int getScreenOrientation() {
		return screenOrientation;
	}
	public String getBrand() {
		return brand;
	}
	public String getImsi() {
		return imsi;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public void setIdfaMd5(String idfaMd5) {
		this.idfaMd5 = idfaMd5;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setImeiMd5(String imeiMd5) {
		this.imeiMd5 = imeiMd5;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	public void setOsType(int osType) {
		this.osType = osType;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public void setPpi(int ppi) {
		this.ppi = ppi;
	}
	public void setScreenOrientation(int screenOrientation) {
		this.screenOrientation = screenOrientation;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
}
