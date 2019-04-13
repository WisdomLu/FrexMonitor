package com.ocean.persist.api.proxy.youken;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
public class YoukenResponse  extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5098276613685225707L;
	/**
	 * 
	 */
	private String request_id;/*
	string
	是
	DAP 平台生成标识请求 ID*/
	private int error_code;/*
	int
	否
	请求响应出错时的错误
	码，用于问题排查。*/
	private List<YoukenAd> adms;/*
	Ad
	否
	应答广告清单，在无错误码 的应答中，会若干个广告描 述信息，需要逐个解析， 具体最多返回广告个数与 请求使用的广告位设置有 关。*/
	private long expiration_time;/*
	long
	否
	广告清单过期时间戳，单位 秒（保留字段）*/
	private int get_ad_in_same_view_interval;
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	public List<YoukenAd> getAdms() {
		return adms;
	}
	public void setAdms(List<YoukenAd> adms) {
		this.adms = adms;
	}
	public long getExpiration_time() {
		return expiration_time;
	}
	public void setExpiration_time(long expiration_time) {
		this.expiration_time = expiration_time;
	}
	public int getGet_ad_in_same_view_interval() {
		return get_ad_in_same_view_interval;
	}
	public void setGet_ad_in_same_view_interval(int get_ad_in_same_view_interval) {
		this.get_ad_in_same_view_interval = get_ad_in_same_view_interval;
	}


}
