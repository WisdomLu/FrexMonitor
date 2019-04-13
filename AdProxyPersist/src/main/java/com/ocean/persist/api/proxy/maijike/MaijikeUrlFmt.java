package com.ocean.persist.api.proxy.maijike;

public class MaijikeUrlFmt {
    private String url;//":"http://www.sina.com/ic",
    private MaijikeMacro replace;
	public String getUrl() {
		return url;
	}
	public MaijikeMacro getReplace() {
		return replace;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setReplace(MaijikeMacro replace) {
		this.replace = replace;
	}
}
