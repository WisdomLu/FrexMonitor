package com.ocean.persist.api.proxy.jicheng;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月31日 
      @version 1.0 
 */
public class JichengExt {
	private String dsp;//	dsp类型
	private String dsp_log;//	曝光地址 曝光广告之后 请访问以下此地址 访问成功返回{"error_no":0,"data":{}}
	private String ad_icon;//	广告字样
	public String getDsp() {
		return dsp;
	}
	public void setDsp(String dsp) {
		this.dsp = dsp;
	}
	public String getDsp_log() {
		return dsp_log;
	}
	public void setDsp_log(String dsp_log) {
		this.dsp_log = dsp_log;
	}
	public String getAd_icon() {
		return ad_icon;
	}
	public void setAd_icon(String ad_icon) {
		this.ad_icon = ad_icon;
	}
}
