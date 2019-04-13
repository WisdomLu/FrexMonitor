package com.ocean.persist.app.dis.zs;

import com.ocean.persist.app.dis.AdDisParams;

public class ZsBaseSearchRequest  implements AdDisParams {
	private String id;
	private long timestamp;// 13位数字
	private String token;
	private String ip;
	private int carrior;
	private Integer network_type;
	private String imei;
	private String mac;
	private String imsi;
	private String serial_no;
	private String android_id;
	private String vendor;// 手机厂商
	private String brand;// 手机品牌
	private String model;// 手机型号
	private int device_type;
	private String dpi;// 屏幕dpi
	private String screen_size;// 屏幕尺寸宽x 高（800x1223）
	private int orientation;// 屏幕方向---0:位置; 1:竖屏; 2:横屏
	private String ua;
	private String version;// 操作系统版本号
	private String version_int;// 操作系统版本号, 采集自Build.VERSION.SDK_INT
	private String uuid;
	private String app_id;// 宿主APP包名
	private String app_name;// 宿主APP应用名称
	private String app_version;
	private int geo_type;// 1;全球卫星定位系统坐标系2;国家测绘局坐标系3;百度坐标系
	private float geo_latitude;// 经度(浮点型)
	private float geo_longitude;// 纬度(浮点型)
	private long geo_time;// 定位所在地的时间精确到毫秒
	private float geo_location_accuracy;// 经纬度精度半径，单位为米 浮点型，暂可不传
	private int support_redirect;// 是否支持302重定向0：否，1：是

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCarrior() {
		return carrior;
	}

	public void setCarrior(int carrior) {
		this.carrior = carrior;
	}

	public Integer getNetwork_type() {
		return network_type;
	}

	public void setNetwork_type(Integer network_type) {
		this.network_type = network_type;
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

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public String getAndroid_id() {
		return android_id;
	}

	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public int getDevice_type() {
		return device_type;
	}

	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}

	public String getDpi() {
		return dpi;
	}

	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	public String getScreen_size() {
		return screen_size;
	}

	public void setScreen_size(String screen_size) {
		this.screen_size = screen_size;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion_int() {
		return version_int;
	}

	public void setVersion_int(String version_int) {
		this.version_int = version_int;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
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

	public long getGeo_time() {
		return geo_time;
	}

	public void setGeo_time(long geo_time) {
		this.geo_time = geo_time;
	}

	public float getGeo_location_accuracy() {
		return geo_location_accuracy;
	}

	public void setGeo_location_accuracy(float geo_location_accuracy) {
		this.geo_location_accuracy = geo_location_accuracy;
	}

	public int getSupport_redirect() {
		return support_redirect;
	}

	public void setSupport_redirect(int support_redirect) {
		this.support_redirect = support_redirect;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
