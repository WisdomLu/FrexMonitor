package com.ocean.persist.api.proxy.yidianzx;

public class YidianzxDevice {
	private YidianzxGeo geo;// object 推荐设备位置信息
	private String ua;// string 推荐浏览器的User-Agent 属性字符串
	private String ip;// string 是设备的IPv4 地址
	private String ipv6;// string 否设备的IPv6 地址
	private int devicetype;// int 否设备类型：
/*	0: 未知
	1: 手机
	2: 平板
	3: PC
	4: 智能电视*/
	private String os ;//string 否设备的操作系统，如IOS
	private String osv ;//string 否设备的操作系统版本，如3.1.2
	private int w ;//int 否物理屏幕宽度，单位像素
	private int h;// int 否物理屏幕高度，单位像素
	private String make;// string 否设备制造商
	private String model ;//string 否设备型号
	private Integer ppi;// int 否每英寸屏幕大小，单位像素
	private Integer pxrate ;//double 否物理像素和设备独立像素的比例
	private int connectiontype;// int 否联网方式：
/*	0：未知
	1：Ethernet
	2：Wifi
	3：蜂窝网络
	4：蜂窝网络2G
	5：蜂窝网络3G
	6：蜂窝网络4G*/
	private String language ;//string 否浏览器语言，ISO-639-1-alpha-2 编码
	private String didmd5;// string 否硬件设备ID MD5 加密
	//对安卓而言是imei，对ios 而言是idfa
	private String didsha1;// string 否硬件设备ID SHA1 加密
	//对安卓而言是imei，对ios 而言是idfa
	private String dpidmd5 ;//string 否平台定义的设备ID MD5 加密
	//对安卓而言是androidid，对ios 而言是idfv
	private String dpidsha1;// string 否平台定义的设备ID SHA1 加密
	//对安卓而言是androidid，对ios 而言是idfv
	private String macmd5;// string 否MAC 地址MD5 加密
	private String macsha1 ;//string 否MAC 地址SHA1 加密
	public YidianzxGeo getGeo() {
		return geo;
	}
	public void setGeo(YidianzxGeo geo) {
		this.geo = geo;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIpv6() {
		return ipv6;
	}
	public void setIpv6(String ipv6) {
		this.ipv6 = ipv6;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
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
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getConnectiontype() {
		return connectiontype;
	}
	public void setConnectiontype(int connectiontype) {
		this.connectiontype = connectiontype;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDidmd5() {
		return didmd5;
	}
	public void setDidmd5(String didmd5) {
		this.didmd5 = didmd5;
	}
	public String getDidsha1() {
		return didsha1;
	}
	public void setDidsha1(String didsha1) {
		this.didsha1 = didsha1;
	}
	public String getDpidmd5() {
		return dpidmd5;
	}
	public void setDpidmd5(String dpidmd5) {
		this.dpidmd5 = dpidmd5;
	}
	public String getMacmd5() {
		return macmd5;
	}
	public void setMacmd5(String macmd5) {
		this.macmd5 = macmd5;
	}
	public String getMacsha1() {
		return macsha1;
	}
	public void setMacsha1(String macsha1) {
		this.macsha1 = macsha1;
	}
	public String getDpidsha1() {
		return dpidsha1;
	}
	public void setDpidsha1(String dpidsha1) {
		this.dpidsha1 = dpidsha1;
	}
	public Integer getPpi() {
		return ppi;
	}
	public Integer getPxrate() {
		return pxrate;
	}
	public void setPpi(Integer ppi) {
		this.ppi = ppi;
	}
	public void setPxrate(Integer pxrate) {
		this.pxrate = pxrate;
	}
}
