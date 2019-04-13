package com.ocean.persist.api.proxy.souying;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class SouyingAdPullParams    extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;

	private String appid;// 应用id	联系我们商务索取
	private Integer adslottype;// 广告位类型	1横幅，2插屏，3开屏
	private String apiver;// api版本号	接口版本号,当前为1
	private String appversion;// android:vers ionCode	1
	private String connecttype;// 连接类型	"1":wifi; "2":2g; "3":3g; "4":4g
	private String countrycode;// 国家编码	CN
	private String devicename;// 设备名	2014813，CHM-UL00
	private String dlanguage;// 设备语言	zh
	private String dmanufacturer;// 设备厂商	Xiaomi
	private String dversion;// 系统版本	4.4.4
	private Integer height;// 屏幕高	1280
	private String imei;// imei	869894026847042
	private String ips;// ip	10.10.17.32
	private String macadd;// mac地址	10:2a:b3:1d:2b:7a
	private String pkgname;// 应用包名	com.example.dspprotocol
	private Integer screendensity;// 屏幕密度	320
	private Integer screentype;// 屏幕类型	2:手机，1:平板
	private String simNetworkOperator;// sim卡运营商	
	private String simcountryiso;// sim卡国家	
	private String simname;// imsi	
	private String uuid;// Uuid	3d1e63d7-4e0b-477d-b868e711f6a5e917
	private Integer width;// 屏幕宽度	720
	private String wifibssid;// Wifibssid	50:06:04:a7:6c:40
	private String wifissid;// Wifi名称	sou-4
	private String ua;
	private String androidid;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getAdslottype() {
		return adslottype;
	}

	public void setAdslottype(Integer adslottype) {
		this.adslottype = adslottype;
	}

	public String getApiver() {
		return apiver;
	}

	public void setApiver(String apiver) {
		this.apiver = apiver;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getConnecttype() {
		return connecttype;
	}

	public void setConnecttype(String connecttype) {
		this.connecttype = connecttype;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getDlanguage() {
		return dlanguage;
	}

	public void setDlanguage(String dlanguage) {
		this.dlanguage = dlanguage;
	}

	public String getDmanufacturer() {
		return dmanufacturer;
	}

	public void setDmanufacturer(String dmanufacturer) {
		this.dmanufacturer = dmanufacturer;
	}

	public String getDversion() {
		return dversion;
	}

	public void setDversion(String dversion) {
		this.dversion = dversion;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getMacadd() {
		return macadd;
	}

	public void setMacadd(String macadd) {
		this.macadd = macadd;
	}

	public String getPkgname() {
		return pkgname;
	}

	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}

	public Integer getScreendensity() {
		return screendensity;
	}

	public void setScreendensity(Integer screendensity) {
		this.screendensity = screendensity;
	}

	public Integer getScreentype() {
		return screentype;
	}

	public void setScreentype(Integer screentype) {
		this.screentype = screentype;
	}

	public String getSimNetworkOperator() {
		return simNetworkOperator;
	}

	public void setSimNetworkOperator(String simNetworkOperator) {
		this.simNetworkOperator = simNetworkOperator;
	}

	public String getSimcountryiso() {
		return simcountryiso;
	}

	public void setSimcountryiso(String simcountryiso) {
		this.simcountryiso = simcountryiso;
	}

	public String getSimname() {
		return simname;
	}

	public void setSimname(String simname) {
		this.simname = simname;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getWifibssid() {
		return wifibssid;
	}

	public void setWifibssid(String wifibssid) {
		this.wifibssid = wifibssid;
	}

	public String getWifissid() {
		return wifissid;
	}

	public void setWifissid(String wifissid) {
		this.wifissid = wifissid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getAndroidid() {
		return androidid;
	}

	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
