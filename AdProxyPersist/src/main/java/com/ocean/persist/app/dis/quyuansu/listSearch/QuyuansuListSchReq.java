package com.ocean.persist.app.dis.quyuansu.listSearch;

import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuReq;

public class QuyuansuListSchReq  extends BaseQuyuansuReq  implements AdDisParams{

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
