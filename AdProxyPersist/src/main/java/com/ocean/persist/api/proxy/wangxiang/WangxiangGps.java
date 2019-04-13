package com.ocean.persist.api.proxy.wangxiang;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangGps {
	private String coordinate_type	;//GPS坐标类型	选填， WGS84 = 1 全球卫星定位系统坐标系,GCJ02 = 2 国家测绘局坐标系,BD09 = 3 百度坐标系
	private String 		longitude;//	GPS坐标经度	选填
	private String 		latitude;//	GPS坐标纬度	选填
	private String 		timestamp;//	GPS时间戳信息	选填
	public String getCoordinate_type() {
		return coordinate_type;
	}
	public void setCoordinate_type(String coordinate_type) {
		this.coordinate_type = coordinate_type;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
