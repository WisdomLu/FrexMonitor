package com.ocean.persist.app.dis.daluoma.pkgSearch;

import java.util.List;

import com.ocean.persist.app.dis.daluoma.BaseDaluomaRequest;

public class DaluomaPkgSearchReq extends BaseDaluomaRequest{
	private List<String> pnames;

	public List<String> getPnames() {
		return pnames;
	}

	public void setPnames(List<String> pnames) {
		this.pnames = pnames;
	}

}
