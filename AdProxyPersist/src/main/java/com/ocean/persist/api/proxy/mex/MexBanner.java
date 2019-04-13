package com.ocean.persist.api.proxy.mex;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

public class MexBanner  {

	private static final long serialVersionUID = 1L;
	
	private Integer w;// required 	广告位宽度的像素值 
	
	private Integer h;// required 	广告位高度的像素值 
	
	// 包含支持的MIME类型，常见的MIME类型可能包括 
	//  “application/x-shockwave-flash”,
	// “image/jpg”,
	// “image/gif”
	private List<String> mimes; 
	
	// 列出本广告位支持的API框架，如果没有明确列出，则视为不支持任何框架，见附录1 API框架 
	//	1 	VPAID 1.0 
	//	2 	VPAID 2.0 
	//	3 	MRAID v1.0 
	//	4 	ORMMA  
	//	5 	MRAID v2.0 
	//private List<String> api;

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

/*	public List<String> getApi() {
		return api;
	}

	public void setApi(List<String> api) {
		this.api = api;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
