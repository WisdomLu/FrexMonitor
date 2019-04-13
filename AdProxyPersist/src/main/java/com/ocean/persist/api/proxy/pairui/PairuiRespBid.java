package com.ocean.persist.api.proxy.pairui;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PairuiRespBid {

	// required String 买方给出本次竞价的id
	private String id;
	
	// required String 对应bid request中imp的id
	private String impid;
	
	// required  String  广告打开类型，注：每一个bid对象仅支持一种打开类型。
	// 1：landing，2：download，3：deeplink
	private String opentype;
	
	// crid required String KDG Ad Exchange提供的创意id
	private String crid;
	
//	private String style_id;
	
	// optional  object Banner类型广告的对象
	private PairuiRespBanner banner;
	
	// optional  object Video类型广告的对象;
	// 如果平台不支持Vast，这里不为空，否则为空
	private PairuiRespVideo video;
	
	// optional  object Native类型广告的对象
	@SerializedName("native")
	private PairuiRespNav nativeObj;
	
	// optional  String array 曝光监测地址，可能包含多个曝光监测
	private List<String> trackurls;
	
	// optional  String 点击跳转地址，当opentype=1时，该字段必填；
	// 实际当中可能是一次或多次302跳转，最终跳转至到达页。
	// 注：该字段与downloadurl、dplurl字段互斥。
	private String clickthrough;
	
	// landpage optional  String 到达地址，当opentype=3时，该字段必填；
	// 注：返回dplurl时，如果起吊APP失败，需通过该字段跳转H5
	// 的落地页
	private String landpage;
	
	// optional  String  下载地址，当opentype=2时，该字段必填；
	// 提供302方式的点击跳转下载，iOS将跳往App Store；Android
	// 默认为.apk的下载url。
	private String downloadurl;
	
	// optional  String  应用包名称，当opentype=2时，该字段必填；
	// Android为包名(package_name)，IOS为App Store中ID号或
	// 包名。
	private String bundle;
	
	// optional  String  起吊app到达目标页的url，当opentype=3时，该字段必填；
	// 注：如果通过dplurl起吊APP失败，需要触发landpage，跳转
	// 到H5落地页，并使用clickTrack发起点击监测；
	private String dplurl;
	
	// optional  String array  异步的点击监测。
	private List<String> clicktracking;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImpid() {
		return impid;
	}

	public void setImpid(String impid) {
		this.impid = impid;
	}

	public String getOpentype() {
		return opentype;
	}

	public void setOpentype(String opentype) {
		this.opentype = opentype;
	}

	public String getCrid() {
		return crid;
	}

	public void setCrid(String crid) {
		this.crid = crid;
	}

	public PairuiRespBanner getBanner() {
		return banner;
	}

	public void setBanner(PairuiRespBanner banner) {
		this.banner = banner;
	}

	public PairuiRespVideo getVideo() {
		return video;
	}

	public void setVideo(PairuiRespVideo video) {
		this.video = video;
	}

	public PairuiRespNav getNativeObj() {
		return nativeObj;
	}

	public void setNativeObj(PairuiRespNav nativeObj) {
		this.nativeObj = nativeObj;
	}

	public List<String> getTrackurls() {
		return trackurls;
	}

	public void setTrackurls(List<String> trackurls) {
		this.trackurls = trackurls;
	}

	public String getClickthrough() {
		return clickthrough;
	}

	public void setClickthrough(String clickthrough) {
		this.clickthrough = clickthrough;
	}

	public String getLandpage() {
		return landpage;
	}

	public void setLandpage(String landpage) {
		this.landpage = landpage;
	}

	public String getDownloadurl() {
		return downloadurl;
	}

	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public String getDplurl() {
		return dplurl;
	}

	public void setDplurl(String dplurl) {
		this.dplurl = dplurl;
	}

	public List<String> getClicktracking() {
		return clicktracking;
	}

	public void setClicktracking(List<String> clicktracking) {
		this.clicktracking = clicktracking;
	}
}
