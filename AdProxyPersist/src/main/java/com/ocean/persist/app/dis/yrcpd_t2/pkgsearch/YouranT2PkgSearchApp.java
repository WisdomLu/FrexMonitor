package com.ocean.persist.app.dis.yrcpd_t2.pkgsearch;

import java.util.List;

import com.ocean.persist.app.dis.yrcpd.YouranAppInfo;

public class YouranT2PkgSearchApp extends YouranAppInfo{
	private String DownloadUrl	;//下载地址	　
	private String Price	;//价格	　
	private String Icon	;//应用的图标	
	private String Image;
	private String AppName;
	private String PackageName;
	private String PackageMd5;
	private String FileSize	;//文件大小	
	private String Description	;//应用的描述信息	
    private String VersionCode;//": "13",
    private String VersionName;//": "1.2.7"

	private List<String> ReportShowUrl	;//string[]	展示上报地址, 如果多个情况 需要上报多个
	private List<String> ReportClickUrl	;//string[]	点击上报地址, 如果多个情况 需要上报多个
	private List<String> ReportDownloadBeginUrl	;//String[]	开始下载上报地址, 如果多个情况 需要上报多个
	private List<String> ReportDownloadCompletedUrl	;//string[]	下载完成上报地址, 如果多个情况 需要上报多个
	private List<String> ReportInstallBeginUrl	;//string[]	开始安装上报地址, 如果多个情况 需要上报多个
	private List<String> ReportInstallCompletedUrl	;//string[]	安装完成上报地址, 如果多个情况 需要上报多个
	private List<String> ReportActiveCompletedUrl	;//string[]	完成激活上报地址, 如果多个情况 需要上报多个
	public String getDownloadUrl() {
		return DownloadUrl;
	}
	public String getPrice() {
		return Price;
	}
	public String getIcon() {
		return Icon;
	}
	public String getImage() {
		return Image;
	}
	public String getAppName() {
		return AppName;
	}
	public String getPackageName() {
		return PackageName;
	}
	public String getPackageMd5() {
		return PackageMd5;
	}
	public String getFileSize() {
		return FileSize;
	}
	public String getDescription() {
		return Description;
	}
	public String getVersionCode() {
		return VersionCode;
	}
	public String getVersionName() {
		return VersionName;
	}
	public List<String> getReportShowUrl() {
		return ReportShowUrl;
	}
	public List<String> getReportClickUrl() {
		return ReportClickUrl;
	}
	public List<String> getReportDownloadBeginUrl() {
		return ReportDownloadBeginUrl;
	}
	public List<String> getReportDownloadCompletedUrl() {
		return ReportDownloadCompletedUrl;
	}
	public List<String> getReportInstallBeginUrl() {
		return ReportInstallBeginUrl;
	}
	public List<String> getReportInstallCompletedUrl() {
		return ReportInstallCompletedUrl;
	}
	public List<String> getReportActiveCompletedUrl() {
		return ReportActiveCompletedUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		DownloadUrl = downloadUrl;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}
	public void setImage(String image) {
		Image = image;
	}
	public void setAppName(String appName) {
		AppName = appName;
	}
	public void setPackageName(String packageName) {
		PackageName = packageName;
	}
	public void setPackageMd5(String packageMd5) {
		PackageMd5 = packageMd5;
	}
	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void setVersionCode(String versionCode) {
		VersionCode = versionCode;
	}
	public void setVersionName(String versionName) {
		VersionName = versionName;
	}
	public void setReportShowUrl(List<String> reportShowUrl) {
		ReportShowUrl = reportShowUrl;
	}
	public void setReportClickUrl(List<String> reportClickUrl) {
		ReportClickUrl = reportClickUrl;
	}
	public void setReportDownloadBeginUrl(List<String> reportDownloadBeginUrl) {
		ReportDownloadBeginUrl = reportDownloadBeginUrl;
	}
	public void setReportDownloadCompletedUrl(
			List<String> reportDownloadCompletedUrl) {
		ReportDownloadCompletedUrl = reportDownloadCompletedUrl;
	}
	public void setReportInstallBeginUrl(List<String> reportInstallBeginUrl) {
		ReportInstallBeginUrl = reportInstallBeginUrl;
	}
	public void setReportInstallCompletedUrl(List<String> reportInstallCompletedUrl) {
		ReportInstallCompletedUrl = reportInstallCompletedUrl;
	}
	public void setReportActiveCompletedUrl(List<String> reportActiveCompletedUrl) {
		ReportActiveCompletedUrl = reportActiveCompletedUrl;
	}
	
}
