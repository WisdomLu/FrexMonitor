package com.ocean.persist.api.proxy.speed;
import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class SpeedTrack   implements AdPullResponse{
  	    /**
	 * 
	 */
	private static final long serialVersionUID = -3596989104515123634L;
	private List<String> downloadstart;//下载开始汇报地址
    private List<String> downloadfinish;//下载完汇报地址
    private List<String> installfinish;//安装完成汇报地址
    private String eventfinish;//自定义事件汇报
    private List<String> playstart;//视频开始播放汇报
	public List<String> getDownloadstart() {
		return downloadstart;
	}
	public void setDownloadstart(List<String> downloadstart) {
		this.downloadstart = downloadstart;
	}
	public List<String> getDownloadfinish() {
		return downloadfinish;
	}
	public void setDownloadfinish(List<String> downloadfinish) {
		this.downloadfinish = downloadfinish;
	}
	public List<String> getInstallfinish() {
		return installfinish;
	}
	public void setInstallfinish(List<String> installfinish) {
		this.installfinish = installfinish;
	}
	public String getEventfinish() {
		return eventfinish;
	}
	public void setEventfinish(String eventfinish) {
		this.eventfinish = eventfinish;
	}
	public List<String> getPlaystart() {
		return playstart;
	}
	public void setPlaystart(List<String> playstart) {
		this.playstart = playstart;
	}
}
