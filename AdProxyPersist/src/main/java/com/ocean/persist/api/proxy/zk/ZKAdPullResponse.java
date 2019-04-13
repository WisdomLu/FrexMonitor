package com.ocean.persist.api.proxy.zk;

import java.util.List;
import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAdPullResponse    extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8215576085567784774L;

	/** 请求标识 */
	private String bid;

	/** 错误信息 */
	private String error_msg;

	/** 广告信息 */
	private List<ZKAd> ads;
	
	private String error_code ;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public List<ZKAd> getAds() {
		return ads;
	}

	public void setAds(List<ZKAd> ads) {
		this.ads = ads;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

}
