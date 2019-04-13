package com.ocean.persist.app.dis.qqDownloader;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月13日 
      @version 1.0 
 */
public  class BaseAppADListItem   implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3356668871731922294L;
	private String appId;//应用的id，由后台分配，唯一标识一个应用
	private String packageName;//应用包名，与appId一一对应
	private String pkgName;//不同接口包名参数不一样
	private String apkId;//apk包对应的ID
	private String appName;//应用名称
	private String iconUrl;//logo的url,默认大小为72
	private String fileSize;//最新下载文件大小
	private int versionCode;//版本号
	private String versionName;//版本名称
	private String apkUrl;//最新版本下载地址
	private String recommendId;//上报时原样带回
	private int source;//上报时原样带回
	private String channelId;//渠道id
	private String dataAnalysisId;//上报时原样带回
	private String shortDesc;
	private long categoryId;//分类ID
	private String categoryName;//分类名称
	private long appDownCount;//app总的下载次数
	private double averageRating;//应用平均评分
	private String apkMd5;//包MD5
	private String signatureMd5;//signatureMd5
	private int minSdkVersion;//支持的最小rom版本
	private int parentCategoryID;//父类id,-1：软件，-2：游戏
	private String interfaceName;
	private int hostVersionCode;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getApkId() {
		return apkId;
	}
	public void setApkId(String apkId) {
		this.apkId = apkId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getApkUrl() {
		return apkUrl;
	}
	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}
	public String getRecommendId() {
		return recommendId;
	}
	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getDataAnalysisId() {
		return dataAnalysisId;
	}
	public void setDataAnalysisId(String dataAnalysisId) {
		this.dataAnalysisId = dataAnalysisId;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getAppDownCount() {
		return appDownCount;
	}
	public void setAppDownCount(long appDownCount) {
		this.appDownCount = appDownCount;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public String getSignatureMd5() {
		return signatureMd5;
	}
	public void setSignatureMd5(String signatureMd5) {
		this.signatureMd5 = signatureMd5;
	}
	public int getMinSdkVersion() {
		return minSdkVersion;
	}
	public void setMinSdkVersion(int minSdkVersion) {
		this.minSdkVersion = minSdkVersion;
	}
	public int getParentCategoryID() {
		return parentCategoryID;
	}
	public void setParentCategoryID(int parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public String getApkMd5() {
		return apkMd5;
	}
	public void setApkMd5(String apkMd5) {
		this.apkMd5 = apkMd5;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public int getHostVersionCode() {
		return hostVersionCode;
	}
	public void setHostVersionCode(int hostVersionCode) {
		this.hostVersionCode = hostVersionCode;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

}
