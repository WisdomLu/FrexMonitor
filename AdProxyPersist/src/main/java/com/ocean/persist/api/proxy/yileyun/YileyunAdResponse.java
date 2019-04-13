package com.ocean.persist.api.proxy.yileyun;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunAdResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5222047435800943407L;
	private String request_id;/*
	string
	Required*/
	private List<YileyunAd> ads;/*
	Ad array
	Optional
	当竞价时必填，竞价广告列表，与 adslots 对应*/
	private int processing_time_ms;/*
	uint32
	Optional
	从收到请求到返回响应所用的时间*/
	private int status_code;/*
	int64
	Optional
	具体说明见【常见问题】*/
	private int expiration_time;/*
	uint32
	Optional
	广告过期时间戳，单位为秒，针对预加载广告*/
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public List<YileyunAd> getAds() {
		return ads;
	}
	public void setAds(List<YileyunAd> ads) {
		this.ads = ads;
	}
	public int getProcessing_time_ms() {
		return processing_time_ms;
	}
	public void setProcessing_time_ms(int processing_time_ms) {
		this.processing_time_ms = processing_time_ms;
	}
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public int getExpiration_time() {
		return expiration_time;
	}
	public void setExpiration_time(int expiration_time) {
		this.expiration_time = expiration_time;
	}
}
