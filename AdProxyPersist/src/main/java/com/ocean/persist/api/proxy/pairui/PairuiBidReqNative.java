package com.ocean.persist.api.proxy.pairui;

public class PairuiBidReqNative {

	// optional  object 图片对象
	private PairuiBidReqNativeImg image;
	
	// optional object  图标/Logo对象
	private PairuiBidReqNativeImg icon;
	
	// optional object  视频对象
	private PairuiBidReqNativeVideo video;
	
	// optional integer  标题，填入的数字代表允许的标题长度
	private Integer title;
	
	// optional integer  描述，填入的数字代表允许的描述长度
	private Integer description;

	public PairuiBidReqNativeImg getImage() {
		return image;
	}

	public void setImage(PairuiBidReqNativeImg image) {
		this.image = image;
	}

	public PairuiBidReqNativeImg getIcon() {
		return icon;
	}

	public void setIcon(PairuiBidReqNativeImg icon) {
		this.icon = icon;
	}

	public PairuiBidReqNativeVideo getVideo() {
		return video;
	}

	public void setVideo(PairuiBidReqNativeVideo video) {
		this.video = video;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public Integer getDescription() {
		return description;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}
}
