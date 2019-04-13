package com.ocean.persist.api.proxy.onemob;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月1日 
      @version 1.0 
 */
public class OnemobCnf   implements AdPullResponse{
   /**
	 * 
	 */
	private static final long serialVersionUID = -5757194348515249898L;
    private OnemobDgfly dgfly;
	public OnemobDgfly getDgfly() {
		return dgfly;
	}
	public void setDgfly(OnemobDgfly dgfly) {
		this.dgfly = dgfly;
	}
}
