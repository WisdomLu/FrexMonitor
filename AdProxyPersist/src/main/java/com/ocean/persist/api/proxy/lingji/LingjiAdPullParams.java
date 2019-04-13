package com.ocean.persist.api.proxy.lingji;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class LingjiAdPullParams   extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;
	
	private String version;// Y	固定指定1.0
	private LingjiNativead nativead;// Y	信息流Asset信息
	private String pid;// Y	由XTrader平台提供,XTrader平台唯一广告位ID,对应媒体一个确定的资源位置.
	private Integer action_type;// Y	媒体资源位置支持的交互类型：1.支持网页打开类+下载类广告 2.只支持打开类广告 3.只支持下载类广告
	private LingjiDevice device;// Y	设备信息
	private LingjiApp app;// Y	应用信息
	private String reqid;// 媒体系统对一次信息流广告请求定义的唯一ID,XTrader将在Response中返回该唯一ID
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public LingjiNativead getNativead() {
		return nativead;
	}

	public void setNativead(LingjiNativead nativead) {
		this.nativead = nativead;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getAction_type() {
		return action_type;
	}

	public void setAction_type(Integer action_type) {
		this.action_type = action_type;
	}

	public LingjiDevice getDevice() {
		return device;
	}

	public void setDevice(LingjiDevice device) {
		this.device = device;
	}

	public LingjiApp getApp() {
		return app;
	}

	public void setApp(LingjiApp app) {
		this.app = app;
	}

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
