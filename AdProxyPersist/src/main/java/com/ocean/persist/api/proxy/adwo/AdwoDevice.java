package com.ocean.persist.api.proxy.adwo;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月13日 
      @version 1.0 
 */
public class AdwoDevice    extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5230011466638401593L;
	private String ip;// Y String 用户手机IP 地址（公网IP） 如：201.24.23.201
	private String imei ;//N String Android 设备的imei Android 时，2 个参数至少有1
	private String androidid ;//N String Android 设备的Androidid 个
	private String idfa ;//N String iOS 时至少有1 个，当操作系统
	private String mac ;//N String 为 6.0以上必选为 IDFA
	private float s ;//N Float,iOS 设备屏幕的scale,Android 设备屏幕的密度,如：1.0
	private String ua ;//N String 设备的UserAgent URLEncode 编码
	private int net;// Y int 网络类型,1 代表为WIFI,0 代表不是WIFI
	private String osv ;//Y String 系统版本如2.2
	private String 	manu ;//N String 设备厂商UrlEncode 编码
	private String 	bn ;//Y String 设备型号UrlEncode 编码
	private String mcc_mnc ;//N String 运营商如460_01
	private int sw ;//Y int 屏幕分辨率的width 如:640
	private int sh ;//Y int 屏幕分辨率的height 如:960
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
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public float getS() {
		return s;
	}
	public void setS(float s) {
		this.s = s;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public int getNet() {
		return net;
	}
	public void setNet(int net) {
		this.net = net;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public String getManu() {
		return manu;
	}
	public void setManu(String manu) {
		this.manu = manu;
	}
	public String getBn() {
		return bn;
	}
	public void setBn(String bn) {
		this.bn = bn;
	}
	public String getMcc_mnc() {
		return mcc_mnc;
	}
	public void setMcc_mnc(String mcc_mnc) {
		this.mcc_mnc = mcc_mnc;
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
}
