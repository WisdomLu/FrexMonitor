package com.ocean.persist.api.proxy.wanka_v1;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
public class WankaResponse  extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4939095482767927332L;
	private String request_id;/*
	string
	是
	DAP 平台生成标识请求 ID*/
	private int error_code;/*
	int
	否
	请求响应出错时的错误
	码，用于问题排查。*/
	private List<WankaAd> adms;/*
	Ad
	否
	应答广告清单，在无错误码 的应答中，会若干个广告描 述信息，需要逐个解析， 具体最多返回广告个数与 请求使用的广告位设置有 关。*/
	private long expiration;/*
	long
	否
	广告清单过期时间戳，单位 秒（保留字段）*/
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
	public List<WankaAd> getAdms() {
		return adms;
	}
	public void setAdms(List<WankaAd> adms) {
		this.adms = adms;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

}
