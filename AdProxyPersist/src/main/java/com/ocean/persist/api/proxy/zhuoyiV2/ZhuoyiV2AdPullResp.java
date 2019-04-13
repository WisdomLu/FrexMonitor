package com.ocean.persist.api.proxy.zhuoyiV2;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZhuoyiV2AdPullResp    extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6596981526706252634L;
	private boolean success	;//required	bool	检索是否成功
	private List<ZhuoyiV2Ad> ads;//	required	array of ad object	广告应答
	private String search_id	;//required	string	检索 ID
	private int ad_source	;//required	int	广告源 id（0：ADroi）
	public boolean isSuccess() {
		return success;
	}
	public List<ZhuoyiV2Ad> getAds() {
		return ads;
	}
	public String getSearch_id() {
		return search_id;
	}
	public int getAd_source() {
		return ad_source;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public void setAds(List<ZhuoyiV2Ad> ads) {
		this.ads = ads;
	}
	public void setSearch_id(String search_id) {
		this.search_id = search_id;
	}
	public void setAd_source(int ad_source) {
		this.ad_source = ad_source;
	}
}
