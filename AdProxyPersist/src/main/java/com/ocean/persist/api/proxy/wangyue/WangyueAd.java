package com.ocean.persist.api.proxy.wangyue;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月29日 
      @version 1.0 
 */
public class WangyueAd {
	private String adType	;//String	是	广告交互类型 （跳转类、下载 类、品牌类），redirect/download/brand	redirect
	private String pkgname	;//	String	否	下载包名称，用于监控应用安装完成，当 adtype 为“download”时有效。若该字段为空，则媒体不要触发 inst_installsucc _url 字段中的 安装完成上报监控	
	private String inst_downstart_url		;//String	否	针对下载类广告，下载开始上报监播	
	private String inst_downsucc_url	;//	String	否	针对下载类广告，下载成功上报监播	
	private String inst_installstart_url	;//	String	否	针对下载类广告，安装开始上报监播	
	private String inst_installsucc_url	;//	String	否	针对下载类广告，安装成功上报监播	
	private String clickurl	;//	String	是	点击跳转链接	
	private String 	deepLink		;//String	否	点击跳转应用链接	
	private List<String> imgurls		;//Arrays	是	广告物料数组	
	private String adWidth		;//String	否	宽度	
	private String adHeight	;//	String	否	高度	
	private String displayTitle		;//String	是	广告标题	
	private String displayText		;//String	否	广告描述	
	private List<String> showFollowUrls	;//	Array	是	展示监播数组	
	private List<String> clickFollowUrls		;//array	是	点击监播数组	
	public String getAdType() {
		return adType;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public String getInst_downstart_url() {
		return inst_downstart_url;
	}
	public void setInst_downstart_url(String inst_downstart_url) {
		this.inst_downstart_url = inst_downstart_url;
	}
	public String getInst_downsucc_url() {
		return inst_downsucc_url;
	}
	public void setInst_downsucc_url(String inst_downsucc_url) {
		this.inst_downsucc_url = inst_downsucc_url;
	}
	public String getInst_installstart_url() {
		return inst_installstart_url;
	}
	public void setInst_installstart_url(String inst_installstart_url) {
		this.inst_installstart_url = inst_installstart_url;
	}
	public String getInst_installsucc_url() {
		return inst_installsucc_url;
	}
	public void setInst_installsucc_url(String inst_installsucc_url) {
		this.inst_installsucc_url = inst_installsucc_url;
	}
	public String getClickurl() {
		return clickurl;
	}
	public void setClickurl(String clickurl) {
		this.clickurl = clickurl;
	}
	public String getDeepLink() {
		return deepLink;
	}
	public void setDeepLink(String deepLink) {
		this.deepLink = deepLink;
	}
	public List<String> getImgurls() {
		return imgurls;
	}
	public void setImgurls(List<String> imgurls) {
		this.imgurls = imgurls;
	}
	public String getAdWidth() {
		return adWidth;
	}
	public void setAdWidth(String adWidth) {
		this.adWidth = adWidth;
	}
	public String getAdHeight() {
		return adHeight;
	}
	public void setAdHeight(String adHeight) {
		this.adHeight = adHeight;
	}
	public String getDisplayTitle() {
		return displayTitle;
	}
	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public List<String> getShowFollowUrls() {
		return showFollowUrls;
	}
	public void setShowFollowUrls(List<String> showFollowUrls) {
		this.showFollowUrls = showFollowUrls;
	}
	public List<String> getClickFollowUrls() {
		return clickFollowUrls;
	}
	public void setClickFollowUrls(List<String> clickFollowUrls) {
		this.clickFollowUrls = clickFollowUrls;
	}

}
