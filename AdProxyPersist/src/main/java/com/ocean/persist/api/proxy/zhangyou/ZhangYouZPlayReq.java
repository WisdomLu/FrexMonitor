package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouZPlayReq  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3170443540651546555L;
    private String app_channel;//应用渠道id
    private String uuid;//
    private String request_id;
    private int preload;//插屏是否预加载 0 不预加载 插屏，1，预加载
    private int banner_interval;//Banner轮播时间，单位秒
    private int intersect_interval;//插屏轮播时间，单位秒
    private int splash_interval;//开屏轮播时间，单位秒
    private int is_close;//是否可关闭， 0：不可关闭， 1：可关闭
    private String ad_loc_id;//广告位id
    private String ios_idfv;//ios idfv
    private String open_uuid;//open_uuid, 设备号
	public String getApp_channel() {
		return app_channel;
	}
	public void setApp_channel(String app_channel) {
		this.app_channel = app_channel;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public int getPreload() {
		return preload;
	}
	public void setPreload(int preload) {
		this.preload = preload;
	}
	public int getBanner_interval() {
		return banner_interval;
	}
	public void setBanner_interval(int banner_interval) {
		this.banner_interval = banner_interval;
	}
	public int getIntersect_interval() {
		return intersect_interval;
	}
	public void setIntersect_interval(int intersect_interval) {
		this.intersect_interval = intersect_interval;
	}
	public int getSplash_interval() {
		return splash_interval;
	}
	public void setSplash_interval(int splash_interval) {
		this.splash_interval = splash_interval;
	}
	public int getIs_close() {
		return is_close;
	}
	public void setIs_close(int is_close) {
		this.is_close = is_close;
	}
	public String getAd_loc_id() {
		return ad_loc_id;
	}
	public void setAd_loc_id(String ad_loc_id) {
		this.ad_loc_id = ad_loc_id;
	}
	public String getIos_idfv() {
		return ios_idfv;
	}
	public void setIos_idfv(String ios_idfv) {
		this.ios_idfv = ios_idfv;
	}
	public String getOpen_uuid() {
		return open_uuid;
	}
	public void setOpen_uuid(String open_uuid) {
		this.open_uuid = open_uuid;
	}
    
	
	
	
	
}
