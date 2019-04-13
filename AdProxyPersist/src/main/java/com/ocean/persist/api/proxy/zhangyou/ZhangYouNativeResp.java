package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;
import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月9日 
      @version 1.0 
 */
public class ZhangYouNativeResp  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2725438467366249945L;
	private List<ZhangYouAssetResp> assets;//原生广告元素列表，当前主要支持 5个元素，分别为标题(title), 图标  (img), 大图 (img), 描述  (data), 得分 (data)
    private List<String> imptracker;//展示跟踪地址数组，需要返回一个1像素图片
    private ZhangYouLinkResp link;//目标链接，默认链接对象，当assets中不包括link对象时，使用此对象
	public List<ZhangYouAssetResp> getAssets() {
		return assets;
	}
	public void setAssets(List<ZhangYouAssetResp> assets) {
		this.assets = assets;
	}
	public List<String> getImptracker() {
		return imptracker;
	}
	public void setImptracker(List<String> imptracker) {
		this.imptracker = imptracker;
	}
	public ZhangYouLinkResp getLink() {
		return link;
	}
	public void setLink(ZhangYouLinkResp link) {
		this.link = link;
	}


}
