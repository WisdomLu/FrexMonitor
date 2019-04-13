package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

public class MexDevice {

	private static final long serialVersionUID = 1L;
	
	private String ua;// optional 	user agent，在请求头中已经包含，可以省略 
	private String ip;// required 	设备的ipv4地址 
	// required 	
	private String devicetype;
	private String make;// recommended, 	制造商，如：Apple，常见制造商见附录5 make字典 
	private String model;// recommended 	型号，如：iPhone6s，常见型号见附录6 model字典 
	private String os;// recommended 	操作系统，目前只使用两种:”Android”或”iOS” 
	private String osv;// recommended 	os版本 
	private String imei;// recommended 	设备imei值，我们用它填充BidRequest中的did 
	private String imsi;
	private String idfa;// recommended 	iOS设备的idfa，我们用它来填充BidRequest中的 dpid 
	private String androidid;// recommended 	Android设备的Android ID，我们用它来填充BidRequest中的dpid 
	private String adid;//  	string, recommended 	Android设备的adid，我们优先用它来填充 BidRequest中的dpid 
	private String mac;// recommended 	设备硬件地址，我们用它来填充BidRequest中的 mac 
	// recommended 	连接类型，枚举如下：  0：未知  1：以太网  2：WIFI  3：手机网络  4：2G 5: 3G  6: 4G 
	private String connectiontype;
	// recommended 	运营商，规范格式为”China Mobile”, “China Unicom”, “China Telecom”，分别表示中国移动、 中国联通、中国电信
	private String carrier; 
	private Integer hww;// recommended 	屏幕像素宽度 
	private Integer hwh;// recommended 	屏幕像素高度 
	private MexGeo geo;// 经纬度
	
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
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
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

	public String getAndroidid() {
		return androidid;
	}

	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Integer getHww() {
		return hww;
	}

	public void setHww(Integer hww) {
		this.hww = hww;
	}

	public Integer getHwh() {
		return hwh;
	}

	public void setHwh(Integer hwh) {
		this.hwh = hwh;
	}

	public MexGeo getGeo() {
		return geo;
	}

	public void setGeo(MexGeo geo) {
		this.geo = geo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getConnectiontype() {
		return connectiontype;
	}

	public void setConnectiontype(String connectiontype) {
		this.connectiontype = connectiontype;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
}
