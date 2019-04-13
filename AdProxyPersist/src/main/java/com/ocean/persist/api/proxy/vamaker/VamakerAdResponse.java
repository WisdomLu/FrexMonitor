package com.ocean.persist.api.proxy.vamaker;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class VamakerAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	
	private String adid;// 流量唯一标识，impression id
	private String adtype;// 广告类型； 0:Banner 广告 1:开屏广告  2:插屏广告 3:信息流广告
	private List<String> img;// 广告图片
	private String title;// 广告 title
	private String descprtion;// 广告描述
	private String lp;// 目标地址
	private List<String> pm;// 曝光监测
	private List<String> cm;// 点击监测
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getAdtype() {
		return adtype;
	}
	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}
	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescprtion() {
		return descprtion;
	}
	public void setDescprtion(String descprtion) {
		this.descprtion = descprtion;
	}
	public String getLp() {
		return lp;
	}
	public void setLp(String lp) {
		this.lp = lp;
	}
	public List<String> getPm() {
		return pm;
	}
	public void setPm(List<String> pm) {
		this.pm = pm;
	}
	public List<String> getCm() {
		return cm;
	}
	public void setCm(List<String> cm) {
		this.cm = cm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
