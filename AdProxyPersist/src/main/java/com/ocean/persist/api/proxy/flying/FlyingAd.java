package com.ocean.persist.api.proxy.flying;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月2日 
      @version 1.0 
 */
public class FlyingAd  extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3630446142320040244L;
	private String strAdId;//：广告id
	private List<String> arrViewTrackUrl;//：曝光回调地址列表，需要依次回调
	private List<String> arrClickTrackUrl;//：点击回调地址列表，需要依次回调
	private List<String> arrDownloadTrackUrl;// 开始下载跟踪地址
	private List<String> arrDownloadedTrakUrl ;//下载完成跟踪地址
	private List<String> arrIntalledTrackUrl;//：安装完成跟踪地址
	private int intStuffType;//：素材类型， 1、图片;2、图文;3、文字; 5、HTML
	private String strTitle;//：广告的标题
	private String strContent;//：广告的文字内容
	private String strIconImageUrl;//：广告推广app 的icon 地址
	private String strImageUrl;//：广告主图片
	private String strLinkUrl;//：推广链接，用于拼接点击上报url
	private String strADtypeId;//：广告类型id
	private String strStuffId;//：素材id
	private String strStuffWidth;//：素材宽度
	private String strStuffHeight;//：素材高度
	private int intFullScreen;//：是否全屏，0 否，1 是
	private int intActionType;//：响应类型，1、下载app;2 打开网页;3、android 应用市场下载4、ios appstore 下载
	private List<String> arrAdLogo;
	public String getStrAdId() {
		return strAdId;
	}
	public void setStrAdId(String strAdId) {
		this.strAdId = strAdId;
	}
	public List<String> getArrViewTrackUrl() {
		return arrViewTrackUrl;
	}
	public void setArrViewTrackUrl(List<String> arrViewTrackUrl) {
		this.arrViewTrackUrl = arrViewTrackUrl;
	}
	public List<String> getArrClickTrackUrl() {
		return arrClickTrackUrl;
	}
	public void setArrClickTrackUrl(List<String> arrClickTrackUrl) {
		this.arrClickTrackUrl = arrClickTrackUrl;
	}
	public List<String> getArrDownloadTrackUrl() {
		return arrDownloadTrackUrl;
	}
	public void setArrDownloadTrackUrl(List<String> arrDownloadTrackUrl) {
		this.arrDownloadTrackUrl = arrDownloadTrackUrl;
	}
	public List<String> getArrDownloadedTrakUrl() {
		return arrDownloadedTrakUrl;
	}
	public void setArrDownloadedTrakUrl(List<String> arrDownloadedTrakUrl) {
		this.arrDownloadedTrakUrl = arrDownloadedTrakUrl;
	}
	public List<String> getArrIntalledTrackUrl() {
		return arrIntalledTrackUrl;
	}
	public void setArrIntalledTrackUrl(List<String> arrIntalledTrackUrl) {
		this.arrIntalledTrackUrl = arrIntalledTrackUrl;
	}
	public int getIntStuffType() {
		return intStuffType;
	}
	public void setIntStuffType(int intStuffType) {
		this.intStuffType = intStuffType;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	public String getStrContent() {
		return strContent;
	}
	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}
	public String getStrIconImageUrl() {
		return strIconImageUrl;
	}
	public void setStrIconImageUrl(String strIconImageUrl) {
		this.strIconImageUrl = strIconImageUrl;
	}
	public String getStrImageUrl() {
		return strImageUrl;
	}
	public void setStrImageUrl(String strImageUrl) {
		this.strImageUrl = strImageUrl;
	}
	public String getStrLinkUrl() {
		return strLinkUrl;
	}
	public void setStrLinkUrl(String strLinkUrl) {
		this.strLinkUrl = strLinkUrl;
	}
	public String getStrADtypeId() {
		return strADtypeId;
	}
	public void setStrADtypeId(String strADtypeId) {
		this.strADtypeId = strADtypeId;
	}
	public String getStrStuffId() {
		return strStuffId;
	}
	public void setStrStuffId(String strStuffId) {
		this.strStuffId = strStuffId;
	}
	public String getStrStuffWidth() {
		return strStuffWidth;
	}
	public void setStrStuffWidth(String strStuffWidth) {
		this.strStuffWidth = strStuffWidth;
	}
	public String getStrStuffHeight() {
		return strStuffHeight;
	}
	public void setStrStuffHeight(String strStuffHeight) {
		this.strStuffHeight = strStuffHeight;
	}
	public int getIntFullScreen() {
		return intFullScreen;
	}
	public void setIntFullScreen(int intFullScreen) {
		this.intFullScreen = intFullScreen;
	}
	public int getIntActionType() {
		return intActionType;
	}
	public void setIntActionType(int intActionType) {
		this.intActionType = intActionType;
	}
	public List<String> getArrAdLogo() {
		return arrAdLogo;
	}
	public void setArrAdLogo(List<String> arrAdLogo) {
		this.arrAdLogo = arrAdLogo;
	}
}