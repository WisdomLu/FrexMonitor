package com.ocean.persist.api.proxy.borui;

public class BoruiBidReqDevice {
	
	// 设备类型"PHONE" 手机, "TABLET" 平板 DeviceType
	private String devicetype;
	
	// 操作系统类型 // Android,// iOS
	private String ostype;
	
	// 必填！操作系统版本 如：4.0.1
	private String os_version;
	
	// 必填！设备厂商名称
	private String vendor;
	
	// 必填！设备型号
	private String model;
	
	// 必填！运营商
	private String sptype;
	
	//必填！ Android 手机设备的 IMEI default = ""
	private String imei;
	
	//必填！ Android 非手机设备的 WiFi 网卡 MAC default = ""
	private String mac;
	
	//必填！ Android 手机设备的 IMEI，经过 MD5 加密
	private String imei_md5;
	
	//必填！Android 手机设备系统 ID default = ""
	private String android_id;
	
	//设备屏幕宽
	private Integer screen_width;
	
	//设备屏幕高
	private Integer screen_height;
	
	//必填！手机设备的 IMSI
	private String imsi;
	
	// 必填！手机客户端 IP
	private String ip;
	
	// 必填！联网类型
	private String connectiontype;
	
	//设备位置信息，经纬度以及省市信息
	private BoruiBidReqGeo geo;
	
	// 客户端 User-Agent
	private String ua;
	
	// 必填！IOS 设备的 idfa 值
	private String idfa;
	
	// IOS 设备的 openudid 值
	private String udid;
	
	// IOS 设备的 idv 值
	private String idv;

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSptype() {
		return sptype;
	}

	public void setSptype(String sptype) {
		this.sptype = sptype;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getImei_md5() {
		return imei_md5;
	}

	public void setImei_md5(String imei_md5) {
		this.imei_md5 = imei_md5;
	}

	public String getAndroid_id() {
		return android_id;
	}

	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}

	public Integer getScreen_width() {
		return screen_width;
	}

	public void setScreen_width(Integer screen_width) {
		this.screen_width = screen_width;
	}

	public Integer getScreen_height() {
		return screen_height;
	}

	public void setScreen_height(Integer screen_height) {
		this.screen_height = screen_height;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getConnectiontype() {
		return connectiontype;
	}

	public void setConnectiontype(String connectiontype) {
		this.connectiontype = connectiontype;
	}

	public BoruiBidReqGeo getGeo() {
		return geo;
	}

	public void setGeo(BoruiBidReqGeo geo) {
		this.geo = geo;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getIdv() {
		return idv;
	}

	public void setIdv(String idv) {
		this.idv = idv;
	}
}
