package com.ocean.persist.api.proxy.jicheng;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月31日 
      @version 1.0 
 */
public class JichengAd {
	private int creative_type;/*	创意类型
	当前api接口可以支持的广告类型合集，包括以下类型
	1.纯文字（text）
	2.纯图片（image）
	3.图文混合
	4.不返还类型（no_type）该字段主要针对信息流
	5.当前 API 接口可以支持的广告创意类型集合，包括以下类型（具体选项可参考 Proto 文件“创意类型”部分）： 1. 纯文字（TEXT）。一般由 title、description 构成； 2. 纯图片（IMAGE）。一般由单张或多张image_src构成； 3. 图文（混合广告）。一般由单张icon_src和 title、description 构成，请注意是 icon 或 logo，非 image； 4. 不返回创意类型（NO_TYPE）。该字段主要针对信息流（自定义素材）广告设置，接入方可根据 MSSP 广告位设置对返回字段进行读取即可。
	*/
	private int interaction_type;	//交互类型当前 API 接口可以支持的广告交互类型集合，包括以下类型（具体选项可参考 Proto 文件“交互类型”部分）： 1. 打开网页（SURFING）。使用 Webview 或浏览器打开网页，目标页即 landing page，一般由广告主定义；建议使用应用内 Webview 打开，以免跳 出应用；用户可能进行点击或其它操作，建议在 Webview 中设置关闭广告或 返回按钮； 2. 点击下载（DOWNLOAD）。Android 可设置为直接下载或让用户进行确 认；iOS 可通过添加 Store Kit 框架，实现在应用内打开 app store 应用详情 页（仅 iOS 6.0 以上设备支持），以免直接跳出应用。应用下载使用 iOS store kit 需要传入 itunesid，开发者可根据 iTunes 链接自行解析 itunesid （最新了解到，APPLE 官方对 storekit 接口维护有异常，请谨慎使用，可通 过测试确认）。
	private String title	;	//标题 urlencode 处理
	private String txt;	//	副标题 urlencode 处理
	private String description;	//	;	//描述文字 urlencode 处理
	private List<String> icon_src	;//图标列表 可能包含多个
	private List<String> image_src	;	//图片列表
	private String click_url	;	//用户点击后，在客户端进行响应 会经过多次302跳转最终达到目的地址
	private String app_package	;	//推广包名
	private int app_size	;	//推广包大小
	private String deeplink;	//	app;	// 直链类型（一般不用） 空会返回0 是int 有内容链接返回字符串链接
	private String html_snippet	;	//html 内容（一般不用）空会返回0 是int 有内容返回HTML代码
	private JichengTrack track	;	//上报数组

	private JichengExt ext	;	//扩展字段

	public int getCreative_type() {
		return creative_type;
	}

	public void setCreative_type(int creative_type) {
		this.creative_type = creative_type;
	}

	public int getInteraction_type() {
		return interaction_type;
	}

	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getIcon_src() {
		return icon_src;
	}

	public void setIcon_src(List<String> icon_src) {
		this.icon_src = icon_src;
	}

	public List<String> getImage_src() {
		return image_src;
	}

	public void setImage_src(List<String> image_src) {
		this.image_src = image_src;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public String getApp_package() {
		return app_package;
	}

	public void setApp_package(String app_package) {
		this.app_package = app_package;
	}

	public int getApp_size() {
		return app_size;
	}

	public void setApp_size(int app_size) {
		this.app_size = app_size;
	}

	public String getDeeplink() {
		return deeplink;
	}

	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}

	public String getHtml_snippet() {
		return html_snippet;
	}

	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}

	public JichengTrack getTrack() {
		return track;
	}

	public void setTrack(JichengTrack track) {
		this.track = track;
	}

	public JichengExt getExt() {
		return ext;
	}

	public void setExt(JichengExt ext) {
		this.ext = ext;
	}

}
