package com.ocean.persist.api.proxy.oneway;

import java.util.List;

public class OnewayTracking {
	private List<String> show ;//: 展示
	private List<String> start;// : 视频开始播放
	private List<String> firstQuartile ;//: 视频播放1/4
	private List<String> midpoint;// : 视频播放1/2
	private List<String> thirdQuartile ;//: 视频播放3/4
	private List<String> end ;//: 视频播放完成
	private List<String> skip ;//: 跳过 (可跳过广告才需上报)
	private List<String> close;// : 关闭
	private List<String> click ;//: 点击
	private List<String> apkDownloadStart;// : 下载开始
	private List<String> apkDownloadFinish;// : 下载完成
	private List<String> apkDownloadError ;//: 下载错误
	private List<String> packageAdded ;//: 安装完成，为防止用户在下载过程中或者安装过程中退出应用，造成最后实际安装了但无上报的问题，建议开发者在下次启动应用时，扫描已下载但未上报安装的广告 APP 是否已安装，如果已安装，则补报一次。
	public List<String> getShow() {
		return show;
	}
	public List<String> getStart() {
		return start;
	}
	public List<String> getFirstQuartile() {
		return firstQuartile;
	}
	public List<String> getMidpoint() {
		return midpoint;
	}
	public List<String> getThirdQuartile() {
		return thirdQuartile;
	}
	public List<String> getEnd() {
		return end;
	}
	public List<String> getSkip() {
		return skip;
	}
	public List<String> getClose() {
		return close;
	}
	public List<String> getClick() {
		return click;
	}
	public List<String> getApkDownloadStart() {
		return apkDownloadStart;
	}
	public List<String> getApkDownloadFinish() {
		return apkDownloadFinish;
	}
	public List<String> getApkDownloadError() {
		return apkDownloadError;
	}
	public List<String> getPackageAdded() {
		return packageAdded;
	}
	public void setShow(List<String> show) {
		this.show = show;
	}
	public void setStart(List<String> start) {
		this.start = start;
	}
	public void setFirstQuartile(List<String> firstQuartile) {
		this.firstQuartile = firstQuartile;
	}
	public void setMidpoint(List<String> midpoint) {
		this.midpoint = midpoint;
	}
	public void setThirdQuartile(List<String> thirdQuartile) {
		this.thirdQuartile = thirdQuartile;
	}
	public void setEnd(List<String> end) {
		this.end = end;
	}
	public void setSkip(List<String> skip) {
		this.skip = skip;
	}
	public void setClose(List<String> close) {
		this.close = close;
	}
	public void setClick(List<String> click) {
		this.click = click;
	}
	public void setApkDownloadStart(List<String> apkDownloadStart) {
		this.apkDownloadStart = apkDownloadStart;
	}
	public void setApkDownloadFinish(List<String> apkDownloadFinish) {
		this.apkDownloadFinish = apkDownloadFinish;
	}
	public void setApkDownloadError(List<String> apkDownloadError) {
		this.apkDownloadError = apkDownloadError;
	}
	public void setPackageAdded(List<String> packageAdded) {
		this.packageAdded = packageAdded;
	}
}
