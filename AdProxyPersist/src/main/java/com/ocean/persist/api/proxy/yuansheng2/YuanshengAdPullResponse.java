package com.ocean.persist.api.proxy.yuansheng2;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237153099455323411L;
	private int code ;
	private String msg ;	
	private List<YuanshengImp> imp;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<YuanshengImp> getImp() {
		return imp;
	}
	public void setImp(List<YuanshengImp> imp) {
		this.imp = imp;
	}

}
