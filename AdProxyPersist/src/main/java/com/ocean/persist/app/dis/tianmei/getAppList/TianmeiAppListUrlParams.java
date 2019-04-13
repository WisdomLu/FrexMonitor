package com.ocean.persist.app.dis.tianmei.getAppList;

public class TianmeiAppListUrlParams {
	private int pver;//	number	是	宿主app版本号	-	132
	private String pkg;//	string	是	宿主app包名	-	com.test
	private String osver;//	string	是	客户端系统版本名	-	5.1.1
	private int osint;//	number	是	客户端系统版本号	-	22
	private String brand;//	string	是	客户手机品牌	-	doov
	private String model;//	string	是	客户手机型号,需要encode	-	doov+L925
	private String appid;//	string	是	我司分配的appid渠道号	-	测试key: test01
	private int nettype;//	number	是	网络类型	-	0:未知,1:WIFI,2:2G,3:3G,4:4G,5:未知移动网络
	private String resolution;//	string	是	分辨率,宽_高	-	1080_1920
	private String imsi	;//string	是	imsi	-	460032083289208
	private String imei	;//string	是	imei	-	891524321583218
	private String ip	;//string	是	客户端外网ip地址	-	20.155.115.57
	private String mno	;//string	是	运营商ID	-	移动联通是imsi的4-5位,电信是SID
	private String lac	;//string	是	LAC基站位置区域码	-	9627
	private String cid	;//string	是	CID基站编号	-	24893
	private String androidid	;//string	是	客户端Android_ID	-	3a6217a
	private int nonce	;//string	是	请求时间戳,10位秒	-	1508211605
	private String mac	;//string	是	客户端mac地址	-	54:00:00:00
	private String serno	;//string	是	客户端serialno	-	0123456789abcdef
	private int so	;//number	是	客户端屏幕方向,1竖屏 2横屏	-	1
	private int dpi	;//number	是	客户端屏幕像素密度	-	480
	private String adsize	;//string	否	广告素材的尺寸	-	480x800
	private String pk;
	private String ua	;//string	是	客户端user-agent,需要encode	-	Dalvik%2f1.6.0+(xxx)
	public int getPver() {
		return pver;
	}
	public String getPkg() {
		return pkg;
	}
	public String getOsver() {
		return osver;
	}
	public int getOsint() {
		return osint;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public String getAppid() {
		return appid;
	}
	public int getNettype() {
		return nettype;
	}
	public String getResolution() {
		return resolution;
	}
	public String getImsi() {
		return imsi;
	}
	public String getImei() {
		return imei;
	}
	public String getIp() {
		return ip;
	}
	public String getMno() {
		return mno;
	}
	public String getLac() {
		return lac;
	}
	public String getCid() {
		return cid;
	}
	public String getAndroidid() {
		return androidid;
	}
	public String getMac() {
		return mac;
	}
	public String getSerno() {
		return serno;
	}
	public int getSo() {
		return so;
	}
	public int getDpi() {
		return dpi;
	}
	public String getAdsize() {
		return adsize;
	}
	public String getUa() {
		return ua;
	}
	public void setPver(int pver) {
		this.pver = pver;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public void setOsver(String osver) {
		this.osver = osver;
	}
	public void setOsint(int osint) {
		this.osint = osint;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setNettype(int nettype) {
		this.nettype = nettype;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setSerno(String serno) {
		this.serno = serno;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}
	public void setAdsize(String adsize) {
		this.adsize = adsize;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
}
