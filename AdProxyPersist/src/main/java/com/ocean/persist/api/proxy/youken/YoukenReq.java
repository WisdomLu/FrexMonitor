package com.ocean.persist.api.proxy.youken;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月6日 
      @version 1.0 
 */
public class YoukenReq {
	/** API 版本 **/
	private String api_version;
	/** 应用信息 **/
	private YoukenApp app;
	/** 设备信息 **/
	private YoukenDevice device;
	/** 广告位信息 **/
	private YoukenAdslot adslot;
	/** 网络环境信息 **/
	private YoukenNetwork network;
	/** 定位信息 **/
	private YoukenGps gps;
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	public YoukenApp getApp() {
		return app;
	}
	public void setApp(YoukenApp app) {
		this.app = app;
	}
	public YoukenDevice getDevice() {
		return device;
	}
	public void setDevice(YoukenDevice device) {
		this.device = device;
	}
	public YoukenAdslot getAdslot() {
		return adslot;
	}
	public void setAdslot(YoukenAdslot adslot) {
		this.adslot = adslot;
	}
	public YoukenNetwork getNetwork() {
		return network;
	}
	public void setNetwork(YoukenNetwork network) {
		this.network = network;
	}
	public YoukenGps getGps() {
		return gps;
	}
	public void setGps(YoukenGps gps) {
		this.gps = gps;
	}
}
