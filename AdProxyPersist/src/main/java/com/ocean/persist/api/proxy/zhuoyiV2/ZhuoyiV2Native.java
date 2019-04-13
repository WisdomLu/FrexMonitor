package com.ocean.persist.api.proxy.zhuoyiV2;

import java.util.List;

public class ZhuoyiV2Native {
	private String id	;//	required		;//	广告 ID
	private int native_material_type		;//required	int	物料类型
/*	0.NO_TYPE
	1.TEXT
	2.IMAGE
	3.TEXT_ICON
	4.VIDEO*/
	private int interaction_type	;//	required	int	广告交互类型
/*	0.NO_INTERACT（无动作）
	1.SURFING （打开链接）
	2.DOWNLOAD （下载）
	3.ANDROID_STORE（安卓市场）
	4.APPLE_STORE(苹果市场)
	5.OPEN_EXTERNAL(调起应用)
	6.TOUTIAO_ DOWNLOAD(今日头条下载)*/
	private String adicon	;//optional	String	"广告"字样
	private String adlogo	;//optional	String	广告来源 logo
	private String title	;//optional	string	广告标题
	private String description;//	optional	string	广告描述
	private String image_url;//	optional	string	图片物料 url
	private List<String> ext_image_url;//	optional	;//array of
	//string	三图物料中的第二第三图（第一张存于 image_url）
	private ZhuoyiV2Size image_size	;//optional	size object	图片尺寸
	private String logo_url;//	optional	string	图标物料 url
	private ZhuoyiV2Size logo_size;//	optional	size object	图标大小
	private String click_url	;//required	string	点击地址


	private String landing_page	;//optional	string	Deeplink 调起失败后的落地页
	private String 	video_url	;//optional	string	当返回的广告是视频物料时，此字段表示视频广告的地址，支持流
	//媒体播放
	private int video_duration	;//optional	int	当返回的广告是视频物料时，此字段表示视频广告的时长，单位秒
	private List<String> impression_log_url	;//required	array of
	//string	展现监测 url
	private List<String> click_monitor_url	;//optional	array of
	//string	点击监测 url
	private String app_name	;//optional	string	下载类应用的名称
	private String app_pkg	;//optional	string	下载类应用的包名
	private List<String> app_download_start	;//required	array of
	//string	下载开始监测
	private List<String> app_download	;//required	array of
	//string	下载完成监测
	private List<String> app_install_start	;//required	array of
	//string	安装开始监测
	private List<String> app_install	;//required	array of
	//string	安装完成监测
	private List<String> app_active	;//required	array of
	//string	激活监测
	private List<String> app_open	;//required	array of
	//string	安装完成后打开监测
	private String app_size	;//optional	string	应用包大小
	private List<ZhuoyiVdTrack> ad_tracking;//	optional	array of tracking object	广告效果监控信息
/*	1） 激励视频类广告展示过程事件：广告被点击、 视频开始播放、 广告被关闭、 广告跳过。 对于要接入激励视频的用户必须实现相应的监控接口，对接方除了发送相应的监控
	URL，还需要对有”als.baidu.com/clog/glog”特征的 5 个 URL 里面的%25%25XXX%25%25 宏进行替换。其中
	tracking_event:VIDEO_AD_CLICK 代表广告被点击、
	tracking_event:VIDEO_AD_START 代表视频开始播放、
	tracking_event:VIDEO_AD_CLOSE 代表广告被关闭、
	tracking_event:VIDEO_AD_SKIP 代表广告跳过
	click_url: 点击地址落地页
	impression_log_url: 展现监测
	a 展现、 点击、开始播放、关闭、跳过 url 的
	origin_time=%25%25origin_time%25%25 代表事件发生 unix 时间戳(毫秒)；
	b 点击 url 的 da_area=%25%25area%25%25 代表点击区域,目前只用 hot 替换；
	c 开 始 播 放 url 的 da_ext1=%25%25play_mode%25%25 代表播放方式 0：自动播放、 1：手动播放；
	d 关 闭 url 的 da_ext2=%25%25cur_time%25%25 代 表 打
	印当前播放的进度（秒）、


				da_ext4=%25%25start_time%25%25 代表打印开始播放时的
	进度（秒）*/
	private ZhuoyiVdPlayTrack ad_playtracker;
	public String getId() {
		return id;
	}
	public int getNative_material_type() {
		return native_material_type;
	}
	public int getInteraction_type() {
		return interaction_type;
	}
	public String getAdicon() {
		return adicon;
	}
	public String getAdlogo() {
		return adlogo;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getImage_url() {
		return image_url;
	}
	public List<String> getExt_image_url() {
		return ext_image_url;
	}
	public ZhuoyiV2Size getImage_size() {
		return image_size;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public ZhuoyiV2Size getLogo_size() {
		return logo_size;
	}
	public String getClick_url() {
		return click_url;
	}
	public String getLanding_page() {
		return landing_page;
	}
	public String getVideo_url() {
		return video_url;
	}
	public int getVideo_duration() {
		return video_duration;
	}
	public List<String> getImpression_log_url() {
		return impression_log_url;
	}
	public List<String> getClick_monitor_url() {
		return click_monitor_url;
	}
	public String getApp_name() {
		return app_name;
	}
	public String getApp_pkg() {
		return app_pkg;
	}
	public List<String> getApp_download_start() {
		return app_download_start;
	}
	public List<String> getApp_download() {
		return app_download;
	}
	public List<String> getApp_install_start() {
		return app_install_start;
	}
	public List<String> getApp_install() {
		return app_install;
	}
	public List<String> getApp_active() {
		return app_active;
	}
	public List<String> getApp_open() {
		return app_open;
	}
	public String getApp_size() {
		return app_size;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setNative_material_type(int native_material_type) {
		this.native_material_type = native_material_type;
	}
	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}
	public void setAdicon(String adicon) {
		this.adicon = adicon;
	}
	public void setAdlogo(String adlogo) {
		this.adlogo = adlogo;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public void setExt_image_url(List<String> ext_image_url) {
		this.ext_image_url = ext_image_url;
	}
	public void setImage_size(ZhuoyiV2Size image_size) {
		this.image_size = image_size;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public void setLogo_size(ZhuoyiV2Size logo_size) {
		this.logo_size = logo_size;
	}
	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}
	public void setLanding_page(String landing_page) {
		this.landing_page = landing_page;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public void setVideo_duration(int video_duration) {
		this.video_duration = video_duration;
	}
	public void setImpression_log_url(List<String> impression_log_url) {
		this.impression_log_url = impression_log_url;
	}
	public void setClick_monitor_url(List<String> click_monitor_url) {
		this.click_monitor_url = click_monitor_url;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public void setApp_pkg(String app_pkg) {
		this.app_pkg = app_pkg;
	}
	public void setApp_download_start(List<String> app_download_start) {
		this.app_download_start = app_download_start;
	}
	public void setApp_download(List<String> app_download) {
		this.app_download = app_download;
	}
	public void setApp_install_start(List<String> app_install_start) {
		this.app_install_start = app_install_start;
	}
	public void setApp_install(List<String> app_install) {
		this.app_install = app_install;
	}
	public void setApp_active(List<String> app_active) {
		this.app_active = app_active;
	}
	public void setApp_open(List<String> app_open) {
		this.app_open = app_open;
	}
	public void setApp_size(String app_size) {
		this.app_size = app_size;
	}
	public List<ZhuoyiVdTrack> getAd_tracking() {
		return ad_tracking;
	}
	public ZhuoyiVdPlayTrack getAd_playtracker() {
		return ad_playtracker;
	}
	public void setAd_tracking(List<ZhuoyiVdTrack> ad_tracking) {
		this.ad_tracking = ad_tracking;
	}
	public void setAd_playtracker(ZhuoyiVdPlayTrack ad_playtracker) {
		this.ad_playtracker = ad_playtracker;
	}
	
}
