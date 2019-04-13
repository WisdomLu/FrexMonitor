package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月9日 
      @version 1.0 
 */
public class ZhangYouVideo   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7115588460913718360L;
    private String url;//视频播放url
    private int play_duration;//视频播放时长， 单位为秒
    private List<String> player_start_trackers;//播放时上报url
    private List<String> player_end_trackers;//目标页展示上报url，与imp_trackers效果相同
    private List<String> target_page_show_trackers;//目标页点击上报url，与click_trackers效果相同
    private List<String> target_page_click_trackers;//目标页点击上报url，与click_trackers效果相同
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPlay_duration() {
		return play_duration;
	}
	public void setPlay_duration(int play_duration) {
		this.play_duration = play_duration;
	}
	public List<String> getPlayer_start_trackers() {
		return player_start_trackers;
	}
	public void setPlayer_start_trackers(List<String> player_start_trackers) {
		this.player_start_trackers = player_start_trackers;
	}
	public List<String> getPlayer_end_trackers() {
		return player_end_trackers;
	}
	public void setPlayer_end_trackers(List<String> player_end_trackers) {
		this.player_end_trackers = player_end_trackers;
	}
	public List<String> getTarget_page_show_trackers() {
		return target_page_show_trackers;
	}
	public void setTarget_page_show_trackers(List<String> target_page_show_trackers) {
		this.target_page_show_trackers = target_page_show_trackers;
	}
	public List<String> getTarget_page_click_trackers() {
		return target_page_click_trackers;
	}
	public void setTarget_page_click_trackers(
			List<String> target_page_click_trackers) {
		this.target_page_click_trackers = target_page_click_trackers;
	}
    
}
