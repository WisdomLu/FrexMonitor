package com.ocean.persist.api.proxy.xd;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月10日 
      @version 1.0 
 */
public class XDAdPullParams    extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5707365370551539322L;
	private String channelId;
	private XDSlot slot;
	private XDApp app;
	private XDDevice device;
	private XDNetwork network;
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public XDSlot getSlot() {
		return slot;
	}
	public void setSlot(XDSlot slot) {
		this.slot = slot;
	}
	public XDApp getApp() {
		return app;
	}
	public void setApp(XDApp app) {
		this.app = app;
	}
	public XDDevice getDevice() {
		return device;
	}
	public void setDevice(XDDevice device) {
		this.device = device;
	}
	public XDNetwork getNetwork() {
		return network;
	}
	public void setNetwork(XDNetwork network) {
		this.network = network;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

}
