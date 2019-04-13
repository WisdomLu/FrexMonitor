package com.ocean.persist.api.proxy.yijifen;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class YijifenAdData  implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	private List<YijifenAdContent> adList;// 广告列表
	private Long adSlotId;// 广告位id
	private Long appId;// 应用id
	private String timestamp;// 13位时间戳
	private String reqid;// 请求id
	private List<YijifenAdContent> defaultAd;//	默认广告列表

	public List<YijifenAdContent> getAdList() {
		return adList;
	}

	public void setAdList(List<YijifenAdContent> adList) {
		this.adList = adList;
	}

	public Long getAdSlotId() {
		return adSlotId;
	}

	public void setAdSlotId(Long adSlotId) {
		this.adSlotId = adSlotId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

//	public List<YijifenAdContent> getDefaultAd() {
//		return defaultAd;
//	}
//
//	public void setDefaultAd(List<YijifenAdContent> defaultAd) {
//		this.defaultAd = defaultAd;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<YijifenAdContent> getDefaultAd() {
		return defaultAd;
	}

	public void setDefaultAd(List<YijifenAdContent> defaultAd) {
		this.defaultAd = defaultAd;
	}
}
