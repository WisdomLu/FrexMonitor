package com.ocean.persist.api.proxy.oneway;

public class OnewayVideo {
	private String url;//	String	视频链接
	private float duration;//	float	视频时长
	private String coverUrl;//	String	封面图
	private String sourceUrl	;//String	视频来源链接
	public String getUrl() {
		return url;
	}
	public float getDuration() {
		return duration;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
}
