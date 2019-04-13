package com.ocean.persist.app.dis.wanka.pkgsearch;
import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

public class WankaPkgResponse  extends AbstractBaseEntity  implements AppDisResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -18750355611963214L;

	
		private int result;
		private String msg;
		private WankaPkgContet content;
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
		public WankaPkgContet getContent() {
			return content;
		}
		public void setContent(WankaPkgContet content) {
			this.content = content;
		}
}
