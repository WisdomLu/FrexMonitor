package com.ocean.persist.api.proxy.yuansheng;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengNative extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3436617221225687202L;
	public int getAdtype() {
		return adtype;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	public YuanshengSession getSession() {
		return session;
	}
	public void setSession(YuanshengSession session) {
		this.session = session;
	}
	/**
	 * 
	 */
	private int adtype	;//integer	Y	native广告类型，向我司申请	广告位ID
	private YuanshengSession session;//	object	N	session对象，见3.2.7。	

}
