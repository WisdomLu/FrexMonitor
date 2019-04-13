package com.ocean.persist.api.proxy.yileyun;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunMaterial {
	private int creative_type;/*
	CreativeType
	Required
	该广告的创意类型，文字=1；静态图片=2 图文=6；*/
	private int interaction_type;/*
	InteractionType
	Optional
	该 广 告 支 持 的 交 互 类 型 ，使用浏览器打 开=2；应用内打开=3；下载应用=4*/
	private YileyunImage image;/*
	Image
	Optional
	素材图片*/
	private String target_url;/*
	string
	Optional
	创 意 的 落 地 页 url， 当 广 告 交 互 类 型 为 2，3 时，使用此 url.*/
	private String download_url;/*
	string
	Optional
	应 用 下 载 url， 当 广告 交互类型为 4
	时候，使用此 url.*/
	private String title;/*
	string
	Optional
	广告标题*/
	private String description;/*
	string
	Optional
	广告描述
	*/
	private String source;/*
	strin g
	O p tio nal
	落 地 页 的来源*/
	private String app_name;/*
	strin g
	O p tio nal
	针 对 应 用下载类广告*/
	private String package_name;/*
	strin g
	O p tio nal
	应 用 下 载包名*/
	private List<String> win_notice_url;/*
	strin g a rra y
	O p tio nal
	获 胜 通 知的 u rl 列 表*/
	private List<String> show_url;/*
	strin g a rray
	O p tio nal
	展现监测 u rl 列表 ,按实际展现上报，如广 告 划 出 屏 幕 ， 再 划 入 ， 算 两次 ， 通 过 H T TP 上 报*/
	private List<String> click_url;/*
	strin g a rra y
	O p tio nal
	点 击 监 测 u rl 列表，按实际点击上报， 通 过 H TTP 上报*/
	private List<String> ds_url;/*
	strin g a rray
	O p tio nal
	应 用 类 下载开始监测 u rl 列表，按实际下 载 开 始 上报，通 过 HTTP上 报*/
	
	private List<String> df_url;/*
	strin g a rray
	O p tio nal
	应 用 类 下载完成监测 u rl 列表，按实际下 载 完 成 上报，通 过 H TTP 上报*/
	private List<String> sf_url;/*
	strin g a rray
	O p tio nal
	应 用 类 安装完成监测 u rl 列表，按实际安 装 完 成 上报，通 过 H TTP 上报*/
	private String ext;/*
	strin g
	O p tio nal
	扩 展 字 段*/
	private int image_mode;/*
	Im a g e Mode
	O p tio nal
	素 材 模 式， 小图=2；大图=3；组图=4*/
	private List<YileyunImage> image_list;/*
	Im a g e
	O p tio nal
	多 图*/
	private String phone_num;/*
	strin g
	O p tio nal
	电 话 拨 打广告，号码*/
	private String button_text;/*
	strin g
	O p tio nal
	、
	附 加 创 意 按 钮 上 的名称， 如立即下载
	马 上 拨打、 现在申请等*/
	private String icon;/*
	strin g
	O p tio nal
	创 意 中 的 ico n 的 u rl
	Image对象
	字段*/
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
	public YileyunImage getImage() {
		return image;
	}
	public void setImage(YileyunImage image) {
		this.image = image;
	}
	public String getTarget_url() {
		return target_url;
	}
	public void setTarget_url(String target_url) {
		this.target_url = target_url;
	}
	public String getDownload_url() {
		return download_url;
	}
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public List<String> getWin_notice_url() {
		return win_notice_url;
	}
	public void setWin_notice_url(List<String> win_notice_url) {
		this.win_notice_url = win_notice_url;
	}
	public List<String> getShow_url() {
		return show_url;
	}
	public void setShow_url(List<String> show_url) {
		this.show_url = show_url;
	}
	public List<String> getClick_url() {
		return click_url;
	}
	public void setClick_url(List<String> click_url) {
		this.click_url = click_url;
	}
	public List<String> getDs_url() {
		return ds_url;
	}
	public void setDs_url(List<String> ds_url) {
		this.ds_url = ds_url;
	}
	public List<String> getDf_url() {
		return df_url;
	}
	public void setDf_url(List<String> df_url) {
		this.df_url = df_url;
	}
	public List<String> getSf_url() {
		return sf_url;
	}
	public void setSf_url(List<String> sf_url) {
		this.sf_url = sf_url;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public int getImage_mode() {
		return image_mode;
	}
	public void setImage_mode(int image_mode) {
		this.image_mode = image_mode;
	}
	public List<YileyunImage> getImage_list() {
		return image_list;
	}
	public void setImage_list(List<YileyunImage> image_list) {
		this.image_list = image_list;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getButton_text() {
		return button_text;
	}
	public void setButton_text(String button_text) {
		this.button_text = button_text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
