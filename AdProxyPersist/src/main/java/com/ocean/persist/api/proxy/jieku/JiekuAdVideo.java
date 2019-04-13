package com.ocean.persist.api.proxy.jieku;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuAdVideo {

	private static final long serialVersionUID = 1L;
	
	private Integer Time;// required int ⼲⼴广告播放到Time时间发送，单位秒
	private List<String> Impression_url;// required array of string 发送的监控地址(可能有多条)
	
	public Integer getTime() {
		return Time;
	}

	public void setTime(Integer time) {
		Time = time;
	}

	public List<String> getImpression_url() {
		return Impression_url;
	}

	public void setImpression_url(List<String> impression_url) {
		Impression_url = impression_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
