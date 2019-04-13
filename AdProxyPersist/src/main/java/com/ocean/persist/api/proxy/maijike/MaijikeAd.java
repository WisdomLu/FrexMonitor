package com.ocean.persist.api.proxy.maijike;

import java.util.List;

public class MaijikeAd {
	private int creative_type; 
	/*Int 创意类型 1:纯文字（TEXT）。一般由 title、description
	构成；
	2:纯图片（IMAGE）。一般由单张image_src
	构成；
	3:图文（icon 混合广告）。由单张icon_url
	和 title、description 构成，请
	注意是 icon 或 logo，非 image；
	4: 视频广告（VIDEO）。一般由视频 URL
	和视频时长构成;
	5: 图文（ image 混合广告）。由单张
	image_url icon_url 和 title、description
	构成。*/
	private int interaction_type;
	private String  title;
	private String image_url;
	private MaijikeUrlFmt click_url ;
	private List<MaijikeUrlFmt>  impression_log_url;
	private List<MaijikeUrlFmt>  click_monitor_url;
	private List<MaijikeUrlFmt>  down_start_url;
	private List<MaijikeUrlFmt>  down_complete_url;
	private List<MaijikeUrlFmt>  install_start_url;
	private List<MaijikeUrlFmt>  install_complete_url;
	private List<MaijikeUrlFmt>  start_app_url;
	private List<MaijikeUrlFmt>  deeplink_click;
	private List<MaijikeUrlFmt>  ad_close_monitor;
	private List<MaijikeUrlFmt>  video_ad_start;// JSONArray 视频开始播放
	private List<MaijikeUrlFmt>  video_ad_full_screen;// JSONArray 视频全屏开
/*	始播放
	需要对URL 里面的${PROGRESS}宏进行替
	换，该宏表示触发该事件时的广告播放进
	度，单位毫秒，再发送这个URL。
	示例，如当前播放为7.5 秒，格式为
	http://domain.com/xx.php?event=xx&
	progress=7500*/
	private List<MaijikeUrlFmt>  video_ad_end ;//JSONArray 视频正常播放结束
	private List<MaijikeUrlFmt>  video_ad_start_card_click ;//JSONArray 点击预览图播放
	
	private int width;//":600,
	private int height;//":500,
	private String html;//":"",
	private int fallback_type ;//int 类型 deeplink 唤醒广告退化类型，1：浏览器打开页面，2：APP 下载
	private String deeplink;//":""
	private String pkgname;
	private String sessid;
	private String video_url ;//String 广告视频物料地址
	private String video_duration;// String 广告视频物料时长
	public int getInteraction_type() {
		return interaction_type;
	}
	public String getTitle() {
		return title;
	}
	public String getImage_url() {
		return image_url;
	}
	public MaijikeUrlFmt getClick_url() {
		return click_url;
	}
	public List<MaijikeUrlFmt> getImpression_log_url() {
		return impression_log_url;
	}
	public List<MaijikeUrlFmt> getClick_monitor_url() {
		return click_monitor_url;
	}
	public List<MaijikeUrlFmt> getDown_start_url() {
		return down_start_url;
	}
	public List<MaijikeUrlFmt> getDown_complete_url() {
		return down_complete_url;
	}
	public List<MaijikeUrlFmt> getInstall_start_url() {
		return install_start_url;
	}
	public List<MaijikeUrlFmt> getInstall_complete_url() {
		return install_complete_url;
	}
	public List<MaijikeUrlFmt> getStart_app_url() {
		return start_app_url;
	}
	public List<MaijikeUrlFmt> getDeeplink_click() {
		return deeplink_click;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getHtml() {
		return html;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public void setClick_url(MaijikeUrlFmt click_url) {
		this.click_url = click_url;
	}
	public void setImpression_log_url(List<MaijikeUrlFmt> impression_log_url) {
		this.impression_log_url = impression_log_url;
	}
	public void setClick_monitor_url(List<MaijikeUrlFmt> click_monitor_url) {
		this.click_monitor_url = click_monitor_url;
	}
	public void setDown_start_url(List<MaijikeUrlFmt> down_start_url) {
		this.down_start_url = down_start_url;
	}
	public void setDown_complete_url(List<MaijikeUrlFmt> down_complete_url) {
		this.down_complete_url = down_complete_url;
	}
	public void setInstall_start_url(List<MaijikeUrlFmt> install_start_url) {
		this.install_start_url = install_start_url;
	}
	public void setInstall_complete_url(List<MaijikeUrlFmt> install_complete_url) {
		this.install_complete_url = install_complete_url;
	}
	public void setStart_app_url(List<MaijikeUrlFmt> start_app_url) {
		this.start_app_url = start_app_url;
	}
	public void setDeeplink_click(List<MaijikeUrlFmt> deeplink_click) {
		this.deeplink_click = deeplink_click;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public String getPkgname() {
		return pkgname;
	}
	public String getSessid() {
		return sessid;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public void setSessid(String sessid) {
		this.sessid = sessid;
	}
	public List<MaijikeUrlFmt> getVideo_ad_start() {
		return video_ad_start;
	}
	public List<MaijikeUrlFmt> getVideo_ad_full_screen() {
		return video_ad_full_screen;
	}
	public List<MaijikeUrlFmt> getVideo_ad_end() {
		return video_ad_end;
	}
	public List<MaijikeUrlFmt> getVideo_ad_start_card_click() {
		return video_ad_start_card_click;
	}
	public int getFallback_type() {
		return fallback_type;
	}
	public String getVideo_url() {
		return video_url;
	}
	public String getVideo_duration() {
		return video_duration;
	}
	public void setVideo_ad_start(List<MaijikeUrlFmt> video_ad_start) {
		this.video_ad_start = video_ad_start;
	}
	public void setVideo_ad_full_screen(List<MaijikeUrlFmt> video_ad_full_screen) {
		this.video_ad_full_screen = video_ad_full_screen;
	}
	public void setVideo_ad_end(List<MaijikeUrlFmt> video_ad_end) {
		this.video_ad_end = video_ad_end;
	}
	public void setVideo_ad_start_card_click(
			List<MaijikeUrlFmt> video_ad_start_card_click) {
		this.video_ad_start_card_click = video_ad_start_card_click;
	}
	public void setFallback_type(int fallback_type) {
		this.fallback_type = fallback_type;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public void setVideo_duration(String video_duration) {
		this.video_duration = video_duration;
	}
	public List<MaijikeUrlFmt> getAd_close_monitor() {
		return ad_close_monitor;
	}
	public void setAd_close_monitor(List<MaijikeUrlFmt> ad_close_monitor) {
		this.ad_close_monitor = ad_close_monitor;
	}
	public int getCreative_type() {
		return creative_type;
	}
	public void setCreative_type(int creative_type) {
		this.creative_type = creative_type;
	}
	
}
