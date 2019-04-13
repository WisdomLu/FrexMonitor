package com.ocean.persist.api.proxy.huzhong;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
public class HuzhongImp   implements AdPullResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2479577215994684266L;
	private List<String> type;
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
}
