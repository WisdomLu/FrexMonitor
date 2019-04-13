package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouScreen  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443189527970866738L;
	private int w;
	private int h;
	private int dpi;//像素密度，每英寸像素个数
	private float pxratio;//屏幕物理像素密度
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
	public int getDpi() {
		return dpi;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}
	public float getPxratio() {
		return pxratio;
	}
	public void setPxratio(float pxratio) {
		this.pxratio = pxratio;
	}
	
	
	
  
}
