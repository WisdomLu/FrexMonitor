package com.ocean.persist.api.proxy.ruangaoyun;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunReport     implements  AdPullResponse{
   /**
	 * 
	 */
	private static final long serialVersionUID = 7471922788606087370L;
    private Integer type_mma;
    private String url;
	public Integer getType_mma() {
		return type_mma;
	}
	public void setType_mma(Integer type_mma) {
		this.type_mma = type_mma;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
