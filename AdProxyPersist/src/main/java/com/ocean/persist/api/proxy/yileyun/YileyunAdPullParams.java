package com.ocean.persist.api.proxy.yileyun;

import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunAdPullParams   extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5798274484016615225L;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String request_id;/*
	string
	Required
	自定义的请求 id，开发者自行生成，长 度 为 32位，需保证其唯一性。*/
	private String api_version;/*
	string
	Required
	此 API 的版本*/
	private String uid;/*
	string
	Required
	用户 ID。用户在媒体上注册的 ID；如果没 有，传空即可。*/
	private YileyunUser user;/*
	User
	Optional
	用户基本信息, 详细字段见：User 对象*/
	private String source_type;/*
	string
	Required
	流量类型：app*/
	private YileyunApp app;/*
	App
	Required
	当 source_type 为 app 时，必须提供。*/
	private YileyunDevice device;/*
	device
	Required
	移 动 设 备 的 信 息 。 需 提 供 字 段 详 见 ： Device 对象
	*/
	private String ua;/*
	string
	Required
	User-Agent*/
	private String ip;/*
	string
	Required
	设备的 ip，用于定位，地域定向*/
	private List<YileyunAdSlot> adslots;/*
	AdSlot
	Required
	广告位的信息 是数组形式 目前只支持一个*/
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public YileyunUser getUser() {
		return user;
	}
	public void setUser(YileyunUser user) {
		this.user = user;
	}
	public String getSource_type() {
		return source_type;
	}
	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}
	public YileyunApp getApp() {
		return app;
	}
	public void setApp(YileyunApp app) {
		this.app = app;
	}
	public YileyunDevice getDevice() {
		return device;
	}
	public void setDevice(YileyunDevice device) {
		this.device = device;
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
	public List<YileyunAdSlot> getAdslots() {
		return adslots;
	}
	public void setAdslots(List<YileyunAdSlot> adslots) {
		this.adslots = adslots;
	}
}
