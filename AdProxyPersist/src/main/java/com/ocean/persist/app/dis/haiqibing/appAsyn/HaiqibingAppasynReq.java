package com.ocean.persist.app.dis.haiqibing.appAsyn;

import com.ocean.persist.app.dis.AdDisParams;

public class HaiqibingAppasynReq   implements AdDisParams{

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String channel;// String 是渠道标示
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
