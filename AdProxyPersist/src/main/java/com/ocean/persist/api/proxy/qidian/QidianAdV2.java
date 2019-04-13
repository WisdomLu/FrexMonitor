package com.ocean.persist.api.proxy.qidian;

import java.util.List;

public class QidianAdV2 {
	private String id;
	private String creativeId ;
	private String title ;
	private String summary ;
	private String link ;
	private List<String> clickTrackUrls ;
	private List<String> impTrackUrls ;
	private String adWidth;
	private String adHeight ;
	private String adType;
	private String downloadAd ;
	private String source;
	private String nativeAdType ;
	private List<QidianImg> imgs;
	private QidainAppInfo appInfo;
	private QidianSplashInfo splashInfo;
	public String getId() {
		return id;
	}
	public String getCreativeId() {
		return creativeId;
	}
	public String getTitle() {
		return title;
	}
	public String getSummary() {
		return summary;
	}
	public String getLink() {
		return link;
	}
	public List<String> getClickTrackUrls() {
		return clickTrackUrls;
	}
	public List<String> getImpTrackUrls() {
		return impTrackUrls;
	}
	public String getAdWidth() {
		return adWidth;
	}
	public String getAdHeight() {
		return adHeight;
	}
	public String getAdType() {
		return adType;
	}
	public String getDownloadAd() {
		return downloadAd;
	}
	public String getSource() {
		return source;
	}
	public String getNativeAdType() {
		return nativeAdType;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCreativeId(String creativeId) {
		this.creativeId = creativeId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setClickTrackUrls(List<String> clickTrackUrls) {
		this.clickTrackUrls = clickTrackUrls;
	}
	public void setImpTrackUrls(List<String> impTrackUrls) {
		this.impTrackUrls = impTrackUrls;
	}
	public void setAdWidth(String adWidth) {
		this.adWidth = adWidth;
	}
	public void setAdHeight(String adHeight) {
		this.adHeight = adHeight;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public void setDownloadAd(String downloadAd) {
		this.downloadAd = downloadAd;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setNativeAdType(String nativeAdType) {
		this.nativeAdType = nativeAdType;
	}
	public QidainAppInfo getAppInfo() {
		return appInfo;
	}
	public QidianSplashInfo getSplashInfo() {
		return splashInfo;
	}
	public void setAppInfo(QidainAppInfo appInfo) {
		this.appInfo = appInfo;
	}
	public void setSplashInfo(QidianSplashInfo splashInfo) {
		this.splashInfo = splashInfo;
	}
	public List<QidianImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<QidianImg> imgs) {
		this.imgs = imgs;
	}
}
