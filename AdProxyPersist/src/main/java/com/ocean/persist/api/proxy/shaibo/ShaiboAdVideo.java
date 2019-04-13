package com.ocean.persist.api.proxy.shaibo;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
public class ShaiboAdVideo    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 17564135620723885L;
	private int type 	;//int 	视频类型,开屏视屏(901)。
	private int  duration 	;//int 	视频时长(秒)。
	private double bitrate 	;//double 	视频比特率(kbps)。
	private String vurl 	;//string 	视频链接地址。
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getBitrate() {
		return bitrate;
	}
	public void setBitrate(double bitrate) {
		this.bitrate = bitrate;
	}
	public String getVurl() {
		return vurl;
	}
	public void setVurl(String vurl) {
		this.vurl = vurl;
	}
}
