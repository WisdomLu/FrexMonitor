package com.ocean.persist.api.proxy.zhuoyiV2;

import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class ZhuoyiV2AdPullReq   extends AbstractInvokeParameter{
	private ZhuoyiV2Media		media;// object	请求中的媒体信息
	private ZhuoyiV2Device		device ;//object	请求中的设备信息
	private ZhuoyiV2Network		network;// object	设备的网络环境
	private ZhuoyiV2Client		client;// object	广告客户端类型
	private ZhuoyiV2Geo		geo ;//object;//	设备地理位置信息
	private List<ZhuoyiV2Adslot> adslots;//	required	array of adslot object	请求的广告位数组
	public ZhuoyiV2Media getMedia() {
		return media;
	}
	public ZhuoyiV2Device getDevice() {
		return device;
	}
	public ZhuoyiV2Network getNetwork() {
		return network;
	}
	public ZhuoyiV2Client getClient() {
		return client;
	}
	public ZhuoyiV2Geo getGeo() {
		return geo;
	}
	public List<ZhuoyiV2Adslot> getAdslots() {
		return adslots;
	}
	public void setMedia(ZhuoyiV2Media media) {
		this.media = media;
	}
	public void setDevice(ZhuoyiV2Device device) {
		this.device = device;
	}
	public void setNetwork(ZhuoyiV2Network network) {
		this.network = network;
	}
	public void setClient(ZhuoyiV2Client client) {
		this.client = client;
	}
	public void setGeo(ZhuoyiV2Geo geo) {
		this.geo = geo;
	}
	public void setAdslots(List<ZhuoyiV2Adslot> adslots) {
		this.adslots = adslots;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
