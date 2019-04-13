package com.ocean.persist.api.proxy.jieku;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class JiekuAdPullParams   extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;
	private JiekuMedia media;// required media object 请求中的媒体信息
	private JiekuDevice device;// required device object 请求中的设备信息
	private JiekuNetwork network;// required network object 设备的网络环境
	private JiekuClient client;// required client object 广告客户端类型
	private JiekuGeo geo;// optional geo object 设备地理位置信息
	private List<JiekuAdslot> adslots;// required array of adslot object 请求的广告位数组（目前只支持单广告位请求）
	private boolean debug;// optional bool 当前请求是否是debug请求，不填为非debug请求

	public JiekuMedia getMedia() {
		return media;
	}

	public void setMedia(JiekuMedia media) {
		this.media = media;
	}

	public JiekuDevice getDevice() {
		return device;
	}

	public void setDevice(JiekuDevice device) {
		this.device = device;
	}

	public JiekuNetwork getNetwork() {
		return network;
	}

	public void setNetwork(JiekuNetwork network) {
		this.network = network;
	}

	public JiekuClient getClient() {
		return client;
	}

	public void setClient(JiekuClient client) {
		this.client = client;
	}

	public JiekuGeo getGeo() {
		return geo;
	}

	public void setGeo(JiekuGeo geo) {
		this.geo = geo;
	}

	public List<JiekuAdslot> getAdslots() {
		return adslots;
	}

	public void setAdslots(List<JiekuAdslot> adslots) {
		this.adslots = adslots;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
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
