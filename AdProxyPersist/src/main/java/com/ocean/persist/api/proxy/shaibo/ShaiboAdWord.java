package com.ocean.persist.api.proxy.shaibo;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
public class ShaiboAdWord    implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2271285371852277470L;
	private int type 	;//int 	文字类型,标题(1),广告语(2),广告描述(3)。
	private String text 	;//string 	文字内容。
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
