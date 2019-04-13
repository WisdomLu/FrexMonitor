package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouNativeReq  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3884604270955082470L;
    private int layout ;//原生广告类型， 1: 内容墙 , 2: 应用墙 , 3:新闻流， 4：聊天列表， 5：走马灯广告， 6：内容流， 7：矩阵
    private List<ZhangYouAssetReq> assets;//原生广告元素列表，当前 有 5种元素，分别为标题 (title), (title), Icon(img), Large image(img), Description (data), Rating (data)
	public int getLayout() {
		return layout;
	}
	public void setLayout(int layout) {
		this.layout = layout;
	}
	public List<ZhangYouAssetReq> getAssets() {
		return assets;
	}
	public void setAssets(List<ZhangYouAssetReq> assets) {
		this.assets = assets;
	}
    
    
    
}
