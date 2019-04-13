package com.ocean.persist.api.proxy.uniplay;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年9月7日 
      @version 1.0 
 */
public class UniPlayAdPullParams    extends AdPullParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1629630692786953898L;
	private UniPlayApp app;
	private UniPlayDevice device;
	private UniPlayGeo geo;
	private int adw;
	private int adh;
	private String appid;
	private String slotid;
	private String  ip;
	public UniPlayApp getApp() {
		return app;
	}
	public void setApp(UniPlayApp app) {
		this.app = app;
	}
	public UniPlayDevice getDevice() {
		return device;
	}
	public void setDevice(UniPlayDevice device) {
		this.device = device;
	}
	public UniPlayGeo getGeo() {
		return geo;
	}
	public void setGeo(UniPlayGeo geo) {
		this.geo = geo;
	}
	public int getAdw() {
		return adw;
	}
	public void setAdw(int adw) {
		this.adw = adw;
	}
	public int getAdh() {
		return adh;
	}
	public void setAdh(int adh) {
		this.adh = adh;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSlotid() {
		return slotid;
	}
	public void setSlotid(String slotid) {
		this.slotid = slotid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
			
		

}
