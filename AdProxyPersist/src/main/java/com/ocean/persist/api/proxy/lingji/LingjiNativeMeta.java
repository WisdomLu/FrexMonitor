package com.ocean.persist.api.proxy.lingji;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/**
 * 信息流广告创意 object
 */
public class LingjiNativeMeta  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String title;// 信息流广告title的值
	private String desc;// 信息流广告description的值
	private String source;// 信息流广告广告主/广告来源的值
	private List<LingjiImage> image;// 信息流广告主图片信息
	private LingjiImage logo;// 信息流广告logo信息
	private LingjiImage icon;// 信息流广告icon信息
	private LingjiVideo video;// 信息流广告video信息
	private String rating;// app评级信息
	private String like;// app用户喜欢度信息
	private String downloads;// app下载量
	private String price;// app价格
	private String call_to_auction;// 用户行为按钮信息
	private String store;// app来源信息
	private String appname;// 下载类广告---app名称
	private String bundle;// packagename 或 bundleID
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<LingjiImage> getImage() {
		return image;
	}

	public void setImage(List<LingjiImage> image) {
		this.image = image;
	}

	public LingjiImage getLogo() {
		return logo;
	}

	public void setLogo(LingjiImage logo) {
		this.logo = logo;
	}

	public LingjiImage getIcon() {
		return icon;
	}

	public void setIcon(LingjiImage icon) {
		this.icon = icon;
	}

	public LingjiVideo getVideo() {
		return video;
	}

	public void setVideo(LingjiVideo video) {
		this.video = video;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getDownloads() {
		return downloads;
	}

	public void setDownloads(String downloads) {
		this.downloads = downloads;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCall_to_auction() {
		return call_to_auction;
	}

	public void setCall_to_auction(String call_to_auction) {
		this.call_to_auction = call_to_auction;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
