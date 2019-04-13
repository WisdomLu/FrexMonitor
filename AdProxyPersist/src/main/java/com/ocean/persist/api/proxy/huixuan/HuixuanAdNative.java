package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdNative   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 361682785805386386L;
	private int layout;
	private List<HuixuanAdAssets> assets;
	public int getLayout() {
		return layout;
	}
	public void setLayout(int layout) {
		this.layout = layout;
	}
	public List<HuixuanAdAssets> getAssets() {
		return assets;
	}
	public void setAssets(List<HuixuanAdAssets> assets) {
		this.assets = assets;
	}

}
