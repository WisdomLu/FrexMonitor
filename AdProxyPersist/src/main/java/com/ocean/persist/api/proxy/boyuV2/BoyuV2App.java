package com.ocean.persist.api.proxy.boyuV2;

public class BoyuV2App {
	private String pkgName;//	string	N		app应用包名信息					
	private String appName;//	string	N		app应用名					
	private String category;//	string	N		app类目名称	

	
	//返回
	private String icon;//	string													表示下载类广告应⽤的图标图片地址	
//	private String pkgName;//		string		com.youai.fytxcr.sy37	Apk广告下载包包名	
	private long size;//		long	61479564										包大小			
	private String md5;//		string		d446c5c1d3ec58e191276c2469c5287e	apk包MD5值			
	private String version;//		string	1.0,1										版本名和版本号使用，拼接而成，例如	
	//1.4.0.801,60			
	private String dsUrl;//		string		http://test.rd.e.sogou/ds?url=&posid=2...	下载开始回调地址，非下载类广告该字段为空			
	private String dfUrl;//		string		http://test.rd.e.sogou/df?url=&posid=2...	下载完成回调地址，非下载类广告该字段为空	
	private String sfUrl;//		string		http://test.rd.e.sogou/sf?url=&posid=2		安装完成回调地址，非下载类广告该字段为空		
	private String deepLink;//		string													app唤醒地址,如果媒体端支持deeplink，且用户	
		//安装有对应的app，则点击时使用此字段调起	
			
	public String getPkgName() {
		return pkgName;
	}

	public String getAppName() {
		return appName;
	}

	public String getCategory() {
		return category;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIcon() {
		return icon;
	}

	public long getSize() {
		return size;
	}

	public String getMd5() {
		return md5;
	}

	public String getVersion() {
		return version;
	}

	public String getDsUrl() {
		return dsUrl;
	}

	public String getDfUrl() {
		return dfUrl;
	}

	public String getSfUrl() {
		return sfUrl;
	}

	public String getDeepLink() {
		return deepLink;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}

	public void setDfUrl(String dfUrl) {
		this.dfUrl = dfUrl;
	}

	public void setSfUrl(String sfUrl) {
		this.sfUrl = sfUrl;
	}

	public void setDeepLink(String deepLink) {
		this.deepLink = deepLink;
	}
}
