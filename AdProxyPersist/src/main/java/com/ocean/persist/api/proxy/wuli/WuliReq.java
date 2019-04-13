package com.ocean.persist.api.proxy.wuli;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class WuliReq extends AbstractInvokeParameter {

	private static final long serialVersionUID = 5684998385005425215L;

	private String sid;
	
	private String c;
	
	private Long tm;
	
	private WuliReqDev device;
	
	private WuliReqNet net;
	
	private WuliReqApp app;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public Long getTm() {
		return tm;
	}

	public void setTm(Long tm) {
		this.tm = tm;
	}

	public WuliReqDev getDevice() {
		return device;
	}

	public void setDevice(WuliReqDev device) {
		this.device = device;
	}

	public WuliReqNet getNet() {
		return net;
	}

	public void setNet(WuliReqNet net) {
		this.net = net;
	}

	public WuliReqApp getApp() {
		return app;
	}

	public void setApp(WuliReqApp app) {
		this.app = app;
	}

	@Override
	public boolean validate() {
		return false;
	}
}
