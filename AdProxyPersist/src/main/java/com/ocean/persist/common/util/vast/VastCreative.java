package com.ocean.persist.common.util.vast;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

public class VastCreative {

	private static final long serialVersionUID = 1L;

	private String duration;
	
	private List<VastMedia> medias;
	
	private List<String> linkurls;

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<VastMedia> getMedias() {
		return medias;
	}

	public void setMedias(List<VastMedia> medias) {
		this.medias = medias;
	}

	public List<String> getLinkurls() {
		return linkurls;
	}

	public void setLinkurls(List<String> linkurls) {
		this.linkurls = linkurls;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
