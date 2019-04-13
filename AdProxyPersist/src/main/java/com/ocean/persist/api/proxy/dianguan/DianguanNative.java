package com.ocean.persist.api.proxy.dianguan;

public class DianguanNative {
	private int type;
	private int interaction_type;
	private String image_snippet;
	private String  text_icon_snippet;
	private String video_snippet;
	private String ext;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getInteraction_type() {
		return interaction_type;
	}
	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}
	public String getImage_snippet() {
		return image_snippet;
	}
	public void setImage_snippet(String image_snippet) {
		this.image_snippet = image_snippet;
	}
	public String getText_icon_snippet() {
		return text_icon_snippet;
	}
	public void setText_icon_snippet(String text_icon_snippet) {
		this.text_icon_snippet = text_icon_snippet;
	}
	public String getVideo_snippet() {
		return video_snippet;
	}
	public void setVideo_snippet(String video_snippet) {
		this.video_snippet = video_snippet;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}

}
