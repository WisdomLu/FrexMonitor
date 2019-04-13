package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Device {
	private String brand;//	品牌	string	品牌	是	
	private String model;//	机型	string	机型	是	
	private String imei	;//IMEI	string	IMEI	是	
	private String imsi	;//IMSI	string	IMSI	否	
	private String mac	;//网卡地址	string	网卡地址	是	
	private int carrier	;//运营商	number	运营商:
/*	0 未知
	1 移动
	2 联通
	3 电信	是	*/
	private int network	;//网络	number	网络:
/*	0 未知
	1 wifi
	2 2G
	3 3G
	4 4G
	5 5G	是*/	
	private int network_type;
	/*	联网类型	number	联网类型:
		0 未知
		1 数据
		2 wifi	是	*/
	private String language	;//设备语言	string		是	
	private String client_ip;//	设备IP	string		是	
	private String referer;//	Referer	string		否	referer
	private String user_agent;//	User-agent	string		是	
	private String screen_resolution;//	分辨率	string		是	1080*1920格式，其他不能识别
	private int screen_orientation;//	屏幕方向	number	横竖屏
/*		0 未知
		1竖屏
		2 横屏	是	*/
	private int screen_density;//	屏幕密度	number		是	不可为0
	private  double	longitude;//经度	number		否	
	private  double	latitude;//纬度	number		否	
	private int os_type	;//OS类型	number	操作系统:
/*		0 未知
		1 android
		2 IOS
		3 windows	是	*/
	private String os_version	;//OS版本	string		是	
	private int os_api_level	;//OS API	number		是	
	private String android_id	;//安卓ID	string		是	
	private String google_ad_id;//	谷歌广告ID	string		否	
	private String idfa	;//Ios广告标识符	string		是	
	private String 	idfv	;//Ios必填	string		是	
	private String openudid	;//Ios唯一识别号	string		否	
	private String mnc	;//基站MNC	string		否	imsi不为空的时候必填
	private String mcc	;//基站MCC	string		否	
	private String lac	;//LAC(基站位置区域码)	string		否	
	private String 	cid	;//基站CID(基站编号)	string		否	
	private String bssid;//	WIFI的ID	string		否	
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public String getImei() {
		return imei;
	}
	public String getImsi() {
		return imsi;
	}
	public String getMac() {
		return mac;
	}
	public int getCarrier() {
		return carrier;
	}
	public int getNetwork() {
		return network;
	}
	public int getNetwork_type() {
		return network_type;
	}
	public String getLanguage() {
		return language;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public String getReferer() {
		return referer;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public String getScreen_resolution() {
		return screen_resolution;
	}
	public int getScreen_orientation() {
		return screen_orientation;
	}
	public int getScreen_density() {
		return screen_density;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public int getOs_type() {
		return os_type;
	}
	public String getOs_version() {
		return os_version;
	}
	public int getOs_api_level() {
		return os_api_level;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public String getGoogle_ad_id() {
		return google_ad_id;
	}
	public String getIdfa() {
		return idfa;
	}
	public String getIdfv() {
		return idfv;
	}
	public String getOpenudid() {
		return openudid;
	}
	public String getMnc() {
		return mnc;
	}
	public String getMcc() {
		return mcc;
	}
	public String getLac() {
		return lac;
	}
	public String getCid() {
		return cid;
	}
	public String getBssid() {
		return bssid;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public void setNetwork_type(int network_type) {
		this.network_type = network_type;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public void setScreen_resolution(String screen_resolution) {
		this.screen_resolution = screen_resolution;
	}
	public void setScreen_orientation(int screen_orientation) {
		this.screen_orientation = screen_orientation;
	}
	public void setScreen_density(int screen_density) {
		this.screen_density = screen_density;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setOs_type(int os_type) {
		this.os_type = os_type;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public void setOs_api_level(int os_api_level) {
		this.os_api_level = os_api_level;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public void setGoogle_ad_id(String google_ad_id) {
		this.google_ad_id = google_ad_id;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
}
