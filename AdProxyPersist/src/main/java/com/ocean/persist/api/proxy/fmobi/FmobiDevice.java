package com.ocean.persist.api.proxy.fmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiDevice extends AbstractBaseEntity  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2625300551901021460L;
	private String imei;
	private String imeiori;
	private String mac;
	private String macori;
	private String 	mac1;
	private String idfa;
	private String idfaori;
	private String aaid;
	private String anid;
	private String anidori;
	private String udid;
	private String duid;
	private String brand;
	private String platform;
	private int os;
	private String os_version;
	private String device_size ;
	private int network;
	private int operator;
	private float longitude;
	private float latitude;
	private int screen_orientation;
	private String ip;
	private String ua;
	private String density;
	private String imsi;
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImeiori() {
		return imeiori;
	}
	public void setImeiori(String imeiori) {
		this.imeiori = imeiori;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMacori() {
		return macori;
	}
	public void setMacori(String macori) {
		this.macori = macori;
	}
	public String getMac1() {
		return mac1;
	}
	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getIdfaori() {
		return idfaori;
	}
	public void setIdfaori(String idfaori) {
		this.idfaori = idfaori;
	}
	public String getAaid() {
		return aaid;
	}
	public void setAaid(String aaid) {
		this.aaid = aaid;
	}
	public String getAnid() {
		return anid;
	}
	public void setAnid(String anid) {
		this.anid = anid;
	}
	public String getAnidori() {
		return anidori;
	}
	public void setAnidori(String anidori) {
		this.anidori = anidori;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getDuid() {
		return duid;
	}
	public void setDuid(String duid) {
		this.duid = duid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public String getDevice_size() {
		return device_size;
	}
	public void setDevice_size(String device_size) {
		this.device_size = device_size;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public int getScreen_orientation() {
		return screen_orientation;
	}
	public void setScreen_orientation(int screen_orientation) {
		this.screen_orientation = screen_orientation;
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
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
}
