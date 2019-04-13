package com.ocean.persist.app.dis.youyou;

public class BaseYouyouReq {
	// url后面的
	private String token;
	private Long timestamp;
	private String nonce;
	private String sign;

	// 设备参数
	private String ip;
	private String md;
	private String imei;
	private String androidid;
	private String serialno;
	private String br;
	private int os;
	private String osv;
	private Integer carrier;
	private int sw;
	private int sh;
	private double dip;
	private int so;
	private int net;
	private String ua;
	private String mac;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
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
	public Integer getCarrier() {
		return carrier;
	}
	public void setCarrier(Integer carrier) {
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
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
}
