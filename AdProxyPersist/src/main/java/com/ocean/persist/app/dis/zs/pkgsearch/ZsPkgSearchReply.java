package com.ocean.persist.app.dis.zs.pkgsearch;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class ZsPkgSearchReply implements AppDisResponse {
	private List<ZsPkgSearchApp> list;

	public List<ZsPkgSearchApp> getList() {
		return list;
	}

	public void setList(List<ZsPkgSearchApp> list) {
		this.list = list;
	}

}
