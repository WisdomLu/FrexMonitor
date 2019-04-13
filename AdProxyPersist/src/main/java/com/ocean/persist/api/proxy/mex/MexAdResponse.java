package com.ocean.persist.api.proxy.mex;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class MexAdResponse   extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private Integer status;// 错误码，见附录4 错误码 
	// 对publisher，我们宣称它是唯一标识此次展示的id，可以用于追踪；实际上这个id就是我们用于追踪的bidrequestid 
	private String id;
	private String adposid;// 广告位ID 
	// 创意数据，可能是一段HTML代码（定义图片、富媒体等创意）、一个native对象、一段遵循 VAST协议的的XML数据
	private String adm; 
	private String creative_url;// 素材图片地址；仅当不使用adm传输创意数据时，才使用本字段
	private String curl;// 点击地址；仅当使用creative_url传输创意时，才使用本字段
	private List<String> imp_url;// 展示上报地址，可有多个 
	private List<String> click_url;// 点击上报地址，可有多个 
	private List<String> load_url;// （非必须）素材加载完成上报地址，可有多个 
	private String cta;// 点击事件类型（目前仅支持下载应用）：  0：打开网页； 1：下载应用； 

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdposid() {
		return adposid;
	}

	public void setAdposid(String adposid) {
		this.adposid = adposid;
	}

	public String getCreative_url() {
		return creative_url;
	}

	public void setCreative_url(String creative_url) {
		this.creative_url = creative_url;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public List<String> getImp_url() {
		return imp_url;
	}

	public void setImp_url(List<String> imp_url) {
		this.imp_url = imp_url;
	}

	public List<String> getClick_url() {
		return click_url;
	}

	public void setClick_url(List<String> click_url) {
		this.click_url = click_url;
	}

	public List<String> getLoad_url() {
		return load_url;
	}

	public void setLoad_url(List<String> load_url) {
		this.load_url = load_url;
	}

	public String getCta() {
		return cta;
	}

	public void setCta(String cta) {
		this.cta = cta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAdm() {
		return adm;
	}

	public void setAdm(String adm) {
		this.adm = adm;
	}
}
