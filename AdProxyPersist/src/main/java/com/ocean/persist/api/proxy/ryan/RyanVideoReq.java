package com.ocean.persist.api.proxy.ryan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanVideoReq   {

	private static final long serialVersionUID = -1349245455371708769L;
   private Integer protocol;//recommend,视频响应协议： 101 – iflytek specific video response v1
   private Integer w;//recmmend,视频广告位宽度
   private Integer h;//视频广告位高度
   private Integer minduration;//recmmend,最小视频长度， 单位： 毫秒
   private Integer  maxduration;//recmmend,最大视频长度， 单位： 毫秒
   private Integer  linearity;//recmmend,广告位是否是线性的： 1 – 线性/视频流
   private Object  ext;//optional,扩展字段
	public Integer getProtocol() {
		return protocol;
	}
	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public Integer getMinduration() {
		return minduration;
	}
	public void setMinduration(Integer minduration) {
		this.minduration = minduration;
	}
	public Integer getMaxduration() {
		return maxduration;
	}
	public void setMaxduration(Integer maxduration) {
		this.maxduration = maxduration;
	}
	public Integer getLinearity() {
		return linearity;
	}
	public void setLinearity(Integer linearity) {
		this.linearity = linearity;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
}
