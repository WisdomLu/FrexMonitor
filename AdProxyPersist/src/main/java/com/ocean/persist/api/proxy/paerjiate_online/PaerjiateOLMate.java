package com.ocean.persist.api.proxy.paerjiate_online;

import java.util.List;

public class PaerjiateOLMate {
	private int creativeType;//":2,
	private int interactionType;//":1,
    private String landingUrl;//":"http://sx.c.fastapi.net/ j?cpt_cp=__AZCX__,__AZCY__&cpt_fmp=__AZMX__,__AZMY__",
    private int materialSize;
    private List<String> imageSrcs;
    private List<String>  iconSrcs;
    private String title;
    private String desc;
    private String appName;
    private String packageName;
    
    private int totalNum;
    private int index;
	public int getCreativeType() {
		return creativeType;
	}
	public void setCreativeType(int creativeType) {
		this.creativeType = creativeType;
	}
	public int getInteractionType() {
		return interactionType;
	}
	public void setInteractionType(int interactionType) {
		this.interactionType = interactionType;
	}
	public String getLandingUrl() {
		return landingUrl;
	}
	public void setLandingUrl(String landingUrl) {
		this.landingUrl = landingUrl;
	}
	public int getMaterialSize() {
		return materialSize;
	}
	public void setMaterialSize(int materialSize) {
		this.materialSize = materialSize;
	}
	public List<String> getImageSrcs() {
		return imageSrcs;
	}
	public void setImageSrcs(List<String> imageSrcs) {
		this.imageSrcs = imageSrcs;
	}
	public List<String> getIconSrcs() {
		return iconSrcs;
	}
	public void setIconSrcs(List<String> iconSrcs) {
		this.iconSrcs = iconSrcs;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
