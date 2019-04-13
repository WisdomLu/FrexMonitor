package com.ocean.persist.api.proxy.shaibo;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
public class ShaiboAdPullResponse    extends AbstractBaseEntity implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6829977128528940524L;
	private int code;// 	int 	状态码,200:成功,400:请求格式错误,204:无广告返回。
	private String msg; 	//string 	附加消息。
	private List<ShaiboAdImp> imp ;//	list of dict 	广告信息数组。
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
	public List<ShaiboAdImp> getImp() {
		return imp;
	}
	public void setImp(List<ShaiboAdImp> imp) {
		this.imp = imp;
	}

}
