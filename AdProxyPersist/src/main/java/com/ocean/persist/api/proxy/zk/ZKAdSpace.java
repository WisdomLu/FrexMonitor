package com.ocean.persist.api.proxy.zk;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/**
 * 
 * 广告位
 * 
 * @author dell
 *
 */
public class ZKAdSpace    {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7511097062758823194L;

	/** 广告位ID，由INVENO统一规范 */
	private String adspace_id;

	/** 广告位类型 */
	private int adspace_type;

	/** 广告位宽度(像素) */
	private int width;

	/** 广告位高度(像素) */
	private int height;

	/** 广告位当前页关键字 */
	private String keywords;

	/** 广告位所在频道 */
	private String channel;

	/** 广告位请求广告的数量 */
	private int adNum;

	/** 广告位支持的交互类型 */
	private List<Integer> interaction_type;// 交互类型

	/** 广告位必需的物料 */
	private List<ZKAssetType> asset;

	/** 广告位曝光时长，单位秒 */
	private int impression_time;

	/** 广告位所在页面的唯一标识 */
	private String page_id;
	
	/** 广告位是否支持html素材 */
	private Boolean allowed_html;
    private int impression_num;
    private int open_type;
	public String getAdspace_id() {
		return adspace_id;
	}

	public void setAdspace_id(String adspace_id) {
		this.adspace_id = adspace_id;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getAdNum() {
		return adNum;
	}

	public void setAdNum(int adNum) {
		this.adNum = adNum;
	}

	public List<ZKAssetType> getAsset() {
		return asset;
	}

	public void setAsset(List<ZKAssetType> asset) {
		this.asset = asset;
	}

	public int getImpression_time() {
		return impression_time;
	}

	public void setImpression_time(int impression_time) {
		this.impression_time = impression_time;
	}

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public Boolean getAllowed_html() {
		return allowed_html;
	}

	public void setAllowed_html(Boolean allowed_html) {
		this.allowed_html = allowed_html;
	}

	public int getAdspace_type() {
		return adspace_type;
	}

	public void setAdspace_type(int adspace_type) {
		this.adspace_type = adspace_type;
	}

	public int getImpression_num() {
		return impression_num;
	}

	public void setImpression_num(int impression_num) {
		this.impression_num = impression_num;
	}

	public int getOpen_type() {
		return open_type;
	}

	public void setOpen_type(int open_type) {
		this.open_type = open_type;
	}

	public List<Integer> getInteraction_type() {
		return interaction_type;
	}

	public void setInteraction_type(List<Integer> interaction_type) {
		this.interaction_type = interaction_type;
	}


}
