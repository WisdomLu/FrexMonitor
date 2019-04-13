package com.ocean.persist.api.proxy.huixuan;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdAssets    {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1204252982005831583L;
	private int id;
	private boolean required;
	private  HuixuanAdTitle title;
	private  HuixuanAdImg img;
	private  HuixuanAdVideo video;
	private  HuixuanAdData data;

	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public HuixuanAdTitle getTitle() {
		return title;
	}
	public void setTitle(HuixuanAdTitle title) {
		this.title = title;
	}
	public HuixuanAdImg getImg() {
		return img;
	}
	public void setImg(HuixuanAdImg img) {
		this.img = img;
	}
	public HuixuanAdVideo getVideo() {
		return video;
	}
	public void setVideo(HuixuanAdVideo video) {
		this.video = video;
	}
	public HuixuanAdData getData() {
		return data;
	}
	public void setData(HuixuanAdData data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
