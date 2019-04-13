package com.ocean.persist.api.proxy.yingna;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月18日 
      @version 1.0 
 */
public class YingnaFlowInfo {
	private String adText;
	private String adLogo;
	private List<YingnaAd> ads;
	public String getAdText() {
		return adText;
	}
	public void setAdText(String adText) {
		this.adText = adText;
	}
	public String getAdLogo() {
		return adLogo;
	}
	public void setAdLogo(String adLogo) {
		this.adLogo = adLogo;
	}
	public List<YingnaAd> getAds() {
		return ads;
	}
	public void setAds(List<YingnaAd> ads) {
		this.ads = ads;
	}
	
}
