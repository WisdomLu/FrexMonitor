package com.ocean.persist.api.proxy.oneway;

import java.util.List;

public class OnewayData {
	private String sessionId;//	String	唯一回话ID
	private String appIcon;//		String	推广应用图标URL
	//appId	long	推广应用ID  已弃用
	private String campaignId;//		long	推广ID
	private String appName;//		String	推广应用名称
	private float rating;//		float	推广应用平均评分
	private int ratingCount;//		int	推广应用评价数
	private String appStoreId;//		String	应用包名
	private String title	;//	String	推广标题
	private int downloadType	;//	int	下载类型
	private String orientation;//		String	推广应用素材横竖屏方向 取值参考 附录 orientation
	private String videoUrl	;//	String	视频链接 已弃用, 请使用 video
	private float videoDuration;//		float	视频时长 已弃用, 请使用 video
	private String clickUrl	;//	String	点击跳转链接
	private String deeplink	;//	String	深度链接
	private String landingPageUrl	;//	String	落地页链接 (可选)
	private List<String> imgUrls	;//	String Array	图片素材列表  已弃用, 请使用 images
	private OnewayTracking trackingEvents;//		Object	事件跟踪链接, 必须实时处理上报否则会影响收益情况
	private int clickMode	;//	int	点击模式 0: 默认(落地页下载按钮可点) 1: 落地页全屏可点 2: 播放过程中全屏可点，落地页全屏可点
	private int skipSeconds	;//	int	秒数, 显示多少秒之后才可跳过
	private OnewayVideo video;//		Video	视频素材
	private List<OneWayImg> images	;//	Image Array	图片素材
	public String getSessionId() {
		return sessionId;
	}
	public String getAppIcon() {
		return appIcon;
	}
	public String getCampaignId() {
		return campaignId;
	}
	public String getAppName() {
		return appName;
	}
	public float getRating() {
		return rating;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public String getAppStoreId() {
		return appStoreId;
	}
	public String getTitle() {
		return title;
	}
	public int getDownloadType() {
		return downloadType;
	}
	public String getOrientation() {
		return orientation;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public float getVideoDuration() {
		return videoDuration;
	}
	public String getClickUrl() {
		return clickUrl;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public String getLandingPageUrl() {
		return landingPageUrl;
	}
	public List<String> getImgUrls() {
		return imgUrls;
	}
	public OnewayTracking getTrackingEvents() {
		return trackingEvents;
	}
	public int getClickMode() {
		return clickMode;
	}
	public int getSkipSeconds() {
		return skipSeconds;
	}
	public OnewayVideo getVideo() {
		return video;
	}
	public List<OneWayImg> getImages() {
		return images;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public void setAppStoreId(String appStoreId) {
		this.appStoreId = appStoreId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDownloadType(int downloadType) {
		this.downloadType = downloadType;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public void setVideoDuration(float videoDuration) {
		this.videoDuration = videoDuration;
	}
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public void setLandingPageUrl(String landingPageUrl) {
		this.landingPageUrl = landingPageUrl;
	}
	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}
	public void setTrackingEvents(OnewayTracking trackingEvents) {
		this.trackingEvents = trackingEvents;
	}
	public void setClickMode(int clickMode) {
		this.clickMode = clickMode;
	}
	public void setSkipSeconds(int skipSeconds) {
		this.skipSeconds = skipSeconds;
	}
	public void setVideo(OnewayVideo video) {
		this.video = video;
	}
	public void setImages(List<OneWayImg> images) {
		this.images = images;
	}
}
