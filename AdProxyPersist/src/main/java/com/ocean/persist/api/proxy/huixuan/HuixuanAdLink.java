package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdLink    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6455707227995273686L;
	private String clkurl;
	private String clkurl302;
	private List<String>  clktrackers;
	private String fallback;
	private String intro_url;
	private int action_type;
	public String getClkurl() {
		return clkurl;
	}
	public void setClkurl(String clkurl) {
		this.clkurl = clkurl;
	}
	public String getClkurl302() {
		return clkurl302;
	}
	public void setClkurl302(String clkurl302) {
		this.clkurl302 = clkurl302;
	}
	public List<String> getClktrackers() {
		return clktrackers;
	}
	public void setClktrackers(List<String> clktrackers) {
		this.clktrackers = clktrackers;
	}
	public String getFallback() {
		return fallback;
	}
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	public String getIntro_url() {
		return intro_url;
	}
	public void setIntro_url(String intro_url) {
		this.intro_url = intro_url;
	}
	public int getAction_type() {
		return action_type;
	}
	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}

}
