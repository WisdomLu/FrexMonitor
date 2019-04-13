package com.ocean.persist.api.proxy.zhuoyiV2;

import java.util.List;

public class ZhuoyiVdPlayPct {
	private double checkpoint;//	optional	double	视频播放进度
	private List<String> urls;//	optional	array of string	播放进度追踪
	public double getCheckpoint() {
		return checkpoint;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setCheckpoint(double checkpoint) {
		this.checkpoint = checkpoint;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
}
