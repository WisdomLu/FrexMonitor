package com.ocean.persist.app.dis.wxcpd;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

public class WxApplistResponse  extends AbstractBaseEntity  implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3759720752743309916L;
	
		private int code;//:200,
		private String msg;//":"success",
		private List<WxAppParamData> data;

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
		public List<WxAppParamData> getData() {
			return data;
		}
		public void setData(List<WxAppParamData> data) {
			this.data = data;
		}
	
}
