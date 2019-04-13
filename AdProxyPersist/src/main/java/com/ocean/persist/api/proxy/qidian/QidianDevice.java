package com.ocean.persist.api.proxy.qidian;

public class QidianDevice {
	private String deviceId;
	private int network;
	private int deviceType;
	private String imei;
	private String imeiMd5 ;
	private String idfa ;
	private String androidId;
	private String brand ;
	private String model ;
	private String os;
	private String osVersion ;
	private int carrier ;
	private String mac ;
	private String ip;
	private String userAgent;
	private QidianGeo geo;
	public String getDeviceId() {
		return deviceId;
	}
	public int getNetwork() {
		return network;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public String getImei() {
		return imei;
	}
	public String getImeiMd5() {
		return imeiMd5;
	}
	public String getIdfa() {
		return idfa;
	}
	public String getAndroidId() {
		return androidId;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public String getOs() {
		return os;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public int getCarrier() {
		return carrier;
	}
	public String getMac() {
		return mac;
	}
	public String getIp() {
		return ip;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setImeiMd5(String imeiMd5) {
		this.imeiMd5 = imeiMd5;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public QidianGeo getGeo() {
		return geo;
	}
	public void setGeo(QidianGeo geo) {
		this.geo = geo;
	}

}
