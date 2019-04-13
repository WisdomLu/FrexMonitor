package com.ocean.persist.app.dis.wanka.appasyn;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

public class WankaAsynResponse  extends AbstractBaseEntity  implements AppDisResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -18750355611963214L;

	
		private int result;
		private String msg;
		private WankaAppasynContent content;
		public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public WankaAppasynContent getContent() {
			return content;
		}
		public void setContent(WankaAppasynContent content) {
			this.content = content;
		}

}
