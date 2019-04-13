package com.ocean.persist.app.dis.yitong.applist;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class ListSearchYitongResp_v2  implements AppDisResponse {
	
		private String msg;//":"获取成功",
		private int code;//":0
		private List<ListSearchYitongApp_v2> data;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public List<ListSearchYitongApp_v2> getData() {
			return data;
		}
		public void setData(List<ListSearchYitongApp_v2> data) {
			this.data = data;
		}
		
}
