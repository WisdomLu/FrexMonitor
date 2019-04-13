package com.ocean.persist.api.proxy.onemob;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class OnemobAdResponse  extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5428449331028528922L;
	private OnemobCnf cnf;
	public OnemobCnf getCnf() {
		return cnf;
	}
	public void setCnf(OnemobCnf cnf) {
		this.cnf = cnf;
	}
	

}
