package com.ocean.persist.api.proxy.dianguan;

public class DianguanMedia {
	private int type;//媒体类别 (1.app 2.h5) app
	private DianguanApp app;//当前 app 的信息 api必 须要传 site
	private DianguanBrowser browser;
	/*optional
	site object
	当前站点信息 browser
	optional
	browser object
	浏*/
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public DianguanApp getApp() {
		return app;
	}
	public void setApp(DianguanApp app) {
		this.app = app;
	}
	public DianguanBrowser getBrowser() {
		return browser;
	}
	public void setBrowser(DianguanBrowser browser) {
		this.browser = browser;
	}
}
