package com.ocean.persist.api.proxy.zk;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/**
 * 
 * @Description: 广告请求
 * @author yunhui.zhang
 * @date 2016年8月3日 上午10:18:50
 */
public class ZKAdPullParams    extends AdPullParams{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1501899002287348861L;

	/** 请求ID */
	private String bid;
	private String  api_version;

	/** 浏览器信息 */
	private String ua;

	/** 应用信息 */
	private ZKAppInfo app;

	/** 设备信息 */
	private ZKDeviceInfo device;

	/** 网络信息 */
	private ZKNetworkInfo network;

	/** GPS信息 */
	private ZKGpsInfo gps;

	/** 用户信息 */
	private ZKUserProfile user;

	/** 广告位信息 */
	private List<ZKAdSpace> adspaces;

	/** 额外的拓展信息 */
	private String ext;

	public ZKAppInfo getApp() {
		return app;
	}

	public void setApp(ZKAppInfo app) {
		this.app = app;
	}

	public ZKDeviceInfo getDevice() {
		return device;
	}

	public void setDevice(ZKDeviceInfo device) {
		this.device = device;
	}

	public ZKGpsInfo getGps() {
		return gps;
	}

	public void setGps(ZKGpsInfo gps) {
		this.gps = gps;
	}

	public ZKUserProfile getUser() {
		return user;
	}

	public void setUser(ZKUserProfile user) {
		this.user = user;
	}

	public List<ZKAdSpace> getAdspaces() {
		return adspaces;
	}

	public void setAdspaces(List<ZKAdSpace> adspaces) {
		this.adspaces = adspaces;
	}

	public ZKNetworkInfo getNetwork() {
		return network;
	}

	public void setNetwork(ZKNetworkInfo network) {
		this.network = network;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getApi_version() {
		return api_version;
	}

	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}


}
