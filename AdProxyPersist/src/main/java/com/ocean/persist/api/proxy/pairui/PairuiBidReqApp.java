package com.ocean.persist.api.proxy.pairui;

public class PairuiBidReqApp {

	// required String app的包名。
	// Android为包名（package_name），IOS为App Store中的ID
	// 或包名。
	private String bundle;
	
	// optional  String app的名称
	private String name;
	
	// optional  String app的类别，详见类别说明表格1。
	// 注：提供2级分类即可
	private String cat;
	
	// optional object 媒体当前展示内容（Content）对象
	private PairuiBidReqContent content;

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
