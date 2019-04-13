package com.ocean.persist.api.proxy.borui;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class BoruiBidReq extends AbstractInvokeParameter {

	private static final long serialVersionUID = -1629312154002378151L;

	@Override
	public boolean validate() {
		return false;
		// TODO 不知道这个方法要干什么
	}

	//请求 ID 必填！接入方自定义请求 ID，[a-zA-Z0-9]{32}
	private String id;
	
	// 必填！API 版本，当前版本 2.0.5，填写错误会导致
	private String api_version;
	
	// 必填！--需要广告商务提供
	private BoruiBidReqConfig config;
	
	//设备信息-客户端
	private BoruiBidReqDevice device;
	
	//广告位信息
	private BoruiBidReqAdSlot adslot;
	
	//app 信息
	private BoruiBidReqApp app;
	
	//返回的广告数量。 默认是 1
	private Integer adcount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApi_version() {
		return api_version;
	}

	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}

	public BoruiBidReqConfig getConfig() {
		return config;
	}

	public void setConfig(BoruiBidReqConfig config) {
		this.config = config;
	}

	public BoruiBidReqDevice getDevice() {
		return device;
	}

	public void setDevice(BoruiBidReqDevice device) {
		this.device = device;
	}

	public BoruiBidReqAdSlot getAdslot() {
		return adslot;
	}

	public void setAdslot(BoruiBidReqAdSlot adslot) {
		this.adslot = adslot;
	}

	public BoruiBidReqApp getApp() {
		return app;
	}

	public void setApp(BoruiBidReqApp app) {
		this.app = app;
	}

	public Integer getAdcount() {
		return adcount;
	}

	public void setAdcount(Integer adcount) {
		this.adcount = adcount;
	}
	
	
}
