package com.ocean.persist.api.proxy.yileyun;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunDevice {
	private String did;/*
	string
	Required
	设备 id, android 系统填空；ios 系统为 idfa*/
	private String imei;/*
	string
	Required
	设备 IMEI，若 IOS 设备拿不到则填空*/
	private String android_id;/*
	string
	Optional
	安卓设备的 android_id,安卓设备必填*/
	private String uuid;/*
	string
	Optional
	安卓手机系统生成的设备*/
	private String openudid;/*
	String
	Optional
	IOS 软件生成替代 UDID 的标识*/
	private int type;/*
	DeviceType
	Required
	设 备 类 型 ： Unknown=0 ； Phone/ 手 机
	= 1 ； Tablet/平板=2；TV/智能电视=3*/
	private int mccmnc;/*
	uint32
	Optional
	运营商信息：国家码(460)+运营商编码比如
	46000、46001*/
	private int os;/*
	OsType
	Required
	操作系统类型：
	Unknown=0;Android=1;IOS=2;Windows=3*/
	private String os_version;/*
	string
	Required
	操作系统版本*/
	private String vendor;/*
	string
	Required
	设备厂商，如 Apple, Samsung*/
	private String model;/*
	string
	Required
	设备型号，如 iPhone5s, Galaxy*/
	private String language;/*
	string
	Optional
	设备设置的语言：中文、英文、其他*/
	private int conn_type;/*
	Connection Type
	Required
	设 备 的 网 络 类 型 ： Unknown=0 ；
	Wifi=1 ； 2G=2；3G=3；4G=4*/
	private String mac;/*
	string
	Optional
	设备的 mac 地址*/
	private int screen_width;/*
	uint32
	Required
	设备屏宽*/
	private int screen_height;/*
	uint32
	Required
	设备屏高*/
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMccmnc() {
		return mccmnc;
	}
	public void setMccmnc(int mccmnc) {
		this.mccmnc = mccmnc;
	}

	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getConn_type() {
		return conn_type;
	}
	public void setConn_type(int conn_type) {
		this.conn_type = conn_type;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getScreen_width() {
		return screen_width;
	}
	public void setScreen_width(int screen_width) {
		this.screen_width = screen_width;
	}
	public int getScreen_height() {
		return screen_height;
	}
	public void setScreen_height(int screen_height) {
		this.screen_height = screen_height;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
}
