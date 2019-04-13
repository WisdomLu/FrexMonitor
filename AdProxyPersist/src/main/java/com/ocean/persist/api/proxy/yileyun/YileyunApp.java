package com.ocean.persist.api.proxy.yileyun;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunApp {
	private String appid;/*
	string
	Required
	app 应用 id，测试阶段的 appid 详见附录*/
	private YileyunGeo geo;/*
	geo
	Optional
	设备地理位置信息。详细字段见：Geo 对象*/
	private boolean is_paid_app;/*
	bool
	Optional
	表示此 app 是否为付费 app*/
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public YileyunGeo getGeo() {
		return geo;
	}
	public void setGeo(YileyunGeo geo) {
		this.geo = geo;
	}
	public boolean isIs_paid_app() {
		return is_paid_app;
	}
	public void setIs_paid_app(boolean is_paid_app) {
		this.is_paid_app = is_paid_app;
	}
}
