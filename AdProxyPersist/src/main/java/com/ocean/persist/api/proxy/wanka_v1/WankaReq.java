package com.ocean.persist.api.proxy.wanka_v1;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class WankaReq {
	private String api_version;/*
	string
	是
	API 版本，按照当前接入所参照的
	API 文档版本赋值，影响所有后续
	逻辑。当前版本 2.0.0。*/
	private WankaApp app;/*
	App
	是
	应用信息*/
	private WankaDevice device;/*
	Device
	是
	设备信息*/
	private WankaAdSlot adslot;/*
	AdSlot
	是
	广告位信息*/
	private WankaNetwork network;/*
	Network
	是
	网络环境信息*/
	private WankaGps gps;/*
	
	否
	定位信息*/
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	public WankaApp getApp() {
		return app;
	}
	public void setApp(WankaApp app) {
		this.app = app;
	}
	public WankaDevice getDevice() {
		return device;
	}
	public void setDevice(WankaDevice device) {
		this.device = device;
	}
	public WankaAdSlot getAdslot() {
		return adslot;
	}
	public void setAdslot(WankaAdSlot adslot) {
		this.adslot = adslot;
	}
	public WankaNetwork getNetwork() {
		return network;
	}
	public void setNetwork(WankaNetwork network) {
		this.network = network;
	}
	public WankaGps getGps() {
		return gps;
	}
	public void setGps(WankaGps gps) {
		this.gps = gps;
	}
}
