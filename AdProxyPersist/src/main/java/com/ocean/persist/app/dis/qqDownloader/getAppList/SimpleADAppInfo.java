package com.ocean.persist.app.dis.qqDownloader.getAppList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月13日 
      @version 1.0 
 */
public class SimpleADAppInfo    implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9190254291485776283L;
	private String pkgName;
	private String appName;
	private String apkUrl;
	private String fileSize;
	private int versionCode;
	private String versionName;
	private String apkId;
	private String apkMd5;
	private String channelId;
	private String recommendId;
	private int source;
	private String appId;
	private String iconUrl;
	private String totalDownloadTimes;
	private String signatureMd5;
	private int minSdkVersion;
	private int parentCategoryID;
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getApkUrl() {
		return apkUrl;
	}
	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getApkId() {
		return apkId;
	}
	public void setApkId(String apkId) {
		this.apkId = apkId;
	}
	public String getApkMd5() {
		return apkMd5;
	}
	public void setApkMd5(String apkMd5) {
		this.apkMd5 = apkMd5;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getTotalDownloadTimes() {
		return totalDownloadTimes;
	}
	public void setTotalDownloadTimes(String totalDownloadTimes) {
		this.totalDownloadTimes = totalDownloadTimes;
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
}
