package com.ocean.persist.api.proxy.xuanyin;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class XuanyinAdPullReq  extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 670753910532557444L;
	private String app_id;// 必须 string 铉音媒体平台的appId 登录铉音媒体平台，应用管理-应用列表获取 
	private String slot_id ;// 必须 string 铉音媒体平台的slotId 登录铉音媒体平台，应用管理-广告位列表获取 
	private long time ;// 必须 long 时间戳 13位,精确到毫秒 
	private String sign ;// 必须 string 加密签名大写MD5 生成规则{app_id}+{time}+{slot_id}+key 
	private String app_ver ;// 必须 string app版本号 例如：2.0.1 
	private String vkeyword ;// 推荐填写 string 广告关键词 最多长度50 
	private int width ;// 必须 int 广告位宽度 例如：640 单位px 
	private int height ;// 必须 int 广告位高度 例如：150 单位px 
	private String ip ;// 必须 string 客户端的ip 180.173.47.11 
	private int network ;// 必须 int 网络类型 0:未知、1:wifi、2:2G、3:3G、4:4G 
	private int operator ;// 必须 int 网络运营商 46000:中国移动、46001:中国联通、46003:中国电信。 WIFI情况下传0，其他情况必须。 
	private String wifi_ssid ;// 推荐填写 string wifi名称   
	private String wifi_bssid ;// 推荐填写 string wifi对应的网卡mac地址   
	private String user_agent ;// 必须 string 浏览器ua 客户端的UserAgent。必须是客户端通过系统API获取的真实UA，而非自定义 UA 
	private int device_type ;// 必须 int 设备类型 0:未知、1:phone、 2:pad、 3:pc、4:wap。默认填写 1 
	private int os ;// 必须 int 客户端系统 1:Android、 2:IOS、 3:WAP 
	private String os_ver ;// 必须 string 系统版本 例如：8.0.0 
	private String imei;//  安卓 必须 string 移动设备身份码 例：868793037952869 
	private String android_id;//  安卓 必须 string android ID 例：201f2b6b5a3d6fae 
	private String mac ;// 安卓 必须 string mac地址 例：B0:35:9F:84:DA:87 
	private String idfv ;// 推荐填写 string IOS的idfv   
	private String idfa ;// IOS必须 string IOS的idfa   
	private String udid ;// 推荐填写 string IOS中的openudid   
	private String duid ;// 推荐填写 string wp的 duid   
	private String brand ;// 必须 string 设备品牌 例：HUAWEI 
	private String model ;// 必须 string 设备型号 例：HWI-AL00 
	private double density;//  必须 double 屏幕密度 例：2.0 
	private int screen_width ;// 必须 int 屏幕宽度(单位px) 例：375 
	private int screen_height ;// 必须 int 屏幕高度(单位px) 例：667 
	private int screen_orientation ;// 必须 int 屏幕方向，横竖屏 0：未知、1：竖屏、2：横屏 
	private String language ;// 推荐填写 string 当前使用语言 默认：zh-cn 
	private int gps_type ;// 推荐填写 int gps类型 0 高德、 1 百度、 2 腾讯、 3 谷歌、 4 其他。默认值 4 
	private Float longitude;//  必须 float 经度 例：106.887642，默认值 0 
	private Float latitude;//  必须 float 纬度 例：29.94666，默认值 0 
	public String getApp_id() {
		return app_id;
	}
	public String getSlot_id() {
		return slot_id;
	}
	public long getTime() {
		return time;
	}
	public String getSign() {
		return sign;
	}
	public String getApp_ver() {
		return app_ver;
	}
	public String getVkeyword() {
		return vkeyword;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getIp() {
		return ip;
	}
	public int getNetwork() {
		return network;
	}
	public int getOperator() {
		return operator;
	}
	public String getWifi_ssid() {
		return wifi_ssid;
	}
	public String getWifi_bssid() {
		return wifi_bssid;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public int getDevice_type() {
		return device_type;
	}
	public int getOs() {
		return os;
	}
	public String getOs_ver() {
		return os_ver;
	}
	public String getImei() {
		return imei;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public String getMac() {
		return mac;
	}
	public String getIdfv() {
		return idfv;
	}
	public String getIdfa() {
		return idfa;
	}
	public String getUdid() {
		return udid;
	}
	public String getDuid() {
		return duid;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public double getDensity() {
		return density;
	}
	public int getScreen_width() {
		return screen_width;
	}
	public int getScreen_height() {
		return screen_height;
	}
	public int getScreen_orientation() {
		return screen_orientation;
	}
	public String getLanguage() {
		return language;
	}
	public int getGps_type() {
		return gps_type;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public void setSlot_id(String slot_id) {
		this.slot_id = slot_id;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setApp_ver(String app_ver) {
		this.app_ver = app_ver;
	}
	public void setVkeyword(String vkeyword) {
		this.vkeyword = vkeyword;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public void setWifi_ssid(String wifi_ssid) {
		this.wifi_ssid = wifi_ssid;
	}
	public void setWifi_bssid(String wifi_bssid) {
		this.wifi_bssid = wifi_bssid;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public void setOs_ver(String os_ver) {
		this.os_ver = os_ver;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public void setDuid(String duid) {
		this.duid = duid;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public void setScreen_width(int screen_width) {
		this.screen_width = screen_width;
	}
	public void setScreen_height(int screen_height) {
		this.screen_height = screen_height;
	}
	public void setScreen_orientation(int screen_orientation) {
		this.screen_orientation = screen_orientation;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setGps_type(int gps_type) {
		this.gps_type = gps_type;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public Float getLongitude() {
		return longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

}
