package com.ocean.persist.api.proxy.ryan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanBannerReq   {
   /**
	 * 
	 */
	private static final long serialVersionUID = -8853683633865944163L;
   private Integer type;//required,banner 时指定： 横幅 –1 全屏 – 2 插屏 – 3
   private Integer w;//required,广告位宽度，单位像素
   private Integer h;//required,广告位高度，单位像素
   private Object ext;//拓展信息
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
   
}
