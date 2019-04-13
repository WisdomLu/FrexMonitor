package com.ocean.persist.api.proxy.zhongcheng;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class ZhongchengAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private Integer res_code;// Int	返回状态码，处理正常：200 处理异常：404   参数缺失：500
	private String msg_cn;// 请求状态说明，
	// 是	String	1表示落地页为普通页面(href)采用浏览器打开；
	//落地方式，redirect 为网址，download 为下载 
	private String adtype;
	private String ma;//
	private String close_icon;
	private String reptype;// //目前仅 html 方式
	private String rtp;// Int	是否，1表示需要上报用户点击坐标，0表示不需要。详情见附录A
	private List<String> impr_url;// Array	展示上报
	private List<String> click_url;// array	点击上报
	private List<ZhongchengNativeCreative> batch_body;// 原生广告物料信息
	
	public Integer getRes_code() {
		return res_code;
	}

	public void setRes_code(Integer res_code) {
		this.res_code = res_code;
	}

	public String getMsg_cn() {
		return msg_cn;
	}

	public void setMsg_cn(String msg_cn) {
		this.msg_cn = msg_cn;
	}

	public String getAdtype() {
		return adtype;
	}

	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getClose_icon() {
		return close_icon;
	}

	public void setClose_icon(String close_icon) {
		this.close_icon = close_icon;
	}

	public String getReptype() {
		return reptype;
	}

	public void setReptype(String reptype) {
		this.reptype = reptype;
	}

	public String getRtp() {
		return rtp;
	}

	public void setRtp(String rtp) {
		this.rtp = rtp;
	}

	public List<String> getImpr_url() {
		return impr_url;
	}

	public void setImpr_url(List<String> impr_url) {
		this.impr_url = impr_url;
	}

	public List<String> getClick_url() {
		return click_url;
	}

	public void setClick_url(List<String> click_url) {
		this.click_url = click_url;
	}

	public List<ZhongchengNativeCreative> getBatch_body() {
		return batch_body;
	}

	public void setBatch_body(List<ZhongchengNativeCreative> batch_body) {
		this.batch_body = batch_body;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
