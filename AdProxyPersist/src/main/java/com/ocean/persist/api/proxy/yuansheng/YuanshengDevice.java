package com.ocean.persist.api.proxy.yuansheng;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengDevice extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9110268172273497972L;
	private String ua;//	string		浏览器的user agent。
	private String ip;//	string	Y	设备的ip地址。
	private YuanshengGeo geo	;//object		geo对象，见3.2.8。
	private int sw	;//integer	Y	设备屏幕宽度，物理像素。
	private int sh;//	integer	Y	设备屏幕高度，物理像素。
	private int sd	;//integer	N	设备像素密度，物理像素。
	private String make;//	string	Y	制造商。
	private String brand;//	string	N	品牌。
	private String model;//	string	Y	机型。
	private int os;//	integer	Y	操作系统类型，见A3。
	private String osv	;//string	Y	操作系统版本。
	private String did;//	string	Y	设备id，Android设备为imei（iOS不适用）。
	private String mac	;//string	Y	设备mac地址，Android设备适用（iOS不适用）。
	private String adid	;//string	Y	系统广告指示符。Android系统为Android Id，iOS系统为idfa。
	private String carrier;//	string	Y	运营商代码，MCC（3位）+ MNC（2位），例如：46000（中国移动）。MCC: Mobile Country Code, MNC: Mobile Network Code，见A6。
	private int connectiontype;//	integer	Y	网络连接类型，见A2。
	private int devicetype;//	integer	Y	设备类型，见A1。
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
	public YuanshengGeo getGeo() {
		return geo;
	}
	public void setGeo(YuanshengGeo geo) {
		this.geo = geo;
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
	public int getSd() {
		return sd;
	}
	public void setSd(int sd) {
		this.sd = sd;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
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
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public int getConnectiontype() {
		return connectiontype;
	}
	public void setConnectiontype(int connectiontype) {
		this.connectiontype = connectiontype;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

}
