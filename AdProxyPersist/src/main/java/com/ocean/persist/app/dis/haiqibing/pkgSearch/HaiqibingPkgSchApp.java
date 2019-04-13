package com.ocean.persist.app.dis.haiqibing.pkgSearch;

public class HaiqibingPkgSchApp {
	private long appId;// long 是应用唯一标示id
	private long apkId ;//long 是安装包唯一标示id
	private String appName;
	private String apkMd5;
	private int categoryId;
	private String categoryName;
	private long fileSize;
	private String iconUrl;
	private String logoUrl;
	private int minSdkVersion;
	private String pkgName;
	private String shortDesc;
	private int targetSdkVersion;
	private String totalDownloadTimes;
	
	//private String packageName ;//String 是应用包名
	private int versionCode ;//int 是应用所对应的版本号
	private String versionName;
	private String recommendId ;//String 是上报需要的数据，原样返回
	private int source ;//int 是上报需要的数据，原样返回
	private String channelId;// String 是渠道id
	private String apkUrl;// String 是安装包最新下载地址
	private String dataAnalysisId;// String 是上报需要的数据，原样返回
	public long getAppId() {
		return appId;
	}
	public long getApkId() {
		return apkId;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public String getRecommendId() {
		return recommendId;
	}
	public int getSource() {
		return source;
	}
	public String getChannelId() {
		return channelId;
	}
	public String getApkUrl() {
		return apkUrl;
	}
	public String getDataAnalysisId() {
		return dataAnalysisId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public void setApkId(long apkId) {
		this.apkId = apkId;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}
	public void setDataAnalysisId(String dataAnalysisId) {
		this.dataAnalysisId = dataAnalysisId;
	}
	public String getAppName() {
		return appName;
	}
	public String getApkMd5() {
		return apkMd5;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public int getMinSdkVersion() {
		return minSdkVersion;
	}
	public String getPkgName() {
		return pkgName;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public int getTargetSdkVersion() {
		return targetSdkVersion;
	}
	public String getTotalDownloadTimes() {
		return totalDownloadTimes;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public void setApkMd5(String apkMd5) {
		this.apkMd5 = apkMd5;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public void setMinSdkVersion(int minSdkVersion) {
		this.minSdkVersion = minSdkVersion;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public void setTargetSdkVersion(int targetSdkVersion) {
		this.targetSdkVersion = targetSdkVersion;
	}
	public void setTotalDownloadTimes(String totalDownloadTimes) {
		this.totalDownloadTimes = totalDownloadTimes;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
}
