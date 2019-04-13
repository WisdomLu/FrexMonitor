package com.ocean.persist.api.proxy.yuansheng2;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengAdPullParams      extends AbstractInvokeParameter{
	private static final long serialVersionUID = 4075182569348284658L;
	private String version;//
	private String time;
	
	private String token;
	private String  reqid;
	private String appid;
	private String appver;
	private String adspotid;
	private int impsize;
	private String ip;
	private String ua;
	private float lat;
	private float lon;
	private int sw ;
	private int sh ;
	private int ppi ;
	private String make;
	private String model;
	private int os ;
	private String osv ;
	private String imei;
	private String mac;
	private String androidid;
	private String idfa;
	private String carrier;
	private int  network;
	private int devicetype;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppver() {
		return appver;
	}
	public void setAppver(String appver) {
		this.appver = appver;
	}
	public String getAdspotid() {
		return adspotid;
	}
	public void setAdspotid(String adspotid) {
		this.adspotid = adspotid;
	}
	public int getImpsize() {
		return impsize;
	}
	public void setImpsize(int impsize) {
		this.impsize = impsize;
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
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
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
	public int getPpi() {
		return ppi;
	}
	public void setPpi(int ppi) {
		this.ppi = ppi;
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
	public String getAndroidid() {
		return androidid;
	}
	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	

}
