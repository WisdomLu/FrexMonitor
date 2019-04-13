package com.ocean.persist.app.dis.quyuansu.hotwordSearch;

import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuReq;

public class QuyuansuHotwdSchReq  extends BaseQuyuansuReq  implements AdDisParams{
	private int num;
	private int page;
	private String tag;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public int getNum() {
		return num;
	}
	public int getPage() {
		return page;
	}
	public String getTag() {
		return tag;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
