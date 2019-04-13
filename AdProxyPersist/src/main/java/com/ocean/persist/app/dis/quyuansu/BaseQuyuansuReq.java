package com.ocean.persist.app.dis.quyuansu;

public class BaseQuyuansuReq {
    private String id;
    private String apiToken;
    private long timestamp;
    private String versionName;
    private int versionCode;
    private String ip;
    private String mac;
    private String imei;
    private String imsi;
    private String model;
    private String manufacture;
    private String api_level;
    private String osv;
    private String androidId;
    private String serialno;
    private int sw;
    private int sh;
    private double dip;
    private int so;
    private int net;
    private Integer info_la;
    private Integer info_ci;
    private String ua;
	public String getId() {
		return id;
	}
	public String getApiToken() {
		return apiToken;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public String getVersionName() {
		return versionName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public String getIp() {
		return ip;
	}
	public String getMac() {
		return mac;
	}
	public String getImei() {
		return imei;
	}
	public String getImsi() {
		return imsi;
	}
	public String getModel() {
		return model;
	}
	public String getManufacture() {
		return manufacture;
	}
	public String getApi_level() {
		return api_level;
	}
	public String getOsv() {
		return osv;
	}
	public String getAndroidId() {
		return androidId;
	}
	public String getSerialno() {
		return serialno;
	}
	public int getSw() {
		return sw;
	}
	public int getSh() {
		return sh;
	}
	public double getDip() {
		return dip;
	}
	public int getSo() {
		return so;
	}
	public int getNet() {
		return net;
	}
	public String getUa() {
		return ua;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public void setApi_level(String api_level) {
		this.api_level = api_level;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public void setDip(double dip) {
		this.dip = dip;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public void setNet(int net) {
		this.net = net;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public Integer getInfo_la() {
		return info_la;
	}
	public Integer getInfo_ci() {
		return info_ci;
	}
	public void setInfo_la(Integer info_la) {
		this.info_la = info_la;
	}
	public void setInfo_ci(Integer info_ci) {
		this.info_ci = info_ci;
	}
}
