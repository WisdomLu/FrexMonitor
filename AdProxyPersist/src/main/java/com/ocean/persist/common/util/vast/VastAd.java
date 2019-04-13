package com.ocean.persist.common.util.vast;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;


public class VastAd  implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private Integer sequence;
	
	private String title;
	
	private String desc;
	
	private List<String> impr;
	
	private List<VastCreative> creatives;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public List<String> getImpr() {
		return impr;
	}

	public void setImpr(List<String> impr) {
		this.impr = impr;
	}

	public List<VastCreative> getCreatives() {
		return creatives;
	}

	public void setCreatives(List<VastCreative> creatives) {
		this.creatives = creatives;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
