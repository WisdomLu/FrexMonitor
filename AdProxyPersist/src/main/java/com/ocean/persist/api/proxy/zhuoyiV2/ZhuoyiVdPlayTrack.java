package com.ocean.persist.api.proxy.zhuoyiV2;

import java.util.List;

public class ZhuoyiVdPlayTrack {
	private List<String> unmute;//	optional	array of string	关闭静音事件上报
	private List<String> mute;//	optional	array of string	静音事件上报
	private List<String> videoclose	;//optional	array of string	关闭视频事件上报
	private List<String> error;//	optional	array of string	播放视频错误追踪
	private List<ZhuoyiVdPlayPct> playpercentage;//	optional	array of playpercentage
	public List<String> getUnmute() {
		return unmute;
	}
	public List<String> getMute() {
		return mute;
	}
	public List<String> getVideoclose() {
		return videoclose;
	}
	public List<String> getError() {
		return error;
	}
	public List<ZhuoyiVdPlayPct> getPlaypercentage() {
		return playpercentage;
	}
	public void setUnmute(List<String> unmute) {
		this.unmute = unmute;
	}
	public void setMute(List<String> mute) {
		this.mute = mute;
	}
	public void setVideoclose(List<String> videoclose) {
		this.videoclose = videoclose;
	}
	public void setError(List<String> error) {
		this.error = error;
	}
	public void setPlaypercentage(List<ZhuoyiVdPlayPct> playpercentage) {
		this.playpercentage = playpercentage;
	}
	
}
