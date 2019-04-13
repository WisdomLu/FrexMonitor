package com.ocean.persist.api.proxy.maiguang;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月30日 
      @version 1.0 
 */
public class MaiguangAdpullResponse    extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4956075364920650714L;
	private int resultCode;
	private String msg;
	private List<MaiguangAd> data;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<MaiguangAd> getData() {
		return data;
	}
	public void setData(List<MaiguangAd> data) {
		this.data = data;
	}

}
