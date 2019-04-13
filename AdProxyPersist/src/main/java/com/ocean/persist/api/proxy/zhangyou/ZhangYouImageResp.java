package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月10日 
      @version 1.0 
 */
public class ZhangYouImageResp    {
   /**
	 * 
	 */
	private static final long serialVersionUID = 8693617577857261465L;
   private String url;
   private int w;
   private int h;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
   
}
