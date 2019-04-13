package com.ocean.persist.api.proxy.pairui;

import java.util.List;

public class PairuiBidReqNativeImg {

	// required 素材宽，单位px
	private Integer w;

	// required 素材高，单位px
	private Integer h;

	// required 支持的mime类型。详见类别说明表格2
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

	public List<String> getMimes() {
		return mimes;
	}

	public void setMimes(List<String> mimes) {
		this.mimes = mimes;
	}
}
