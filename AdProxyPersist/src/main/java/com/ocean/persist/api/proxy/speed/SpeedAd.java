package com.ocean.persist.api.proxy.speed;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
public class SpeedAd   implements AdPullResponse{
	    /**
	 * 
	 */
	private static final long serialVersionUID = -8474361822801184486L;
	private int aid;//广告位id
    private String source;//广告来源
    private List<SpeedCreative> creative;//广告创意
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<SpeedCreative> getCreative() {
		return creative;
	}
	public void setCreative(List<SpeedCreative> creative) {
		this.creative = creative;
	}
	  
}
