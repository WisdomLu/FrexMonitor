package com.ocean.persist.api.proxy.firebird;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月19日 
      @version 1.0 
 */
public class FireBirdAdPullParams     extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8675358234972683748L;
	private String appid ;//String Y 􀀬􀁆􀁓􀀏
	private String posid;// String Y 􀀪􀀔􀀅 id
	private String imei ;//String Y 􀁚􀀞 IMEI 􀀏
	private String imsi ;//String Y 􀁚􀀞 IMSI 􀀏
	private String hstype;// String Y 􀀽􀀜
	private String hsman;// String Y 􀀍􀀕
	private String osver ;//String Y 􀁎􀁑􀁅􀀼􀀏
	private String density ;//String Y 􀀧􀀩􀀥􀀭
	private int nettype;// Integer Y 1 􀀻􀁉 0 􀀃􀀗􀁋􀀌 1 􀀃􀀗􀁕􀁟 2 􀀃􀀗􀁇􀀆
	private int devicetype ;//Integer Y 0:􀀳􀀽􀀁1:PAD􀀁2:PC􀀁3:TV
	private String os;// String ;//Y Android/IOS
	private String adid ;//String Y Android Id
	private String mac;// String Y 􀁚􀀞 MAC 􀀚􀀛
	private String operator;// String Y 􀁔􀁐􀁝􀁖􀀕
	private String dvw ;//String Y 􀀧􀀩􀀤􀀭
	private String dvh ;//String Y 􀀧􀀩􀁦􀀭
	private String clientUa ;//String Y 􀁚􀀞 UA 􀀆􀀯
	private String clientIp;// String Y 􀀣􀀱􀁌􀁈􀀢 IP
	private String lon ;//String Y 􀁏􀀭
	private String lat ;//String Y 􀁒􀀭
	private String city ;//String N 􀀲􀀙􀀝􀀨
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getHstype() {
		return hstype;
	}
	public void setHstype(String hstype) {
		this.hstype = hstype;
	}
	public String getHsman() {
		return hsman;
	}
	public void setHsman(String hsman) {
		this.hsman = hsman;
	}
	public String getOsver() {
		return osver;
	}
	public void setOsver(String osver) {
		this.osver = osver;
	}
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public int getNettype() {
		return nettype;
	}
	public void setNettype(int nettype) {
		this.nettype = nettype;
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
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDvw() {
		return dvw;
	}
	public void setDvw(String dvw) {
		this.dvw = dvw;
	}
	public String getDvh() {
		return dvh;
	}
	public void setDvh(String dvh) {
		this.dvh = dvh;
	}
	public String getClientUa() {
		return clientUa;
	}
	public void setClientUa(String clientUa) {
		this.clientUa = clientUa;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
