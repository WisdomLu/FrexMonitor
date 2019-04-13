package com.ocean.persist.app.dis.appSearch360T2.pkgSearch;

import java.util.List;

import com.ocean.persist.app.dis.appSearch360T2.BaseSearch360T2Request;

public class PkgSearch360T2Req extends BaseSearch360T2Request{
	private List<String> pnames;

	public List<String> getPnames() {
		return pnames;
	}

	public void setPnames(List<String> pnames) {
		this.pnames = pnames;
	}

}
