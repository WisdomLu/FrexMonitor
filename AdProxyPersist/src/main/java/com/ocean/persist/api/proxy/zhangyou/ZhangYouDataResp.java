package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月10日 
      @version 1.0 
 */
public class ZhangYouDataResp  {
  /**
	 * 
	 */
	private static final long serialVersionUID = 8531288082785210777L;
    private String label;
    private String value;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
