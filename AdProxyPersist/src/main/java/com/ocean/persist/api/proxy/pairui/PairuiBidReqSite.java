package com.ocean.persist.api.proxy.pairui;

public class PairuiBidReqSite {

	// required String 当前页的url
	private String pageurl;
	
	// optional  String 当前页面的前导url
	private String referrer;
	
	// optional String  网站所属类别，详见类别说明表格1
	private String cat;
	
	// optional object 媒体当前展示内容（Content）对象
	private PairuiBidReqContent content;

	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public PairuiBidReqContent getContent() {
		return content;
	}

	public void setContent(PairuiBidReqContent content) {
		this.content = content;
	}
}
