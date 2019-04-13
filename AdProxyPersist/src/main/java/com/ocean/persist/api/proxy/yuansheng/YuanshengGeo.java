package com.ocean.persist.api.proxy.yuansheng;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengGeo  extends AbstractBaseEntity{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -3432167070390845796L;
	private float lat;// float	Y	纬度
	private float lon;//	float	Y	经度
	private String country;//	string	N	国家
	private int type;//	integer	N	经纬度获取途径，见A10。
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}


}
