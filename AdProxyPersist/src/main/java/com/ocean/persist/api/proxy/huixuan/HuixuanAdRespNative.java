package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdRespNative    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7744267048261790299L;
	private List<HuixuanAdRespAssets> assets;
	private 	HuixuanAdLink	link;
	private List<String>		imptrackers;
	public List<HuixuanAdRespAssets> getAssets() {
		return assets;
	}
	public void setAssets(List<HuixuanAdRespAssets> assets) {
		this.assets = assets;
	}
	public HuixuanAdLink getLink() {
		return link;
	}
	public void setLink(HuixuanAdLink link) {
		this.link = link;
	}
	public List<String> getImptrackers() {
		return imptrackers;
	}
	public void setImptrackers(List<String> imptrackers) {
		this.imptrackers = imptrackers;
	}

}
