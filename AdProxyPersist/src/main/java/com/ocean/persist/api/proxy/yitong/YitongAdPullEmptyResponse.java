package com.ocean.persist.api.proxy.yitong;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月27日 
      @version 1.0 
 */
public class YitongAdPullEmptyResponse extends AbstractYitongResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4170566291551593832L;

	private String clickmonitor;
	private String error;
	private String impressionid;
	private String itemspaceid;
	private String viewmonitor;
	public String getClickmonitor() {
		return clickmonitor;
	}
	public void setClickmonitor(String clickmonitor) {
		this.clickmonitor = clickmonitor;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getImpressionid() {
		return impressionid;
	}
	public void setImpressionid(String impressionid) {
		this.impressionid = impressionid;
	}
	public String getItemspaceid() {
		return itemspaceid;
	}
	public void setItemspaceid(String itemspaceid) {
		this.itemspaceid = itemspaceid;
	}
	public String getViewmonitor() {
		return viewmonitor;
	}
	public void setViewmonitor(String viewmonitor) {
		this.viewmonitor = viewmonitor;
	}
}
