package com.ocean.persist.app.dis.quyuansu.keywordSearch;

import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuReq;

public class QuyuansuKwdSchReq extends BaseQuyuansuReq  implements AdDisParams{
	private String searchword;
	private int num;
	private int page;
	public String getSearchword() {
		return searchword;
	}
	public int getNum() {
		return num;
	}
	public int getPage() {
		return page;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
