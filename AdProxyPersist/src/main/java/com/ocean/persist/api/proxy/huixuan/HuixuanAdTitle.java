package com.ocean.persist.api.proxy.huixuan;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdTitle    {
   /**
	 * 
	 */
	private static final long serialVersionUID = 4328134896282694460L;
    private int len;
    
    //response
    private String text;
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
