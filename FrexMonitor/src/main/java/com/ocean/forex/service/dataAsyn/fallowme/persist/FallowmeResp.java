package com.ocean.forex.service.dataAsyn.fallowme.persist;

import com.ocean.forex.service.dataAsyn.IDataAsynResponse;

public class FallowmeResp implements IDataAsynResponse{
	
	    private int code;
	    private FallowmeData data;
		public int getCode() {
			return code;
		}
		public FallowmeData getData() {
			return data;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public void setData(FallowmeData data) {
			this.data = data;
		}
	
}
