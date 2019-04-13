package com.ocean.persist.app.dis.yitong.applist;

import com.ocean.persist.app.dis.AppDisResponse;

public class ListSearchYitongResp  implements AppDisResponse {
	
		private String msg;//":"获取成功",
		private int code;//":0
		private ListSearchYitongData data;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}

		public ListSearchYitongData getData() {
			return data;
		}
		public void setData(ListSearchYitongData data) {
			this.data = data;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		
}
