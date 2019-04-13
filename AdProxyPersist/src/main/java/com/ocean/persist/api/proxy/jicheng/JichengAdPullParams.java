package com.ocean.persist.api.proxy.jicheng;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月31日 
      @version 1.0 
 */
public class JichengAdPullParams  extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 282386530463789350L;

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String ad_type	;//广告类型:", 横幅: 1001 插屏: 1002 开屏: 1003 信息流: 1000
	private String 	app_id;//	应用 ID 正式参数需开发者申请后分配
	private String 	channel_id;//	发行渠道正式参数需开发者申请后分配
	private String 	ad_postion	;//广告位ID 正式参数需开发者申请后分配
	private String ad_size	;//填写当前设备可展现广告区域的尺寸。如代码位请求图片物料，图片尺寸是在后台进行设置
	private String imei;//	手机IMEI
	private String android_id	;//Android ID 手机唯一标识
	private String idfa	;//IOS;// 设备的idfa
	private int os	;//设备系统 Android = 1,IOS=2
	private String os_ver	;//当前设备操作系统版本，如 4.4.4
	private String brand;//	设备厂商名称，比如OPPO，中文需要UTF-8编码
	private String model;//	设备型号如 R827T中文需要UTF8编码
	private String 	screen_size;//	设备屏幕大小，如720x1280
	private String ip	;//公网IPv4地址，确保填写的内容为用户设备的公网出口IP地址
	private int connection_type;//	网络连接类型 ,无法探测 (CONNCTION_UNKNOWN)=0,未知数据网络(CELL_UNKNOWN)=1,2G=2,3G=3,4G=4,CELL_5G=5,WIFI=100,ETHERNET=101
	private int operator_type	;//移动运营商类型 ,未知的运营商 (UNKNOWN_OPERATOR) = 0;中国移动 = 1;中国电信 = 2;中国联通 = 3;其他运营商 = 99
	private String app_version;//	在百度提交的APP的应用版本号
	private String 	package_name;//	在百度提交的APP的包名，如"com.lx.xxoo.test"
	private String 	ua	;//终端用户HTTP 请求头中的 User-Agent
	private String reffer;//	终端用户HTTP 请求头中的referer
	private String channel_s_id	;//发行子渠道正式参数需开发者申请后分配
	private String udid;//	ios设备标识
	private String imsi	;//手机卡IMSI
	private String mac	;//mac网卡地址
	private int device_type;//	设备类型 PHONE(手机，含 iTouch) = 1;TABLE( 平板 ) = 2)
	private String lng	;//经度  尽量填写影响收益
	private String lat	;//纬度 尽量填写影响收益
	private String cid;//	;//移动基站 id
	private String a	;//备用参数 空
	private String b	;//备用参数 空
	public String getAd_type() {
		return ad_type;
	}
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getAd_postion() {
		return ad_postion;
	}
	public void setAd_postion(String ad_postion) {
		this.ad_postion = ad_postion;
	}
	public String getAd_size() {
		return ad_size;
	}
	public void setAd_size(String ad_size) {
		this.ad_size = ad_size;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getOs_ver() {
		return os_ver;
	}
	public void setOs_ver(String os_ver) {
		this.os_ver = os_ver;
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
	public String getScreen_size() {
		return screen_size;
	}
	public void setScreen_size(String screen_size) {
		this.screen_size = screen_size;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getConnection_type() {
		return connection_type;
	}
	public void setConnection_type(int connection_type) {
		this.connection_type = connection_type;
	}
	public int getOperator_type() {
		return operator_type;
	}
	public void setOperator_type(int operator_type) {
		this.operator_type = operator_type;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getReffer() {
		return reffer;
	}
	public void setReffer(String reffer) {
		this.reffer = reffer;
	}
	public String getChannel_s_id() {
		return channel_s_id;
	}
	public void setChannel_s_id(String channel_s_id) {
		this.channel_s_id = channel_s_id;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
}
