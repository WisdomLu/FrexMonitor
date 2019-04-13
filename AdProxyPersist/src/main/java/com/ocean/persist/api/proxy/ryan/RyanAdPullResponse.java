package com.ocean.persist.api.proxy.ryan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/**DSP 正常出价响应包的返回状态码为 HTTP 200 ，决定不出价的则为 204 无内容。
 * 其 他情况会当做出错处理，如 403 forbidden 或 500 integerernal server error。
 * 注： DSP 决定不出价时，需要返回的状态码是 204，而不是 200。 
 * * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanAdPullResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = -3904265781171414483L;
    private String id;//required,由 dsp 回填 Bidrequest 的唯一标 识
    private String  bidid;//required,由 DSP 给出的该次竞价的Bidresponse 的唯一标识
    private List<RyanSeatbid> seatbid;//required,DSP 投标信息
    private String cur;//optional,价格单位， USD,CNY,默认 CNY
    private Object ext;//拓展字段
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBidid() {
		return bidid;
	}
	public void setBidid(String bidid) {
		this.bidid = bidid;
	}
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
	public List<RyanSeatbid> getSeatbid() {
		return seatbid;
	}
	public void setSeatbid(List<RyanSeatbid> seatbid) {
		this.seatbid = seatbid;
	}
}
