package com.ocean.persist.api.proxy.taylor;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;




public class TaylorAdPullParams    extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;

	private String ver;// 请求的API版本号	本文档中为固定值: 1.7.4
	
	private String appid;// 用户标识，向泰莱申请
	
	private String appkey;// 
	
	private String lid;// 广告位ID，向泰莱申请
	
	private String os;// 操作系统类型
	
	private String osversion;// 操作系统版本
	
	private String appversion;// 当前app版本
	
	private String androidid;// Android Id（android必填）
	
	private String imei;// 手机串号（android必填）
	
	private String mac;// MAC地址（android必填）
	
	private String idfa;// IOS 设备的idfa 值（ios必填）
	
	private String udid;// IOS设备的openudid 值
	
	private String idv;// iOS 设备的idv 值
	
	private String appname;// APP名称
	
	private String apppackagename;// APP应用包名
	
	private String imsi;// 手机网络运营商
	
	private String ip;// 客户端外网IP
	
	private String ua;// 客户端User-Agent
	
	private String network;// 网络类型
	
	private String time;// 请求的unix时间戳，精确到毫秒（13位）
	
	private Integer screenwidth;// 屏幕宽度
	
	private Integer screenheight;// 屏幕高度
	
	private Integer width;// 广告位宽度（图片广告必填）
	
	private Integer height;// 广告位高度（图片广告必填）
	
	private String manufacturer;// 设备生产商
	
	private String brand;// 设备品牌
	
	private String model;// 设备型号
	
	private String language;// 当前使用语言
	
	private String wifissid;// WIFI SSID
	
	private String token;// 验证信息

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsversion() {
		return osversion;
	}

	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getAndroidid() {
		return androidid;
	}

	public void setAndroidid(String androidid) {
		this.androidid = androidid;
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

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getApppackagename() {
		return apppackagename;
	}

	public void setApppackagename(String apppackagename) {
		this.apppackagename = apppackagename;
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

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getScreenwidth() {
		return screenwidth;
	}

	public void setScreenwidth(Integer screenwidth) {
		this.screenwidth = screenwidth;
	}

	public Integer getScreenheight() {
		return screenheight;
	}

	public void setScreenheight(Integer screenheight) {
		this.screenheight = screenheight;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getWifissid() {
		return wifissid;
	}

	public void setWifissid(String wifissid) {
		this.wifissid = wifissid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
