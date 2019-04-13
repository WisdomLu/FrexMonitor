package com.ocean.persist.api.proxy.pairui;

import java.util.List;

public class PairuiBidReqVideo {

	// required 素材宽，单位px 
	private Integer w;

	// required 素材高，单位px
	private Integer h;
	
	// required integer 最大播放时长，单位秒
	private Integer maxduration;
	
	// required integer 最短播放时长，单位秒
	private Integer minduration;
	
	// optional integer 时长的误差值，单位为毫秒。例如：
	// 如果允许视频时长是15s，diffduration为±500，那么实际允许
	// 播放时长为14.5s～15.5s
	private Integer diffduration;
	
	// optional  integer 视频支持的协议，
	// 0.其他.1.VAST1.0;2.VAST2.0;3.VAST3.0;4.VAST4.0.
	private Integer protocol;
	
	// required String array 支持的mime类型。详见类别说明表格2
	private List<String> mimes;

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getMaxduration() {
		return maxduration;
	}

	public void setMaxduration(Integer maxduration) {
		this.maxduration = maxduration;
	}

	public Integer getMinduration() {
		return minduration;
	}

	public void setMinduration(Integer minduration) {
		this.minduration = minduration;
	}

	public Integer getDiffduration() {
		return diffduration;
	}

	public void setDiffduration(Integer diffduration) {
		this.diffduration = diffduration;
	}

	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public List<String> getMimes() {
		return mimes;
	}

	public void setMimes(List<String> mimes) {
		this.mimes = mimes;
	}
}
