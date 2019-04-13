package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;

public class JiekuSite {

	private static final long serialVersionUID = 1L;
	
	private String domain;// required string 站点主域
	private String urls;// required string 当前页面的url
	private String title;// optional string 当前页面的标题(视频广告必须给出)
	private String keywords;// optional string 当前页面的metadata 

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
