package com.ocean.persist.api.proxy.ruangaoyun;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunDevice    {

	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public Integer getScreenorientation() {
		return screenorientation;
	}
	public void setScreenorientation(Integer screenorientation) {
		this.screenorientation = screenorientation;
	}
	public RuanGaoYunGeo getGeo() {
		return geo;
	}
	public void setGeo(RuanGaoYunGeo geo) {
		this.geo = geo;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -828969093189894524L;
    private String id;
    private String dpid;
    private String ua;
    private String ip;
    private String carrier;
    private String model;
    private String os;
    private String osv;
    private Integer connectiontype;
    private Integer devicetype;
    private String mac;
    private Integer w;
    private Integer h;
    private Integer screenorientation;
    private RuanGaoYunGeo geo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDpid() {
		return dpid;
	}
	public void setDpid(String dpid) {
		this.dpid = dpid;
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
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
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
	public Integer getConnectiontype() {
		return connectiontype;
	}
	public void setConnectiontype(Integer connectiontype) {
		this.connectiontype = connectiontype;
	}
	public Integer getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
    
}
