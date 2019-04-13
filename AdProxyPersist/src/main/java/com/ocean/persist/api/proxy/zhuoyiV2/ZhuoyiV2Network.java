package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiV2Network {
	private String ip	;//required	string	设备当前 IP 地址(ipv4)
	private int type	;//required	unit32	设备网络环境（
/*	1.WIFI
	2.UNKNONW
	3.2G
	4.3G
	5.	4G）*/
	private String imsi;//	optional	string	imsi 号
	private ZhuoyiV2Cellular cellular;//	optional	cellular object	基站信息
	//wifis	optional	array of wifi object	wifi 热点
	public String getIp() {
		return ip;
	}
	public int getType() {
		return type;
	}
	public String getImsi() {
		return imsi;
	}
	public ZhuoyiV2Cellular getCellular() {
		return cellular;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setCellular(ZhuoyiV2Cellular cellular) {
		this.cellular = cellular;
	}
}
