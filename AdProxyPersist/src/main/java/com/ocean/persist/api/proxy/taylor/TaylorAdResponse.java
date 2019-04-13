package com.ocean.persist.api.proxy.taylor;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class TaylorAdResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 返回成功 
	 */
	public static final int success = 0;
	
	/**
	 * 没有广告
	 */
	public static final int noad = 1009;
	
	private static final long serialVersionUID = 1L;

	private String adcode;
	
	private String msg;
	
	private Integer code;
	
	private Integer count;
	
	private List<TaylorAdDetails> ads;

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<TaylorAdDetails> getAds() {
		return ads;
	}

	public void setAds(List<TaylorAdDetails> ads) {
		this.ads = ads;
	}
}
