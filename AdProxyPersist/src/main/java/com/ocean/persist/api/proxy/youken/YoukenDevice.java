package com.ocean.persist.api.proxy.youken;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月6日 
      @version 1.0 
 */
public class YoukenDevice {
	private int device_type;//	DeviceType		是	设备类型， 1：移动设备，2：个人计算机，3：连接电视，4：手机，5：平板，6：连通装置，7：机顶盒。
	private int os_type;//	OsType		否	操作系统类型:1:android,2:ios。
	private String os_version;//	string		是	操作系统版本
	private String vendor;//	string	“”	是	设备厂商名称，中文需要UTF-8编码
	private String model	;//string	“”	是	不填写无广告 调用android.os.Build.MODEL获取,中文需要UTF-8编码
	private String manufacturer;//	String	Unknown	是	不填写无广告 调用android.os.Build.MANUFACTURER获取, 中文需要UTF-8编码
	private String idfa;//	String	“”	是	IOS设备的IDFA
	private String android_id	;//string		是	Android设备系统ID
	private String imei;//	String		是	Android手机设备的IMEI
	private String imei_md5	;//string	“”	是	Android手机设备的IMEI_MD5
	private String mac;//	string		是	设备的WiFi网卡硬件mac号
	private int w	;//int		是	设备屏幕宽度
	private int h;//	int		是	设备屏幕高度
	private double dpi	;//double		是	像素密度。即1英寸中含多少个像素点。
	private String ua	;//String		是	系统User-Agent
	private String imsi;
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
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImei_md5() {
		return imei_md5;
	}
	public void setImei_md5(String imei_md5) {
		this.imei_md5 = imei_md5;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public double getDpi() {
		return dpi;
	}
	public void setDpi(double dpi) {
		this.dpi = dpi;
	}
	public String getUa() {
		return ua;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}

}
