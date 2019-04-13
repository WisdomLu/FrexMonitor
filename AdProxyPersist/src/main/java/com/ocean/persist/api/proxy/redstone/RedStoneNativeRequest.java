package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeRequest  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6555044163711713418L;
	private String ver;
	private Integer layout;
	private Integer adunit;
	private Integer plcmtcnt;
	private Integer seq;
	private List<RedStoneNativeAsset> assets;
	private RedStoneExt ext;
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public Integer getLayout() {
		return layout;
	}
	public void setLayout(Integer layout) {
		this.layout = layout;
	}
	public Integer getAdunit() {
		return adunit;
	}
	public void setAdunit(Integer adunit) {
		this.adunit = adunit;
	}
	public Integer getPlcmtcnt() {
		return plcmtcnt;
	}
	public void setPlcmtcnt(Integer plcmtcnt) {
		this.plcmtcnt = plcmtcnt;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public List<RedStoneNativeAsset> getAssets() {
		return assets;
	}
	public void setAssets(List<RedStoneNativeAsset> assets) {
		this.assets = assets;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
	
    
}
