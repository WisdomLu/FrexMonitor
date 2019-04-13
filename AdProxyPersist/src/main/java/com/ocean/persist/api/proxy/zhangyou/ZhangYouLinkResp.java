package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月10日 
      @version 1.0 
 */
public class ZhangYouLinkResp  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3709940158591345368L;
	private String url;
    private List<String> clicktracker;
    private int type;//点击动作类型，  1: 在 app 内 webview打开目标链接 2： 在系统浏览器打开目标链接 , 3 ：打开地图， 4： 拨打电话， 5：播放视频 , 6:App
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getClicktracker() {
		return clicktracker;
	}
	public void setClicktracker(List<String> clicktracker) {
		this.clicktracker = clicktracker;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
