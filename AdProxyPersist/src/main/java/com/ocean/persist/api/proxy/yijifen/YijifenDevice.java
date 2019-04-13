package com.ocean.persist.api.proxy.yijifen;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class YijifenDevice  {

	private static final long serialVersionUID = 1L;
	
	private String ua;// 选填 浏览器UserAgent的base64编码
	private String client_ip;// 必填	221.122.127.111	终端用户IP地址，必须是外网IP
	private Integer devicetype;// 必填 0.未知 1.手机 2:平板电脑
	private String brand;// 可填	samsung	手机品牌
	private String telModel;// 可填	GalaxyS6	设备型号，小写，去掉空格
	private String os_version;// 可填	5.0	操作系统版本号
	private Integer device_pixel_ratio;// 选填 设备像素比例（用于广告位size缩放，千分位表示，默认1000）
	private Integer screen_orientation;// 选填0.未知 1.竖屏 2.横屏 默认：0.未知
	private String imei;// 必填		安卓必传
	private String idfa;// 必填		IOS必传
	private String mac;// 选填		客户端的mac
	private Integer operator;// 必填 0.未知 1.移动 2.联通 3.电信
	private String network;// 必填 0.未知 1.wifi 2.2G 3.3G 4.4G
	private String ext;// 选填 扩展字段，用来扩展信息
	private String androidid;
	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTelModel() {
		return telModel;
	}

	public void setTelModel(String telModel) {
		this.telModel = telModel;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public Integer getDevice_pixel_ratio() {
		return device_pixel_ratio;
	}

	public void setDevice_pixel_ratio(Integer device_pixel_ratio) {
		this.device_pixel_ratio = device_pixel_ratio;
	}

	public Integer getScreen_orientation() {
		return screen_orientation;
	}

	public void setScreen_orientation(Integer screen_orientation) {
		this.screen_orientation = screen_orientation;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAndroidid() {
		return androidid;
	}

	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}
}
