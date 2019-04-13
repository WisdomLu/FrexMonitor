package com.ocean.persist.api.proxy.paerjiate_online;

import java.util.List;

public class PaerjiateOLAd {
	private String adslotId;//":"multi_01",
	private String htmlSnippet;
	private String adKeyv;//":"11806",
	private String expireTime;//":0,
    private List<PaerjiateOLMate> materialMetas;
    private List<PaerjiateOLTracking> adTracking;
	public String getAdslotId() {
		return adslotId;
	}
	public void setAdslotId(String adslotId) {
		this.adslotId = adslotId;
	}
	public String getAdKeyv() {
		return adKeyv;
	}
	public void setAdKeyv(String adKeyv) {
		this.adKeyv = adKeyv;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public List<PaerjiateOLTracking> getAdTracking() {
		return adTracking;
	}
	public void setAdTracking(List<PaerjiateOLTracking> adTracking) {
		this.adTracking = adTracking;
	}
	public String getHtmlSnippet() {
		return htmlSnippet;
	}
	public void setHtmlSnippet(String htmlSnippet) {
		this.htmlSnippet = htmlSnippet;
	}
	public List<PaerjiateOLMate> getMaterialMetas() {
		return materialMetas;
	}
	public void setMaterialMetas(List<PaerjiateOLMate> materialMetas) {
		this.materialMetas = materialMetas;
	}
}
