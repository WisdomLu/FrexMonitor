package com.ocean.persist.api.proxy.qidian;

public class QidainAppInfo {
	private String icon;
	private String pkgName;
	private String size;
	private String md5;
	private String version ;
	private String dsUrl ;
	private String dfUrl ;
	private String sfUrl;
	private String deepLink ;
	public String getIcon() {
		return icon;
	}
	public String getPkgName() {
		return pkgName;
	}
	public String getSize() {
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
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public void setSize(String size) {
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
