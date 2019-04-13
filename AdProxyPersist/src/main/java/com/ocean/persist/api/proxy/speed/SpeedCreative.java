package com.ocean.persist.api.proxy.speed;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
public class SpeedCreative   implements AdPullResponse{
	    /**
	 * 
	 */
	private static final long serialVersionUID = -5248405217367580390L;
	private List<String> impression;//曝光汇报地址
    private List<String> click;//点击上报地址
    private SpeedTrack track;//其他监测对象
    private SpeedMedia media;//素材对象
	public List<String> getImpression() {
		return impression;
	}
	public void setImpression(List<String> impression) {
		this.impression = impression;
	}
	public List<String> getClick() {
		return click;
	}
	public void setClick(List<String> click) {
		this.click = click;
	}
	public SpeedTrack getTrack() {
		return track;
	}
	public void setTrack(SpeedTrack track) {
		this.track = track;
	}
	public SpeedMedia getMedia() {
		return media;
	}
	public void setMedia(SpeedMedia media) {
		this.media = media;
	}

}
