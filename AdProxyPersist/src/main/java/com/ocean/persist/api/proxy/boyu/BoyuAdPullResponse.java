package com.ocean.persist.api.proxy.boyu;
import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年4月4日 
      @version 1.0 
 */
public class BoyuAdPullResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2766242677741223689L;
	private int code ;/*int 系统响应码，0为正常，其他为异常。详见系统响应码表
	奇点系统广告获取接口 - 5/9*/
	private String message ;//St ring 响应提示信息
	private BoyuData data;// 返回数据，详细字段如下行起描述
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BoyuData getData() {
		return data;
	}
	public void setData(BoyuData data) {
		this.data = data;
	}
}
