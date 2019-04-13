package com.ocean.persist.api.proxy.jicheng;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月31日 
      @version 1.0 
 */
public class JichengAdPullResponse extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624403848822163565L;
	private int ret;//	状态码
	private String msg	;//ok代表成功
	private String id;//	每次请求唯一识别码
	private  List<JichengAd> ad_list;//	广告列表
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<JichengAd> getAd_list() {
		return ad_list;
	}
	public void setAd_list(List<JichengAd> ad_list) {
		this.ad_list = ad_list;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	


}
