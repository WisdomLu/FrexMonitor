package com.ocean.persist.api.proxy.youken;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class YoukenGps {
	private int coordinate_type;/*
	int
	是
	坐标类型。1：全球卫星定位系统
	坐标系，2：国家测绘局坐标系，3：
	百度坐标系，4：其他坐标系*/
	private double lon;/*
	double
	是
	经度*/
	private double lat;/*
	double
	是
	纬度*/
	private long coord_time;/*
	int
	是
	时间戳，单位秒*/
	private double location_accuracy;
	private int timestamp;
	public int getCoordinate_type() {
		return coordinate_type;
	}
	public void setCoordinate_type(int coordinate_type) {
		this.coordinate_type = coordinate_type;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLocation_accuracy() {
		return location_accuracy;
	}
	public void setLocation_accuracy(double location_accuracy) {
		this.location_accuracy = location_accuracy;
	}
	public long getCoord_time() {
		return coord_time;
	}
	public void setCoord_time(long coord_time) {
		this.coord_time = coord_time;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

}
