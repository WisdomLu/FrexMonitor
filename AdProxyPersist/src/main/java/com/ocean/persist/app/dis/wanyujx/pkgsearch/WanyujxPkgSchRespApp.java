package com.ocean.persist.app.dis.wanyujx.pkgsearch;

import java.util.List;

public class WanyujxPkgSchRespApp {
	private int id;//": 152910,
	private String title;//": "香浓味美，回味无穷。铁观音，传承中国茶文化精粹！",
	private String summary;//": "香浓味美，回味无穷。铁观音，传承中国茶文化精粹！",
	private String brand;//": "魏鸿茶叶",
	private List<WanyujxPkgSchAsset> assets; 
	private String adMark;//": "",
	private int adStyle;//": 6,
	private int targetType;//": 1,
	private String deeplink;//": "",
	private String appChannel;//": "",
	private String landingPageUrl;//":
	private String actionUrl ;//String 应用下载地址，只对应用下载类广告有效
	private String iconUrl ;//String 应用图标地址，只对应用下载类广告有效
	private String packageName;// String 应用包名，只对应用下载类广告有效
	private int totalDownloadNum ;//int 推广应用已经被多少人下载，只对应用下载

	private List<String> viewMonitorUrls;
	private List<String> clickMonitorUrls;
	private List<String>  skipMonitorUrls ;
	private List<String> startDownloadMonitorUrls;
	private List<String> finishDownloadMonitorUrls ;
	private List<String> startInstallMonitorUrls ;
	private List<String> finishInstallMonitorUrls ;

	private WanyujxPkgSchAdCtrl adControl;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public String getBrand() {
		return brand;
	}

	public List<WanyujxPkgSchAsset> getAssets() {
		return assets;
	}

	public String getAdMark() {
		return adMark;
	}

	public int getAdStyle() {
		return adStyle;
	}

	public int getTargetType() {
		return targetType;
	}

	public String getDeeplink() {
		return deeplink;
	}

	public String getAppChannel() {
		return appChannel;
	}

	public String getLandingPageUrl() {
		return landingPageUrl;
	}

	public List<String> getViewMonitorUrls() {
		return viewMonitorUrls;
	}

	public List<String> getClickMonitorUrls() {
		return clickMonitorUrls;
	}

	public WanyujxPkgSchAdCtrl getAdControl() {
		return adControl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setAssets(List<WanyujxPkgSchAsset> assets) {
		this.assets = assets;
	}

	public void setAdMark(String adMark) {
		this.adMark = adMark;
	}

	public void setAdStyle(int adStyle) {
		this.adStyle = adStyle;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}

	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}

	public void setLandingPageUrl(String landingPageUrl) {
		this.landingPageUrl = landingPageUrl;
	}

	public void setViewMonitorUrls(List<String> viewMonitorUrls) {
		this.viewMonitorUrls = viewMonitorUrls;
	}

	public void setClickMonitorUrls(List<String> clickMonitorUrls) {
		this.clickMonitorUrls = clickMonitorUrls;
	}

	public void setAdControl(WanyujxPkgSchAdCtrl adControl) {
		this.adControl = adControl;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getPackageName() {
		return packageName;
	}

	public int getTotalDownloadNum() {
		return totalDownloadNum;
	}

	public List<String> getSkipMonitorUrls() {
		return skipMonitorUrls;
	}

	public List<String> getStartDownloadMonitorUrls() {
		return startDownloadMonitorUrls;
	}

	public List<String> getFinishDownloadMonitorUrls() {
		return finishDownloadMonitorUrls;
	}

	public List<String> getStartInstallMonitorUrls() {
		return startInstallMonitorUrls;
	}

	public List<String> getFinishInstallMonitorUrls() {
		return finishInstallMonitorUrls;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setTotalDownloadNum(int totalDownloadNum) {
		this.totalDownloadNum = totalDownloadNum;
	}

	public void setSkipMonitorUrls(List<String> skipMonitorUrls) {
		this.skipMonitorUrls = skipMonitorUrls;
	}

	public void setStartDownloadMonitorUrls(List<String> startDownloadMonitorUrls) {
		this.startDownloadMonitorUrls = startDownloadMonitorUrls;
	}

	public void setFinishDownloadMonitorUrls(List<String> finishDownloadMonitorUrls) {
		this.finishDownloadMonitorUrls = finishDownloadMonitorUrls;
	}

	public void setStartInstallMonitorUrls(List<String> startInstallMonitorUrls) {
		this.startInstallMonitorUrls = startInstallMonitorUrls;
	}

	public void setFinishInstallMonitorUrls(List<String> finishInstallMonitorUrls) {
		this.finishInstallMonitorUrls = finishInstallMonitorUrls;
	}
}
