package com.ocean.persist.api.proxy.jieku;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class JiekuAdResponse  extends AbstractBaseEntity  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private Boolean Success;// required bool 检索是否成功
	private List<JiekuAdContent> Ads;// required array of ad object ⼲⼴广告应答
	private String Search_id;// required string 检索ID
	
	public Boolean getSuccess() {
		return Success;
	}

	public void setSuccess(Boolean success) {
		Success = success;
	}

	public List<JiekuAdContent> getAds() {
		return Ads;
	}

	public void setAds(List<JiekuAdContent> ads) {
		Ads = ads;
	}

	public String getSearch_id() {
		return Search_id;
	}

	public void setSearch_id(String search_id) {
		Search_id = search_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
