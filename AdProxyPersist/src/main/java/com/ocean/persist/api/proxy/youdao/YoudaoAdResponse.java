package com.ocean.persist.api.proxy.youdao;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;


public class YoudaoAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	private Integer creativeid;// required	Integer	广告 id
	private String clk;// required	String	点击链接
	private List<String> clktrackers;// required	Array	点击跟踪链接数组
	private List<String> imptracker;// required	Array	展示跟踪链接数组
	private String deeplink ;//optional String URI schemes 形式的deep link 链接。如果广告商设置了此URL 将会返回。
	private List<String>  dptrackers;// optional Array deep link 点击跟踪链接数组。当返回deeplink 字段时返回此字段。
	
	
	private Integer ydAdType;// required	Integer	广告类型，0：落地页广告；1：下载类型广告
	private String styleName ;//required String 广告样式名称
	
	private String iconimage;// optional	String	
	private String mainimage;// optional	String	
	private String text;// optinal	String	
	private String title;// optional	String	
	private String appName ;//optional String 当广告类型为下载类型时，并且appName 不为空时，会返
	//回此字段。该字段会将样式中的同名字段覆盖
	private String packageName ;//optional String 当广告类型为下载类型时，并且packageName 不为
	public Integer getCreativeid() {
		return creativeid;
	}

	public void setCreativeid(Integer creativeid) {
		this.creativeid = creativeid;
	}

	public String getClk() {
		return clk;
	}

	public void setClk(String clk) {
		this.clk = clk;
	}

	public List<String> getClktrackers() {
		return clktrackers;
	}

	public void setClktrackers(List<String> clktrackers) {
		this.clktrackers = clktrackers;
	}

	public List<String> getImptracker() {
		return imptracker;
	}

	public void setImptracker(List<String> imptracker) {
		this.imptracker = imptracker;
	}

	public Integer getYdAdType() {
		return ydAdType;
	}

	public void setYdAdType(Integer ydAdType) {
		this.ydAdType = ydAdType;
	}

	public String getIconimage() {
		return iconimage;
	}

	public void setIconimage(String iconimage) {
		this.iconimage = iconimage;
	}

	public String getMainimage() {
		return mainimage;
	}

	public void setMainimage(String mainimage) {
		this.mainimage = mainimage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDeeplink() {
		return deeplink;
	}

	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}

	public List<String> getDptrackers() {
		return dptrackers;
	}

	public void setDptrackers(List<String> dptrackers) {
		this.dptrackers = dptrackers;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
