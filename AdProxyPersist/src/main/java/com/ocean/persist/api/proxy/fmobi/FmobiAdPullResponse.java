package com.ocean.persist.api.proxy.fmobi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiAdPullResponse    extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4601402659839951231L;
	private float version;
	private List<FmobiAdspace> adspace;
	private String extend_data;
	public float getVersion() {
		return version;
	}
	public void setVersion(float version) {
		this.version = version;
	}
	public List<FmobiAdspace> getAdspace() {
		return adspace;
	}
	public void setAdspace(List<FmobiAdspace> adspace) {
		this.adspace = adspace;
	}
	public String getExtend_data() {
		return extend_data;
	}
	public void setExtend_data(String extend_data) {
		this.extend_data = extend_data;
	}
	
	
	
	
}
