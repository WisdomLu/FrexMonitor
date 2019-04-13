package com.ocean.persist.api.proxy.yuansheng2;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengImp extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5118374462695329853L;
	private String impid;
	private String adsource;
	private String logo ;
	private List<YuanshengImag> image;
	private List<YuanshengWord> word;

	private int action ;
	private String link ;
	private String deeplink;
	private List<String> imptk;
	private List<String> clicktk;
	public String getImpid() {
		return impid;
	}
	public void setImpid(String impid) {
		this.impid = impid;
	}
	public String getAdsource() {
		return adsource;
	}
	public void setAdsource(String adsource) {
		this.adsource = adsource;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<YuanshengImag> getImage() {
		return image;
	}
	public void setImage(List<YuanshengImag> image) {
		this.image = image;
	}
	public List<YuanshengWord> getWord() {
		return word;
	}
	public void setWord(List<YuanshengWord> word) {
		this.word = word;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public List<String> getImptk() {
		return imptk;
	}
	public void setImptk(List<String> imptk) {
		this.imptk = imptk;
	}
	public List<String> getClicktk() {
		return clicktk;
	}
	public void setClicktk(List<String> clicktk) {
		this.clicktk = clicktk;
	}

}
