package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeLink    implements  AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2896632551200351004L;
    private String url;
    private List<String> clicktrackers;
    private String fallback;
    private RedStoneExt ext;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getClicktrackers() {
		return clicktrackers;
	}
	public void setClicktrackers(List<String> clicktrackers) {
		this.clicktrackers = clicktrackers;
	}
	public String getFallback() {
		return fallback;
	}
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
