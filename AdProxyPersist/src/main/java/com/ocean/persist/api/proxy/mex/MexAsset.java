package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

public class MexAsset {

	private static final long serialVersionUID = 1L;
	
	// assetID，由MEX生成。在每一种原生广告的布局中，每一个物料的asset是固定的。见附录2 原生广告布局类型
	private Integer id; 
	private MexTitle title;// title物料对象 
	private MexImage img;// img物料对象 
	private MexVideo video;// video物料对象 
	private MexData data;// data物料对象 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MexTitle getTitle() {
		return title;
	}

	public void setTitle(MexTitle title) {
		this.title = title;
	}

	public MexImage getImg() {
		return img;
	}

	public void setImg(MexImage img) {
		this.img = img;
	}

	public MexVideo getVideo() {
		return video;
	}

	public void setVideo(MexVideo video) {
		this.video = video;
	}

	public MexData getData() {
		return data;
	}

	public void setData(MexData data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
