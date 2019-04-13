package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuMedia {

	private static final long serialVersionUID = 1L;
	
	private Integer type;// required int 媒体类别(1.app 2.web3.wap)
	private JiekuApp app;// optional app object 当前app的信息
	private JiekuSite site;// optional site object 当前站点信息
	private JiekuBrowser browser;// optional browser object 浏览器信息

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public JiekuApp getApp() {
		return app;
	}

	public void setApp(JiekuApp app) {
		this.app = app;
	}

	public JiekuSite getSite() {
		return site;
	}

	public void setSite(JiekuSite site) {
		this.site = site;
	}

	public JiekuBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(JiekuBrowser browser) {
		this.browser = browser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
