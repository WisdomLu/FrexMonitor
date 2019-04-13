package com.ocean.persist.api.proxy.boyuV2;

import java.util.List;

public class BoyuV2Tracking {
/*	事件类型：1:start 视频播放开始；2：	
	firstQuartile视频播放至25%时，3：	*/
	private int eventType;//	int		midpoint，视频播放50%时，4：	
/*	thirdQuartile，视频播放至75%时，5：end，	
	视频播放完成时，6：mute，视频静音时上报，	
	7：skip：跳过视频时，8：close关闭视频时	*/
	private List<String> eventTrackUrls;//	list		事件监测url链接列表	
	public int getEventType() {
		return eventType;
	}
	public List<String> getEventTrackUrls() {
		return eventTrackUrls;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public void setEventTrackUrls(List<String> eventTrackUrls) {
		this.eventTrackUrls = eventTrackUrls;
	}
}
