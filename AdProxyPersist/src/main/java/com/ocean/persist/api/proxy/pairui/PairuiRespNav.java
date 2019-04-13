package com.ocean.persist.api.proxy.pairui;

import java.util.List;

public class PairuiRespNav {

	// optional  Object array 图片对象
	private List<PairuiRespImg> image;
	
	// optional Object  图标/Logo对象
	private PairuiRespImg icon;
	
	// optional  Object  视频对象
	private PairuiRespVideo video;
	
	// optional  String  标题内容
	private String title;
	
	// optional  String  描述内容
	private String description;

	public List<PairuiRespImg> getImage() {
		return image;
	}

	public void setImage(List<PairuiRespImg> image) {
		this.image = image;
	}

	public PairuiRespImg getIcon() {
		return icon;
	}

	public void setIcon(PairuiRespImg icon) {
		this.icon = icon;
	}

	public PairuiRespVideo getVideo() {
		return video;
	}

	public void setVideo(PairuiRespVideo video) {
		this.video = video;
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
	
}
