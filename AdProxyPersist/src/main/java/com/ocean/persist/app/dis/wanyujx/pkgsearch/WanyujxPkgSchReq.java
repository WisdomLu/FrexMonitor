package com.ocean.persist.app.dis.wanyujx.pkgsearch;

import com.ocean.persist.app.dis.AdDisParams;

public class WanyujxPkgSchReq  implements AdDisParams{

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String requestId ;//是请求ID 需保证唯一
	private WanyujxPkgSchApp app ;//是应用相关信息
	private WanyujxPkgSchSlot slot;// 是广告位相关信息
	private WanyujxPkgSchDev device;// 是设备相关信息
	private WanyujxPkgSchNet network;// 是网络相关信息
	private String down_pkgname ;//是下载的包名
	public String getRequestId() {
		return requestId;
	}
	public WanyujxPkgSchApp getApp() {
		return app;
	}
	public WanyujxPkgSchSlot getSlot() {
		return slot;
	}
	public WanyujxPkgSchDev getDevice() {
		return device;
	}
	public WanyujxPkgSchNet getNetwork() {
		return network;
	}
	public String getDown_pkgname() {
		return down_pkgname;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public void setApp(WanyujxPkgSchApp app) {
		this.app = app;
	}
	public void setSlot(WanyujxPkgSchSlot slot) {
		this.slot = slot;
	}
	public void setDevice(WanyujxPkgSchDev device) {
		this.device = device;
	}
	public void setNetwork(WanyujxPkgSchNet network) {
		this.network = network;
	}
	public void setDown_pkgname(String down_pkgname) {
		this.down_pkgname = down_pkgname;
	}
}
