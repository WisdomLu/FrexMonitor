package com.ocean.persist.app.dis.wanka.pkgsearch;

import com.ocean.persist.app.dis.wanka.WankaAppReqBase;

public class WankaPkgSearchReq    extends WankaAppReqBase{
  private String _package;
	
	public String get_package() {
		return _package;
	}
	
	public void set_package(String _package) {
		this._package = _package;
	}
}
