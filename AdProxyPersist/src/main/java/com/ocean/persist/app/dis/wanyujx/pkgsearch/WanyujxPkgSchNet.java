package com.ocean.persist.app.dis.wanyujx.pkgsearch;

public class WanyujxPkgSchNet {
	private String ip ;//是IPv4 地址
	private int connectionType  ;//是网络类型0--CONNECTION_UNKNOWN
/*	1--CELL_UNKNOWN
	2--CELL_2G
	3--CELL_3G
	4--CELL_4G
	5--CELL_5G
	100—WIFI
	101—ETHERNET
	999--NEW_TYPE*/
	private int operatorType  ;//是运营商类型0--UNKNOWN_OPERATOR
/*	1--CHINA_MOBILE
	2--CHINA_TELECOM
	3--CHINA_UNICOM
	99--OTHER_OPERATOR*/
	private String cellular_id  ;//否基站ID
	private float lat  ;///是纬度(无法获取填0) Float 类型
	private float lon  ;//是经度(无法获取填0) Float 类型
	public String getIp() {
		return ip;
	}
	public int getConnectionType() {
		return connectionType;
	}
	public int getOperatorType() {
		return operatorType;
	}
	public String getCellular_id() {
		return cellular_id;
	}
	public float getLat() {
		return lat;
	}
	public float getLon() {
		return lon;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}
	public void setOperatorType(int operatorType) {
		this.operatorType = operatorType;
	}
	public void setCellular_id(String cellular_id) {
		this.cellular_id = cellular_id;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
}
