package com.ocean.persist.api.proxy.xunfei;

import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;


public class XunfeiAdPullParams   extends AbstractInvokeParameter{

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApi_ver() {
		return api_ver;
	}

	public void setApi_ver(String api_ver) {
		this.api_ver = api_ver;
	}

	public int getSettle_type() {
		return settle_type;
	}

	public void setSettle_type(int settle_type) {
		this.settle_type = settle_type;
	}

	public List<XunfeiImp> getImps() {
		return imps;
	}

	public void setImps(List<XunfeiImp> imps) {
		this.imps = imps;
	}

	public XunfeiDevice getDevice() {
		return device;
	}

	public void setDevice(XunfeiDevice device) {
		this.device = device;
	}

	public XunfeiApp getApp() {
		return app;
	}

	public void setApp(XunfeiApp app) {
		this.app = app;
	}

	public List<String> getCur() {
		return cur;
	}

	public void setCur(List<String> cur) {
		this.cur = cur;
	}


	private static final long serialVersionUID = 1L;

	private String id;
	private String api_ver;
	private int settle_type;
	private List<XunfeiImp> imps;
	private XunfeiDevice device;
	private XunfeiApp app;
	private List<String> cur;

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

}
