package com.ocean.persist.app.dis.appSearch360.keywordSearch;

import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class KeywordSearch360Request      implements AdDisParams{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//url参数
	private String from;
	private String ch;
	private String adspaceid;
	private String ip;
	
	//设备参数
	private String imei;
	private String androidid;
	private String serialno;
	private String md;
	private String br;
	private int os;
	private String osv;
	private String carrier;
	private int sw;
	private int sh;
	private double dip;
	
	
	//app
	private String apppkg;
	
	private String appname;
	private String 	appv;
	private int appvint;
	private String mac ;//	Strign	 ;//mac地址		N	
	private double lon	 ;//double	地理位置，经度		N	
	private double lat	 ;//double	地理位置，纬度		N	
	//动态参数
	private int so;
	private int net;
	private String searchword;
	private int num;
	private int page;
	private String ua;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getAdspaceid() {
		return adspaceid;
	}
	public void setAdspaceid(String adspaceid) {
		this.adspaceid = adspaceid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAndroidid() {
		return androidid;
	}
	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
	}
	public String getBr() {
		return br;
	}
	public void setBr(String br) {
		this.br = br;
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
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public int getSw() {
		return sw;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public double getDip() {
		return dip;
	}
	public void setDip(double dip) {
		this.dip = dip;
	}
	public String getApppkg() {
		return apppkg;
	}
	public void setApppkg(String apppkg) {
		this.apppkg = apppkg;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getAppv() {
		return appv;
	}
	public void setAppv(String appv) {
		this.appv = appv;
	}
	public int getAppvint() {
		return appvint;
	}
	public void setAppvint(int appvint) {
		this.appvint = appvint;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public int getNet() {
		return net;
	}
	public void setNet(int net) {
		this.net = net;
	}
	public String getSearchword() {
		return searchword;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getMac() {
		return mac;
	}
	public double getLon() {
		return lon;
	}
	public double getLat() {
		return lat;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
}
