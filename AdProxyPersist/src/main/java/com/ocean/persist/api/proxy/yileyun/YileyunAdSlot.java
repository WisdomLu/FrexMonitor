package com.ocean.persist.api.proxy.yileyun;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunAdSlot {
	private String id;/*
	string
	Required
	代码位 ID，测试结算的 slotid 详见附录一*/
	private int adtype;/*
	adType
	Required
	广告类型: Banner=1；开屏=3；信息流=5*/
	private int pos;/*
	Position
	Required
	广告展现位置：顶部=1；底部=2；信息流
	内=3；中部=4；全屏=5*/
	private List<YileyunSize> accepted_size;/*
	Size
	Required
	图片素材尺寸，数组形式*/
	private int ad_count;/*
	uint32
	optional
	广告数量请填 1，如果多于 1 条 ，请提 前 与商务沟通。*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAdtype() {
		return adtype;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getAd_count() {
		return ad_count;
	}
	public void setAd_count(int ad_count) {
		this.ad_count = ad_count;
	}
	public List<YileyunSize> getAccepted_size() {
		return accepted_size;
	}
	public void setAccepted_size(List<YileyunSize> accepted_size) {
		this.accepted_size = accepted_size;
	}
}
