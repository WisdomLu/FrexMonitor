package com.ocean.persist.app.dis.qqDownloader.getCategoryList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class TagItem    implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3680202825471787342L;
    private long id;//聚合标签Id（调用getAppList类型6时先调该接口）
    private String name;//聚合标签名
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
