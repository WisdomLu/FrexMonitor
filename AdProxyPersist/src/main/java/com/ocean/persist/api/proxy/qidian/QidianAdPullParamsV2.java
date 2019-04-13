package com.ocean.persist.api.proxy.qidian;

import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class QidianAdPullParamsV2 extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -736702649251792642L;
	private String requestId;/* St ring Y 见上节
	请求id，由我方提供前缀，流量方提供的id
	格式为前缀+时间戳*/
	private String auth;/* St ring Y 鉴权字符串，见上节说明*/
	private QidianDevice device;
	private List<QidianImp> imps;
	private QidianApp app;
	private String keyword ;/*St ring N 京东
	private String channel;/* St ring N 渠道标识*/
	private String version ;
	public String getRequestId() {
		return requestId;
	}
	public String getAuth() {
		return auth;
	}
	public QidianDevice getDevice() {
		return device;
	}
	public List<QidianImp> getImps() {
		return imps;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public void setDevice(QidianDevice device) {
		this.device = device;
	}
	public void setImps(List<QidianImp> imps) {
		this.imps = imps;
	}
	public QidianApp getApp() {
		return app;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getVersion() {
		return version;
	}
	public void setApp(QidianApp app) {
		this.app = app;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
