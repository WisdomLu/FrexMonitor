package com.ocean.persist.api.proxy.xunfei;

import java.util.List;

/**
 * App
 */
public class XunfeiImp  {

	public String getAdunit_id() {
		return adunit_id;
	}
	public void setAdunit_id(String adunit_id) {
		this.adunit_id = adunit_id;
	}
	public double getBidfloor() {
		return bidfloor;
	}
	public void setBidfloor(double bidfloor) {
		this.bidfloor = bidfloor;
	}
	public XunfeiPmp getPmp() {
		return pmp;
	}
	public void setPmp(XunfeiPmp pmp) {
		this.pmp = pmp;
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
	public List<Integer> getDp_support_status() {
		return dp_support_status;
	}
	public void setDp_support_status(List<Integer> dp_support_status) {
		this.dp_support_status = dp_support_status;
	}
	public List<Integer> getVoice_ad_support_status() {
		return voice_ad_support_status;
	}
	public void setVoice_ad_support_status(List<Integer> voice_ad_support_status) {
		this.voice_ad_support_status = voice_ad_support_status;
	}
	public int getSecure() {
		return secure;
	}
	public void setSecure(int secure) {
		this.secure = secure;
	}
	public XunfeiDebug getDebug() {
		return debug;
	}
	public void setDebug(XunfeiDebug debug) {
		this.debug = debug;
	}
	private String adunit_id;
	private double bidfloor;
	private XunfeiPmp pmp;
	private int adw;
	private int adh;
	private List<Integer> dp_support_status;
	private List<Integer> voice_ad_support_status;
	private int secure;
	private XunfeiDebug debug;
}
