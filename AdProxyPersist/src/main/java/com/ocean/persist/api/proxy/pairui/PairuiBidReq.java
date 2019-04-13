package com.ocean.persist.api.proxy.pairui;

import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class PairuiBidReq extends AbstractInvokeParameter {

	private static final long serialVersionUID = -6917913770973931930L;

	// required String 请求标识，全局唯一，由流量方（媒体）分配；
	private String id;
	
	// optional bool 标明请求为测试流量，0：true，1：false；
	private Boolean istest;
	
	// optional  integer  标明响应是否需要是https。0：http，1：https。
	// 为空时，非pc且为app且操作系统是ios或者未知，返回https
	private Integer secure;
	
	// required  object array  广告曝光（广告位）对象Imp数组，至少一个
	private List<PairuiBidImp> imp;
	
	// optional  object  网站Site对象。注：网页资源（含PC和Mobile）必填属性；
	private PairuiBidReqSite site;
	
	// optional  object  移动App对象。注：移动APP资源必填属性；
	private PairuiBidReqApp app;
	
	// required object 设备信息对象
	private PairuiBidReqDev device;
	
	// optional  object  客户端Geo对象，建议提供。
	private PairuiBidReqGeo geo;
	
	// optional  object  用户User对象，建议提供。
	private PairuiBidReqUser user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIstest() {
		return istest;
	}

	public void setIstest(Boolean istest) {
		this.istest = istest;
	}

	public Integer getSecure() {
		return secure;
	}

	public void setSecure(Integer secure) {
		this.secure = secure;
	}

	public List<PairuiBidImp> getImp() {
		return imp;
	}

	public void setImp(List<PairuiBidImp> imp) {
		this.imp = imp;
	}

	public PairuiBidReqSite getSite() {
		return site;
	}

	public void setSite(PairuiBidReqSite site) {
		this.site = site;
	}

	public PairuiBidReqApp getApp() {
		return app;
	}

	public void setApp(PairuiBidReqApp app) {
		this.app = app;
	}

	public PairuiBidReqDev getDevice() {
		return device;
	}

	public void setDevice(PairuiBidReqDev device) {
		this.device = device;
	}

	public PairuiBidReqGeo getGeo() {
		return geo;
	}

	public void setGeo(PairuiBidReqGeo geo) {
		this.geo = geo;
	}

	public PairuiBidReqUser getUser() {
		return user;
	}

	public void setUser(PairuiBidReqUser user) {
		this.user = user;
	}

	@Override
	public boolean validate() {
		return false;
	}
}
