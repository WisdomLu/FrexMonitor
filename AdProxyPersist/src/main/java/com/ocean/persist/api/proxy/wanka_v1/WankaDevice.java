package com.ocean.persist.api.proxy.wanka_v1;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class WankaDevice {
	private int device_type;/*
	int
	是
	设备类型， 1：移动设备，2：个人 计算机，3：连接电视，4：手机，5： 平板，6：连通装置，7：机顶盒。*/
	private String os_type;/*
	string
	是
	操作系统类型:android,ios*/
	private String os_version;/*
	string
	是
	操作系统版本*/
	private String vendor;/*
	string
	“”
	是
	设备厂商名称，中文需要 UTF-8 编 码*/
	private String model;/*
	string
	“”
	是
	设备型号，中文需要 UTF-8 编码*/
	private String idfa;/*
	
	String
	“”
	是
	IOS 设备的 IDFA*/
	private String openudid;
	private String idfv;
	private String android_id;/*
	
	string
	是
	Android 设备系统 ID*/
	private String android_id_md5;
	private String android_advertising_id;
	public String getAndroid_advertising_id() {
		return android_advertising_id;
	}
	public void setAndroid_advertising_id(String android_advertising_id) {
		this.android_advertising_id = android_advertising_id;
	}
	private String imei;/*
	String
	是
	Android 手机设备的 IMEI*/
	private String imei_md5;/*
	string
	“”
	是
	Android 手机设备的 IMEI_MD5*/
	private String mac;/*
	string
	是
	设备的 WiFi 网卡 mac 号*/
	private int 	w;/*
	int
	是
	设备屏幕宽度*/
	private int h;/*
	int
	是
	设备屏幕高度*/
	private int dpi;/*
	
	double
	是
	像素密度。*/
	private String language;
	private String country;
	private String resolution;
	private int orientation;
	private String referer;
	private int isroot;
	private String btmac;
	private String pdunid;
	private String cputy;
	private String cpusn;
	private String iccid;
	
	
	private String ua;/*
	string
	是
	系统 User-Agent*/
	private String imsi;/*
	String
	是
	设备的 imsi 号*/
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public String getOs_type() {
		return os_type;
	}
	public void setOs_type(String os_type) {
		this.os_type = os_type;
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
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImei_md5() {
		return imei_md5;
	}
	public void setImei_md5(String imei_md5) {
		this.imei_md5 = imei_md5;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getAndroid_id_md5() {
		return android_id_md5;
	}
	public void setAndroid_id_md5(String android_id_md5) {
		this.android_id_md5 = android_id_md5;
	}
	public int getDpi() {
		return dpi;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public int getIsroot() {
		return isroot;
	}
	public void setIsroot(int isroot) {
		this.isroot = isroot;
	}
	public String getBtmac() {
		return btmac;
	}
	public void setBtmac(String btmac) {
		this.btmac = btmac;
	}
	public String getPdunid() {
		return pdunid;
	}
	public void setPdunid(String pdunid) {
		this.pdunid = pdunid;
	}
	public String getCputy() {
		return cputy;
	}
	public void setCputy(String cputy) {
		this.cputy = cputy;
	}
	public String getCpusn() {
		return cpusn;
	}
	public void setCpusn(String cpusn) {
		this.cpusn = cpusn;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
}
