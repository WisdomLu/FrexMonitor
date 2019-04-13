package com.ocean.persist.api.proxy.lingji;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/**
 * // 信息流属性定义 // title 1 // desc 2 // source 3 // img 4 // logo 5 // icon 6 //
 * video 7 // rating 8 // like 9 // downloads 10 // price 11 // call_to_auction
 * 12 // store 13 // appname 14 // bundle 15
 */
public class LingjiNativead   {

	private static final long serialVersionUID = 1L;

	private List<String> required_fields;// 广告展示必须的信息流元素,传信息流元素定义的编号,示例：["1","2","3","14"]
	private Integer title_max_safe_length;// title允许的最大字符个数
	private Integer desc_max_safe_length;// description允许的最大字符个数
	private Integer source_max_safe_length;// 广告主来源允许的最大字符个数
	private Integer rating_max_safe_length;// app评级允许的最大字符个数
	private Integer like_max_safe_length;// 用户喜欢度评级的最大字符个数
	private Integer downloads_max_safe_length;// app下载量允许的最大字符个数
	private Integer price_max_safe_length;// app价格允许的最大字符个数
	private Integer call_to_auction_max_safe_length;// 用户行为按钮允许的最大字符个数
	private Integer store_max_safe_length;// app来源允许的最大字符个数
	private Integer img_width;// img图片宽度
	private Integer img_height;// img图片高度
	private Integer img_num;// img图片个数
	private Integer logo_width;// logo图片宽度
	private Integer logo_height;// logo图片高度
	private Integer icon_width;// icon图片宽度
	private Integer icon_height;// icon图片高度
	private Integer video_width;// 贴片图片宽度
	private Integer video_height;// 贴片图片高度
	private Integer video_minduration;// 贴片允许最小时长
	private Integer video_maxduration;// 贴片允许最大时长

	public List<String> getRequired_fields() {
		return required_fields;
	}

	public void setRequired_fields(List<String> required_fields) {
		this.required_fields = required_fields;
	}

	public Integer getTitle_max_safe_length() {
		return title_max_safe_length;
	}

	public void setTitle_max_safe_length(Integer title_max_safe_length) {
		this.title_max_safe_length = title_max_safe_length;
	}

	public Integer getDesc_max_safe_length() {
		return desc_max_safe_length;
	}

	public void setDesc_max_safe_length(Integer desc_max_safe_length) {
		this.desc_max_safe_length = desc_max_safe_length;
	}

	public Integer getSource_max_safe_length() {
		return source_max_safe_length;
	}

	public void setSource_max_safe_length(Integer source_max_safe_length) {
		this.source_max_safe_length = source_max_safe_length;
	}

	public Integer getRating_max_safe_length() {
		return rating_max_safe_length;
	}

	public void setRating_max_safe_length(Integer rating_max_safe_length) {
		this.rating_max_safe_length = rating_max_safe_length;
	}

	public Integer getLike_max_safe_length() {
		return like_max_safe_length;
	}

	public void setLike_max_safe_length(Integer like_max_safe_length) {
		this.like_max_safe_length = like_max_safe_length;
	}

	public Integer getDownloads_max_safe_length() {
		return downloads_max_safe_length;
	}

	public void setDownloads_max_safe_length(Integer downloads_max_safe_length) {
		this.downloads_max_safe_length = downloads_max_safe_length;
	}

	public Integer getPrice_max_safe_length() {
		return price_max_safe_length;
	}

	public void setPrice_max_safe_length(Integer price_max_safe_length) {
		this.price_max_safe_length = price_max_safe_length;
	}

	public Integer getCall_to_auction_max_safe_length() {
		return call_to_auction_max_safe_length;
	}

	public void setCall_to_auction_max_safe_length(
			Integer call_to_auction_max_safe_length) {
		this.call_to_auction_max_safe_length = call_to_auction_max_safe_length;
	}

	public Integer getStore_max_safe_length() {
		return store_max_safe_length;
	}

	public void setStore_max_safe_length(Integer store_max_safe_length) {
		this.store_max_safe_length = store_max_safe_length;
	}

	public Integer getImg_width() {
		return img_width;
	}

	public void setImg_width(Integer img_width) {
		this.img_width = img_width;
	}

	public Integer getImg_height() {
		return img_height;
	}

	public void setImg_height(Integer img_height) {
		this.img_height = img_height;
	}

	public Integer getImg_num() {
		return img_num;
	}

	public void setImg_num(Integer img_num) {
		this.img_num = img_num;
	}

	public Integer getLogo_width() {
		return logo_width;
	}

	public void setLogo_width(Integer logo_width) {
		this.logo_width = logo_width;
	}

	public Integer getLogo_height() {
		return logo_height;
	}

	public void setLogo_height(Integer logo_height) {
		this.logo_height = logo_height;
	}

	public Integer getIcon_width() {
		return icon_width;
	}

	public void setIcon_width(Integer icon_width) {
		this.icon_width = icon_width;
	}

	public Integer getIcon_height() {
		return icon_height;
	}

	public void setIcon_height(Integer icon_height) {
		this.icon_height = icon_height;
	}

	public Integer getVideo_width() {
		return video_width;
	}

	public void setVideo_width(Integer video_width) {
		this.video_width = video_width;
	}

	public Integer getVideo_height() {
		return video_height;
	}

	public void setVideo_height(Integer video_height) {
		this.video_height = video_height;
	}

	public Integer getVideo_minduration() {
		return video_minduration;
	}

	public void setVideo_minduration(Integer video_minduration) {
		this.video_minduration = video_minduration;
	}

	public Integer getVideo_maxduration() {
		return video_maxduration;
	}

	public void setVideo_maxduration(Integer video_maxduration) {
		this.video_maxduration = video_maxduration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
