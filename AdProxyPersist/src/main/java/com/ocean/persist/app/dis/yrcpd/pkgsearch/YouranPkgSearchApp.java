package com.ocean.persist.app.dis.yrcpd.pkgsearch;

import com.ocean.persist.app.dis.yrcpd.YouranAppInfo;

public class YouranPkgSearchApp extends YouranAppInfo{
	private String channel	;//渠道号	　上报时将该参数带上
	//packageName	应用包名	　
	private String downloadUrl	;//下载地址	　
	private String price	;//价格	　
	//private String title;//	应用的名称	　
	private String tagline	;//应用的副标题	　
	private String icons	;//应用的图标	　
	private String installedCount;//	安装量	　
	private String installedCountStr;//	安装量的文字表示	　
	private String downloadCount	;//下载量	　
	private String downloadCountStr;//;//	下载量的文字表示	　
	private String categories	;//应用所属的分类	　
	private String tags;//	应用的标签	　
	private String screenshots	;//应用的截图	　
	private String description	;//应用的描述信息	　
	private String changelog	;//应用版本更新日志	　
	private String developer	;//开发者信息	　
	private String likesRate	;//好评率	　
	private String bytes	;//文件大小	　
	//versionCode	版本号	　
	//versionName	版本名称	　
	private String signatrue	;//该APK包的签名	　
	//md5	APK的MD5校验值	　
	private String pubKeySignature	;//RSA签名中公钥的md5值	　
	private String minSdkVersion	;//支持的最小版本的android api level	　
	private String adsType	;//有无广告	　
	private String paidType	;//有无付费	　
	private String permissions	;//该APK需要的权限	　
	private String securityStatus	;//有无病毒	　
	private String official	;//是否是官方应用	　
	private String language	;//语言	　
	private String publishDate	;//发布日志	　
	private String creation;//	创建时间，即最新的APK的发布时间	　
	private String sequence	;//序列号，请求返回的唯一标识	上报时将该参数带上
	private String appId	;//应用ID	　
	private String apkId	;//应用安装包ID	　
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getIcons() {
		return icons;
	}
	public void setIcons(String icons) {
		this.icons = icons;
	}
	public String getInstalledCount() {
		return installedCount;
	}
	public void setInstalledCount(String installedCount) {
		this.installedCount = installedCount;
	}
	public String getInstalledCountStr() {
		return installedCountStr;
	}
	public void setInstalledCountStr(String installedCountStr) {
		this.installedCountStr = installedCountStr;
	}
	public String getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getDownloadCountStr() {
		return downloadCountStr;
	}
	public void setDownloadCountStr(String downloadCountStr) {
		this.downloadCountStr = downloadCountStr;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getScreenshots() {
		return screenshots;
	}
	public void setScreenshots(String screenshots) {
		this.screenshots = screenshots;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChangelog() {
		return changelog;
	}
	public void setChangelog(String changelog) {
		this.changelog = changelog;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getLikesRate() {
		return likesRate;
	}
	public void setLikesRate(String likesRate) {
		this.likesRate = likesRate;
	}
	public String getBytes() {
		return bytes;
	}
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	public String getSignatrue() {
		return signatrue;
	}
	public void setSignatrue(String signatrue) {
		this.signatrue = signatrue;
	}
	public String getPubKeySignature() {
		return pubKeySignature;
	}
	public void setPubKeySignature(String pubKeySignature) {
		this.pubKeySignature = pubKeySignature;
	}
	public String getMinSdkVersion() {
		return minSdkVersion;
	}
	public void setMinSdkVersion(String minSdkVersion) {
		this.minSdkVersion = minSdkVersion;
	}
	public String getAdsType() {
		return adsType;
	}
	public void setAdsType(String adsType) {
		this.adsType = adsType;
	}
	public String getPaidType() {
		return paidType;
	}
	public void setPaidType(String paidType) {
		this.paidType = paidType;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getSecurityStatus() {
		return securityStatus;
	}
	public void setSecurityStatus(String securityStatus) {
		this.securityStatus = securityStatus;
	}
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getCreation() {
		return creation;
	}
	public void setCreation(String creation) {
		this.creation = creation;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getApkId() {
		return apkId;
	}
	public void setApkId(String apkId) {
		this.apkId = apkId;
	}
}
