package com.ocean.persist.api.proxy.helian;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月15日 
      @version 1.0 
 */
public class HelianAdPullParams   extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -909927740942275577L;
	private String adid;//广告位id

	private int adtype;//1:横幅2:开屏3:全屏4:插屏7:图形文字链（信息流）

	private int width;//广告宽度

	private int height;//广告高度

	private int devicetype;//设备类型。1 ：移动手机，2 ：平板电脑
    /*
     * "0:Android
		1:iOS
		2:Windows Phone
		3:Others"

     * */
	private int os;//

	private String osv;//设备版本
	private String pkgname;//对于Android,是应用的 PackageName 对于iOS,是 Bundle Identifier

	private String appname;//UrlEncode 之后的应用程序名称编码。

	private String ua;//用户终端浏览器标识。需要UrlEncode 处理

	private String uuid;//识别码。对于IOS 设备，该值为系统的idfa, 对于Android 设备，该值为系统的imei



	private String mac;//MAC 地址，如：e8:bb:a8:1b:d5:51

	private String anid;//The Android Id，如：9774d56d682e549c

	private int pw;//设备屏幕宽度。例如：1000

	private int ph;//设备屏幕高度。例如：600
    /*
     * "1:移动
		2:联通
		3:电信
		4:其他"

     * */
	private int carrier;//
	/*
	 * "1:2g
		2:3g
		3:wifi
		4:4g
		5:其他"

	 * */
	private int conn;//
	private String  ip;//用户来源IP,  如 211.136.28.18

	private String density;//设备屏幕的密度。android 的取值为0.75、1、1.5，Ios 的取值为：1、2 

	private String brand;//设备制造厂商， 全为小写字符。例如：nokia ，samsung

	private String 	model;//设备型号，如：galaxy

	private String Lon;//设备所在地理位置的经度。例如：111.467

	private String Lat;//设备所在地理位置的纬度。例如：49.667

	private int trq;//测试请求，0：否、1：是
   
	private String imsi;
	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public int getAdtype() {
		return adtype;
	}

	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public int getOs() {
		return os;
	}

	public void setOs(int os) {
		this.os = os;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}

	public String getPkgname() {
		return pkgname;
	}

	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getAnid() {
		return anid;
	}

	public void setAnid(String anid) {
		this.anid = anid;
	}

	public int getPw() {
		return pw;
	}

	public void setPw(int pw) {
		this.pw = pw;
	}

	public int getPh() {
		return ph;
	}

	public void setPh(int ph) {
		this.ph = ph;
	}

	public int getCarrier() {
		return carrier;
	}

	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}

	public int getConn() {
		return conn;
	}

	public void setConn(int conn) {
		this.conn = conn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
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

	public String getLon() {
		return Lon;
	}

	public void setLon(String lon) {
		Lon = lon;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public int getTrq() {
		return trq;
	}

	public void setTrq(int trq) {
		this.trq = trq;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}


}
