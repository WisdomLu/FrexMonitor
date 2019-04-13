package com.ocean.persist.api.proxy.youken;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class YoukenNetwork {
	private String ip;/*
	string
	是
	用户设备的公网 IP，格式： 255.255.255.255*/
	private int connect_type;/*
	int
	是
	0：未知连接，1：以太网，2：WiFi，
	3：未知蜂窝网络，4:2G，5:3G，6:4G*/
	private int carrier;/*
	int
	是
	0：未知运营商，1：中国移动，2： 中国电信，3：中国联通，4：其他 运营商。*/
	private String cellular_id;/*
	string
	否
	当前连接的运营商基站 ID，用于辅 助用户定位*/
	private String lac;/*
	string
	是
	基站位置区域码*/
	private String mcc;/*
	string
	是
	移动国家代码（中国 460）*/
	private String bss_id;/*
	string
	是
	wifi 地址*/

	private String     ssid;
	private int     lksd;
	private int     rssi;
	private int     roaming;
  
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getConnect_type() {
		return connect_type;
	}
	public void setConnect_type(int connect_type) {
		this.connect_type = connect_type;
	}
	public int getCarrier() {
		return carrier;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public String getCellular_id() {
		return cellular_id;
	}
	public void setCellular_id(String cellular_id) {
		this.cellular_id = cellular_id;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getBss_id() {
		return bss_id;
	}
	public void setBss_id(String bss_id) {
		this.bss_id = bss_id;
	}
	public int getLksd() {
		return lksd;
	}
	public void setLksd(int lksd) {
		this.lksd = lksd;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public int getRoaming() {
		return roaming;
	}
	public void setRoaming(int roaming) {
		this.roaming = roaming;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
}
