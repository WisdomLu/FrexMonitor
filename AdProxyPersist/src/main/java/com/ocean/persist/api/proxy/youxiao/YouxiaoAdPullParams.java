package com.ocean.persist.api.proxy.youxiao;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月15日 
      @version 1.0 
 */
public class YouxiaoAdPullParams     extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8858668737702296287L;
	private String appid;//	媒体id	是	
	private String advplaceid;//		广告位id	是	
	private int muidtype	;//	移动终端标识类型	是	取值: 1：imei 2：ifa 3：pc
	private int mode	;//	终端接入方式	是	取值: 1：js 2：api 3：sdk
	private String mac	;//	设备的mac地址	建议	Android必填
	private String memory;//		如果是pc,则表示内存，如果是手机表示RAM	否	
	private String harddisk	;//	如果是pc,则表示硬盘，如果是手机表示ROM	否	
	private String 	cpu	;//	CPU的型号	否	
	private String os	;//	操作系统	是	
	private String dpi;//		屏幕分辨率	建议	例：1366*768
	private String graphics	;//	显卡型号	否	
	private String devicetype	;//	设备类型	否	手机：phone,电脑：pc,平板：pad,手表：watch
	private int c_ori	;//	设备横竖屏,可能取值：0, iphone 4s屏幕正对自己，home键靠下。90, 顺时针旋转90度。180 270 	建议	如:"c_ori":90
	private String lat	;//	纬度	建议	
	private String lng	;//	经度	建议	
	private String osversion	;//	操作系统的版本	必填	如：window
	private String imeiidfa	;//	Imei或者idfa	必填	Android：imei ios:idfa
	private String model	;//	设备型号	必填	设备型号
	private String c_device	;//	设备品牌	必填	设备品牌
	private int network	;//	网络类型	必填	取值: 0:unknown 1:wifi 2:2g 3:3g 4:4g

	private int carrieroperator	;//	运营商	运营商	取值: 0:unknown 1:移动 2:联通 3:电信

	private String sdkversion	;//	SDK接口版本	建议	Sdk接入 必填
	private int c_w	;//	屏幕宽		
	private int c_h	;//	屏幕高		
	private String ip	;//	客户端ip地址	必填	
	private String c_pkgname	;//	App包名	建议	终端为app的必填
	private String sizeid	;//	尺寸id	必填	附录1
	private String ua	;//	浏览器ua	建议	Js接入必填
	private String density	;//	设备屏幕的密度	建议	取值:1; 1.5; 2; 3 sdk接入必填
	private String appversion;//		App版本号	建议	
	private String anid	;//	Android Id	;//	建议	Android必填
	private String 	domains	;//	s域名	建议	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAdvplaceid() {
		return advplaceid;
	}
	public void setAdvplaceid(String advplaceid) {
		this.advplaceid = advplaceid;
	}
	public int getMuidtype() {
		return muidtype;
	}
	public void setMuidtype(int muidtype) {
		this.muidtype = muidtype;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getHarddisk() {
		return harddisk;
	}
	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getDpi() {
		return dpi;
	}
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}
	public String getGraphics() {
		return graphics;
	}
	public void setGraphics(String graphics) {
		this.graphics = graphics;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public int getC_ori() {
		return c_ori;
	}
	public void setC_ori(int c_ori) {
		this.c_ori = c_ori;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public String getImeiidfa() {
		return imeiidfa;
	}
	public void setImeiidfa(String imeiidfa) {
		this.imeiidfa = imeiidfa;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getC_device() {
		return c_device;
	}
	public void setC_device(String c_device) {
		this.c_device = c_device;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}

	public String getSdkversion() {
		return sdkversion;
	}
	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}
	public int getC_w() {
		return c_w;
	}
	public void setC_w(int c_w) {
		this.c_w = c_w;
	}
	public int getC_h() {
		return c_h;
	}
	public void setC_h(int c_h) {
		this.c_h = c_h;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getC_pkgname() {
		return c_pkgname;
	}
	public void setC_pkgname(String c_pkgname) {
		this.c_pkgname = c_pkgname;
	}
	public String getSizeid() {
		return sizeid;
	}
	public void setSizeid(String sizeid) {
		this.sizeid = sizeid;
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
	public String getAppversion() {
		return appversion;
	}
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
	public String getAnid() {
		return anid;
	}
	public void setAnid(String anid) {
		this.anid = anid;
	}
	public String getDomains() {
		return domains;
	}
	public void setDomains(String domains) {
		this.domains = domains;
	}
	public int getCarrieroperator() {
		return carrieroperator;
	}
	public void setCarrieroperator(int carrieroperator) {
		this.carrieroperator = carrieroperator;
	}

}
