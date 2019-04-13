package com.ocean.persist.api.proxy.dianguan;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class DianguanReq extends AbstractInvokeParameter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6885848499465122371L;
	private DianguanMedia media;//请求中的媒体信息 
	private DianguanDevice device;//请求中的设备信息 
	private DianguanNet network;//设备的网络环境
	private DianguanClient client;//广告客户端类型 
	private DianguanAdslot adslot;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public DianguanMedia getMedia() {
		return media;
	}
	public void setMedia(DianguanMedia media) {
		this.media = media;
	}
	public DianguanDevice getDevice() {
		return device;
	}
	public void setDevice(DianguanDevice device) {
		this.device = device;
	}
	public DianguanNet getNetwork() {
		return network;
	}
	public void setNetwork(DianguanNet network) {
		this.network = network;
	}
	public DianguanClient getClient() {
		return client;
	}
	public void setClient(DianguanClient client) {
		this.client = client;
	}
	public DianguanAdslot getAdslot() {
		return adslot;
	}
	public void setAdslot(DianguanAdslot adslot) {
		this.adslot = adslot;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
}
