package com.ocean.persist.app.dis.youyou.pkgSearch;

import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.youyou.YouyouRespData;

public class YouyouPkgSearchRespose implements AppDisResponse {
	    private String resp_status;
	    private String resp_info;
	    private YouyouRespData resp_data;
	    private String errorCode;
		public String getResp_status() {
			return resp_status;
		}
		public void setResp_status(String resp_status) {
			this.resp_status = resp_status;
		}
		public String getResp_info() {
			return resp_info;
		}
		public void setResp_info(String resp_info) {
			this.resp_info = resp_info;
		}
		public YouyouRespData getResp_data() {
			return resp_data;
		}
		public void setResp_data(YouyouRespData resp_data) {
			this.resp_data = resp_data;
		}
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
}
