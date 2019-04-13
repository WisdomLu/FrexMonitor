package com.ocean.persist.api.proxy.ryan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanSeatbid    {
	private static final long serialVersionUID = -6668889671271953636L;
	private List<RyanBid> bid;//required,针对 Imp 投标信息， 目前只支持一 个
    private Object ext;//optional,拓展字段
	public List<RyanBid> getBid() {
		return bid;
	}
	public void setBid(List<RyanBid> bid) {
		this.bid = bid;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
}
