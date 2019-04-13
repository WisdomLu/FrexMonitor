package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneDevice   {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4231869831119851651L;
	private int dnt;
	private String ua;
	private String ip;
	private RedStoneGeo geo;
	private String idfa;
	private String didsha1;
	private String dpidsha1;
	private String macsha1;
	private String didmd5;
	private String dpidmd5;
	private String macmd5;
	private String carrier;
	private String language;
	private String make;
	private String model;
	private String os;
	private String osv;
	private int js;
	private int connectiontype;
	private int devicetype;
	private float s_density;
	private int sw;
	private int sh;
	private int orientation;
	private RedStoneExt ext;
	public int getDnt() {
		return dnt;
	}
	public void setDnt(int dnt) {
		this.dnt = dnt;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public RedStoneGeo getGeo() {
		return geo;
	}
	public void setGeo(RedStoneGeo geo) {
		this.geo = geo;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getDidsha1() {
		return didsha1;
	}
	public void setDidsha1(String didsha1) {
		this.didsha1 = didsha1;
	}
	public String getDpidsha1() {
		return dpidsha1;
	}
	public void setDpidsha1(String dpidsha1) {
		this.dpidsha1 = dpidsha1;
	}
	public String getMacsha1() {
		return macsha1;
	}
	public void setMacsha1(String macsha1) {
		this.macsha1 = macsha1;
	}
	public String getDidmd5() {
		return didmd5;
	}
	public void setDidmd5(String didmd5) {
		this.didmd5 = didmd5;
	}
	public String getDpidmd5() {
		return dpidmd5;
	}
	public void setDpidmd5(String dpidmd5) {
		this.dpidmd5 = dpidmd5;
	}
	public String getMacmd5() {
		return macmd5;
	}
	public void setMacmd5(String macmd5) {
		this.macmd5 = macmd5;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public int getJs() {
		return js;
	}
	public void setJs(int js) {
		this.js = js;
	}
	public int getConnectiontype() {
		return connectiontype;
	}
	public void setConnectiontype(int connectiontype) {
		this.connectiontype = connectiontype;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}
	public float getS_density() {
		return s_density;
	}
	public void setS_density(float s_density) {
		this.s_density = s_density;
	}
	public int getSw() {
		return sw;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
