package com.ocean.persist.api.proxy.fmobi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiRespBody  extends AbstractBaseEntity  implements AdPullResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3786273476117849768L;
	private int status;
    private String message;
    private FmobiAdPullResponse data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public FmobiAdPullResponse getData() {
		return data;
	}
	public void setData(FmobiAdPullResponse data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
