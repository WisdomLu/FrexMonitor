package com.ocean.persist.api.proxy.miquwan;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月31日 
      @version 1.0 
 */
public class MiquwanAdpullParams         extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6947754434892806218L;
	private String version;
	private int c_type;
	private String mid;
	private String uid;
	private String os;
	private String mac;
	private String osv;
	private int networktype;
	private String remoteip;
	private String make;
	private String brand;
	private String model;
	private String devicetype;
	private String imei;
	private String imsi;
	private String androidid;
	private String idfa;
	private int width;
	private int height;
	private int orientation;
	private String appid;
	private String appame;
	private int position;
	private int operator;
	private int geo_type;
	private float geo_latitude;
	private float geo_longitude;
	private int geo_time;
	private String wifi_aps;
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getC_type() {
		return c_type;
	}
	public void setC_type(int c_type) {
		this.c_type = c_type;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public String getRemoteip() {
		return remoteip;
	}
	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
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
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
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
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppame() {
		return appame;
	}
	public void setAppame(String appame) {
		this.appame = appame;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getGeo_type() {
		return geo_type;
	}
	public void setGeo_type(int geo_type) {
		this.geo_type = geo_type;
	}
	public float getGeo_latitude() {
		return geo_latitude;
	}
	public void setGeo_latitude(float geo_latitude) {
		this.geo_latitude = geo_latitude;
	}
	public float getGeo_longitude() {
		return geo_longitude;
	}
	public void setGeo_longitude(float geo_longitude) {
		this.geo_longitude = geo_longitude;
	}
	public int getGeo_time() {
		return geo_time;
	}
	public void setGeo_time(int geo_time) {
		this.geo_time = geo_time;
	}
	public String getWifi_aps() {
		return wifi_aps;
	}
	public void setWifi_aps(String wifi_aps) {
		this.wifi_aps = wifi_aps;
	}
	public int getNetworktype() {
		return networktype;
	}
	public void setNetworktype(int networktype) {
		this.networktype = networktype;
	}
}
