package com.ocean.persist.api.proxy.yuansheng;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengImp extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5118374462695329853L;
	private String id;
	private YuanshengNative _native;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public YuanshengNative get_native() {
		return _native;
	}
	public void set_native(YuanshengNative _native) {
		this._native = _native;
	}
}
