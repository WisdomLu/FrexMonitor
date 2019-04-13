package com.ocean.persist.api.proxy.yingna;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月18日 
      @version 1.0 
 */
public class YingnaAdPullParams    extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8698452407185207034L;
	private String code_id ;/*
	必填
	String
	赢纳分配的广告位 赢纳分配的广告位 赢纳分配的广告位 赢纳分配的广告位 ID
	广告位唯一标识，结账用 广告位唯一标识，结账用 广告位唯一标识，结账用 广告位唯一标识，结账用 广
	*/
	private String os_ver;/*
	主版本必填
	次版本可选
	微版本 可 选
	String
	格式要求： 格式要求： 格式要求： 主版本 主版本 .次版本 .微版本 微版本
	例子：
	9.3.1 ： OS 为 IOS9.3.1 IOS9.3.1 IOS9.3.1的版本
	5.0 .0 ： OS 为 Android Android 5.0 5.0的版本 的版本
	OS 版本*/
	private String app_pkg ;/*
	选填
	String
	例：
	com. wina.mobads.test wina.mobads.test wina.mobads.test wina.mobads.test wina.mobads.test
	APP 包名，确保 包名，确保 包名，确保 与提交的应用 与提交的应用 与提交的应用 与提交的应用 与提交的应用 与提交的应用 一对应，建议 一对应，建议 一对应，建议 一对应，建议 一对应，建议 填写
	*/
	private String app_ver;/*
	主版本必填
	次版本可选
	微版本 可 选
	String
	格式要求： 格式要求： 格式要求： 主版本 主版本 .次版本 .微版本 微版本
	例子：
	1.0
	1.1.1
	1.1.12
	APP 版本*/
	private int device_type ;/*
	必填
	int
	格式 要求： 要求： 1: 手机 2: 平板
	设备类型 设备类型*/
	private int os_type ;/*
	必填
	int
	格式要求： 格式要求： 1: 安卓 2:IOS2:IOS
	系统类型*/
	private String vendor;/*
	必填
	String
	samsung/vivo/xiaomi/... samsung/vivo/xiaomi/... samsung/vivo/xiaomi/... samsung/vivo/xiaomi/... samsung/vivo/xiaomi/... samsung/vivo/xiaomi/... samsung/vivo/xiaomi/... 等 必须要做 必须要做 encode(UTFencode(UTF encode(UTF encode(UTF-8) 操作
	设备厂商 设备厂商*/
	private String model;/*
	必填
	String
	P9/900/... P9/900/... P9/900/... 等 必须要做 必须要做 encode(UTFencode(UTF encode(UTF encode(UTF-8) 操作
	设备型号 设
	*/
	private String idfa;/*
	ios 必填
	String
	格式要求 格式要求 [0 -9a -fA -F]{8}F]{8} -[09a -fA -F]{4} F]{4} -
	[0 -9a -fA -F]{4}F]{4} -[0 -9a -fA -F]{4} -[0 -9a -fA -F]{ F]{12}
	iOS 设备的 设备的 IDFA IDFA*/
	private String imei;/*
	android android必填
	String
	格式要求 格式要求 [0 -9a -fA -F]{14,15}F]{14,15} F]{14,15}F]{14,15}
	Android Android 手机设 手机设 备的 IMEIIMEI*/
	private String mac;/*
	必填
	String
	格式要求 格式要求 [0 -9a -fA -F]{2}:[0F]{2}:[0 F]{2}:[0F]{2}:[0 -9a -fA -F]{2}:F]{2}: F]{2}:
	[0 -9a -fA -F]{2}:[0F]{2}:[0 F]{2}:[0 -9a -fA -F]{2}:[0 F]{2}:[0 -9a -fA -F]{ F]{2}:[0 -9a -fA -F]{2} F]{2}
	Mac 地址*/
	private String android_id ;/*
	android android必填
	String
	格式 要求 [0 -9A -Za -z]{16} z]{16}z]{16}
	Android Android
	*/
	private int sw;/*
	必填
	int
	屏幕宽度 屏幕宽度 Screen Width Screen Width Screen Width*/
	private int sh;/*
	必填
	int
	屏幕高度 屏幕高度 Screen Height eight
	网络
	*/
	private String ip;/*
	必填
	string
	手机 客户端 ip 地址*/
	private int ot;/*
	必填
	int
	0 ： 未知
	1 ： 中国移动 中国移动
	2 ： 中国电信 中国电信
	3 ： 中国联通 中国联通
	99 ： 其它运营商 其它运营商 其它运营商
	手机运营商 手机运营商
	Operator Operator Type*/
	private int ct;/*
	必填
	int
	0 ：无法探测当前网络状态 ：无法探测当前网络状态 ：无法探测当前网络状态 ：无法探测当前网络状态 ：无法探测当前网络状态 ：无法探测当前网络状态
	网络连接类型 网络连接类型 网
	*/
	private int gt;/*
	int
	1 : 全球卫星定位系统坐标。 全球卫星定位系统坐标。 全球卫星定位系统坐标。 全球卫星定位系统坐标。 全球卫星定位系统坐标。 全球卫星定位系统坐标。
	2 : 国家测绘局坐标系。 国家测绘局坐标系。 国家测绘局坐标系。 国家测绘局坐标系。 国家测绘局坐标系。
	3 : 百度坐标 百度坐标
	GPS C oordinate oordinate oordinate oordinate Type 坐标类型 坐标类型*/
	private double lng;/*
	Double
	Longitude Longitude ，经度 ，经度*/
	private double lat;/*
	Double
	Latitude, Latitude, 纬度*/
	private int gts;/*
	int
	时间戳，单位秒 时间戳，单位秒 时间戳，单位秒
	当前定位坐标的有 当前定位坐标的有 当前定位坐标的有 当前定位坐标的有 效时间戳。 效
	*/
	private String ua;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getCode_id() {
		return code_id;
	}

	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}

	public String getOs_ver() {
		return os_ver;
	}

	public void setOs_ver(String os_ver) {
		this.os_ver = os_ver;
	}

	public String getApp_pkg() {
		return app_pkg;
	}

	public void setApp_pkg(String app_pkg) {
		this.app_pkg = app_pkg;
	}

	public String getApp_ver() {
		return app_ver;
	}

	public void setApp_ver(String app_ver) {
		this.app_ver = app_ver;
	}

	public int getDevice_type() {
		return device_type;
	}

	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}

	public int getOs_type() {
		return os_type;
	}

	public void setOs_type(int os_type) {
		this.os_type = os_type;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
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

	public String getAndroid_id() {
		return android_id;
	}

	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getOt() {
		return ot;
	}

	public void setOt(int ot) {
		this.ot = ot;
	}

	public int getCt() {
		return ct;
	}

	public void setCt(int ct) {
		this.ct = ct;
	}

	public int getGt() {
		return gt;
	}

	public void setGt(int gt) {
		this.gt = gt;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public int getGts() {
		return gts;
	}

	public void setGts(int gts) {
		this.gts = gts;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

}
