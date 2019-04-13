package com.ocean.persist.api.proxy.souying;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class SouyingAdResponse  extends AbstractBaseEntity  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private Boolean Success;// 返回状态
	private List<SouyingAdContent> Ads;//	广告内容
	public Boolean getSuccess() {
		return Success;
	}
	public void setSuccess(Boolean success) {
		Success = success;
	}
	public List<SouyingAdContent> getAds() {
		return Ads;
	}
	public void setAds(List<SouyingAdContent> ads) {
		Ads = ads;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
