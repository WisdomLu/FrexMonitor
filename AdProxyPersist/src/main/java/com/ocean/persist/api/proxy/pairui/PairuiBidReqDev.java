package com.ocean.persist.api.proxy.pairui;

public class PairuiBidReqDev {

	// required String 浏览器user-agent信息；
	private String ua;
	
	// required String ipv4地址，客户端IP
	private String ip;
	
	// required integer 0.未知;1.pc;2.phone;3.pad;4.tv
	private Integer devicetype;
	
	// optional String 设备制造商
	private String make;
	
	// optional String 设备型号
	private String model;
	
	// optional String 设备操作系统;
	// 如：Android、IOS、WindowsPhone
	private String os;
	
	// osv optional  String 操作系统版本;
	// 例如4.2.1，主版本为4,次版本为2,小版本为1
	private String osv;
	
	// w optional  integer 设备屏幕横向分辨率
	private Integer w;
	
	// h optional  integer 设备屏幕纵向分辨率
	private Integer h;
	
	// carrier optional  integer 运营商。0.未知;1.中国移动;2.中国联通;3.中国电信
	private Integer carrier;
	
	// connectiontype optional  integer 网络连接类型。0.未知;1.WIFI;2.2G;3.3G;4.4G;5.5G
	private Integer connectiontype;
	
	// ifa optional  String iOS终端设备的identifier for advertising明文，保持字母大写。
	// 注：iOS资源请求，ifa信息为必填
	private String ifa;
	
	// imeisha1 optional  String 设备IMEI（MEID）号,SHA1加密串。
	// 注：Android资源请求，imei信息为必填；
	private String imeisha1;
	
	// imeimd5 optional  String 设备IMEI（MEID）号,MD5加密串。
	// 注：Android资源请求，imei信息为必填；
	private String imeimd5;
	
	// imeiplain optional String 设备IMEI（MEID）号,明文串
	// 注：Android资源请求，imei信息为必填；
	private String imeiplain;
	
	// aidsha1 optional  String 用户终端的 AndroidID,SHA1加密串
	private String aidsha1;
	
	// aidmd5 optional  String 用户终端的 AndroidID,MD5加密串
	private String aidmd5;
	
	// aidplain optional String 用户终端的 AndroidID,明文串
	private String aidplain;
	
	// macsha1 optional  String 终端网卡MAC地址,SHA1加密串
	private String macsha1;
	
	// macmd5 optional  String 终端网卡MAC地址,MD5加密串
	private String macmd5;
	
	// macplain optional String 终端网卡MAC地址,明文串
	private String macplain;
	
	// openudidsha1 optional  String IOS终端设备的OpenUDID,SHA1加密串
	private String openudidsha1;
	
	// openudidmd5 optional  String IOS终端设备的OpenUDID,MD5加密串
	private String openudidmd5;
	
	// openudidplain optional String IOS终端设备的OpenUDID,明文串
	private String openudidplain;

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

	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
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

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getCarrier() {
		return carrier;
	}

	public void setCarrier(Integer carrier) {
		this.carrier = carrier;
	}

	public Integer getConnectiontype() {
		return connectiontype;
	}

	public void setConnectiontype(Integer connectiontype) {
		this.connectiontype = connectiontype;
	}

	public String getIfa() {
		return ifa;
	}

	public void setIfa(String ifa) {
		this.ifa = ifa;
	}

	public String getImeisha1() {
		return imeisha1;
	}

	public void setImeisha1(String imeisha1) {
		this.imeisha1 = imeisha1;
	}

	public String getImeimd5() {
		return imeimd5;
	}

	public void setImeimd5(String imeimd5) {
		this.imeimd5 = imeimd5;
	}

	public String getImeiplain() {
		return imeiplain;
	}

	public void setImeiplain(String imeiplain) {
		this.imeiplain = imeiplain;
	}

	public String getAidsha1() {
		return aidsha1;
	}

	public void setAidsha1(String aidsha1) {
		this.aidsha1 = aidsha1;
	}

	public String getAidmd5() {
		return aidmd5;
	}

	public void setAidmd5(String aidmd5) {
		this.aidmd5 = aidmd5;
	}

	public String getAidplain() {
		return aidplain;
	}

	public void setAidplain(String aidplain) {
		this.aidplain = aidplain;
	}

	public String getMacsha1() {
		return macsha1;
	}

	public void setMacsha1(String macsha1) {
		this.macsha1 = macsha1;
	}

	public String getMacmd5() {
		return macmd5;
	}

	public void setMacmd5(String macmd5) {
		this.macmd5 = macmd5;
	}

	public String getMacplain() {
		return macplain;
	}

	public void setMacplain(String macplain) {
		this.macplain = macplain;
	}

	public String getOpenudidsha1() {
		return openudidsha1;
	}

	public void setOpenudidsha1(String openudidsha1) {
		this.openudidsha1 = openudidsha1;
	}

	public String getOpenudidmd5() {
		return openudidmd5;
	}

	public void setOpenudidmd5(String openudidmd5) {
		this.openudidmd5 = openudidmd5;
	}

	public String getOpenudidplain() {
		return openudidplain;
	}

	public void setOpenudidplain(String openudidplain) {
		this.openudidplain = openudidplain;
	}
}
