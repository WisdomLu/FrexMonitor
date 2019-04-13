package com.ocean.persist.app.dis.wxcpd;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

public class WxAppResponse  extends AbstractBaseEntity implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4787423149596063369L;
	
		private int code;//": 200,
		private String msg;//": "success",
		private WxAppRespData data;
		
		
		//透传
		private WxAppPullParams param;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public WxAppRespData getData() {
			return data;
		}
		public void setData(WxAppRespData data) {
			this.data = data;
		}
		public WxAppPullParams getParam() {
			return param;
		}
		public void setParam(WxAppPullParams param) {
			this.param = param;
		}
}
