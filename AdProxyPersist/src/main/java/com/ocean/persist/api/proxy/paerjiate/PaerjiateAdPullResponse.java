package com.ocean.persist.api.proxy.paerjiate;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class PaerjiateAdPullResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3516909274877206148L;
	private int state;/*	状态码
	200:Normal.
	404:Channels are limited.
	406:No data.*/
	private String msg;//	状态信息
	private List<PaerjiateAd> ads;//	广告列表
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<PaerjiateAd> getAds() {
		return ads;
	}
	public void setAds(List<PaerjiateAd> ads) {
		this.ads = ads;
	}

}
