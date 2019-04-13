package com.ocean.persist.api.proxy.xuanyin;

import java.util.List;

public class XuanyinExtendTracking {
	private List<String> deeplink_evoke ;//否 string array deeplink唤起成功跟踪地址 （ad.deeplink不为空时可能有值） 
	private List<String> download_start ;//否 string array 下载开始跟踪地址（click_action=2时可能有值） 
	private List<String> download_complete;// 否 string array 下载结束跟踪地址（click_action=2时可能有值） 
	private List<String> installation_start;// 否 string array 安装开始跟踪地址（click_action=2时可能有值） 
	private List<String> installation_complete;// 否 string array 安装结束跟踪地址（click_action=2时可能有值） 
	public List<String> getDeeplink_evoke() {
		return deeplink_evoke;
	}
	public List<String> getDownload_start() {
		return download_start;
	}
	public List<String> getDownload_complete() {
		return download_complete;
	}
	public List<String> getInstallation_start() {
		return installation_start;
	}
	public List<String> getInstallation_complete() {
		return installation_complete;
	}
	public void setDeeplink_evoke(List<String> deeplink_evoke) {
		this.deeplink_evoke = deeplink_evoke;
	}
	public void setDownload_start(List<String> download_start) {
		this.download_start = download_start;
	}
	public void setDownload_complete(List<String> download_complete) {
		this.download_complete = download_complete;
	}
	public void setInstallation_start(List<String> installation_start) {
		this.installation_start = installation_start;
	}
	public void setInstallation_complete(List<String> installation_complete) {
		this.installation_complete = installation_complete;
	}

}
