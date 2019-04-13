package com.ocean.persist.api.proxy.yijifen;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class YijifenImp  {

	private static final long serialVersionUID = 1L;
	private Long adSlotId;// 必填 1234567	广告位ID
	private Long eventime;// 必填 1450073898892	13位时间戳,请求时间
	private Integer viewScreen;// 选填 0.未知1:第一页2:第二页默认为 0，其他页以此类推
	private String keys;// 选填	军事	广告位关键词
	private Integer adType;// 必填 广告位类型。1:BANNER 2:插屏 3:开屏 4.信息流
	private String ext;// 扩展字段，用来扩展信息
	public Long getAdSlotId() {
		return adSlotId;
	}

	public void setAdSlotId(Long adSlotId) {
		this.adSlotId = adSlotId;
	}

	public Long getEventime() {
		return eventime;
	}

	public void setEventime(Long eventime) {
		this.eventime = eventime;
	}

	public Integer getViewScreen() {
		return viewScreen;
	}

	public void setViewScreen(Integer viewScreen) {
		this.viewScreen = viewScreen;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
