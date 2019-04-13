package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneAdPullResponse  extends AbstractBaseEntity  implements  AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932669379188018493L;
    private String ver;
    private List<RedStoneNativeAssetResp> assets;
    private RedStoneNativeLink link;
    private List<String> imptrackers;
    private String jstracker;
    private RedStoneExt ext;
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public List<RedStoneNativeAssetResp> getAssets() {
		return assets;
	}
	public void setAssets(List<RedStoneNativeAssetResp> assets) {
		this.assets = assets;
	}
	public RedStoneNativeLink getLink() {
		return link;
	}
	public void setLink(RedStoneNativeLink link) {
		this.link = link;
	}
	public List<String> getImptrackers() {
		return imptrackers;
	}
	public void setImptrackers(List<String> imptrackers) {
		this.imptrackers = imptrackers;
	}
	public String getJstracker() {
		return jstracker;
	}
	public void setJstracker(String jstracker) {
		this.jstracker = jstracker;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
