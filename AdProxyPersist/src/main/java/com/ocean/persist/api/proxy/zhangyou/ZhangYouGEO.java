package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouGEO   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3439162167796624919L;
	private float lat;//维度
	private float lon;//经度
	private int accu;//精度
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
	public int getAccu() {
		return accu;
	}
	public void setAccu(int accu) {
		this.accu = accu;
	}
	

}
