package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiV2Geo {
	private int type;//	required	unit32	坐标类型（
/*	1.WGS84
	2.GCJ02
	3.	BD09）*/
	private double longitude;//	required	double	经度
	private double latitude;//	required	double	纬度
	private long timestamp;//	required	unit32	时间戳
	private int source	;//
	/*optional	unit32	定位来源（
	1.NATIVE
	2.BAIDU）*/
	public int getType() {
		return type;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public int getSource() {
		return source;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public void setSource(int source) {
		this.source = source;
	}
}
