package com.ocean.persist.app.dis.baiduAppSearch.keywordSearch;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class KeywordSearchBaiduRespose  implements AppDisResponse{
	private String statuscode;
	private String statusmessage;
	private List<KeywordSearchBaiduResult> result;
	public String getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	public String getStatusmessage() {
		return statusmessage;
	}
	public void setStatusmessage(String statusmessage) {
		this.statusmessage = statusmessage;
	}
	public List<KeywordSearchBaiduResult> getResult() {
		return result;
	}
	public void setResult(List<KeywordSearchBaiduResult> result) {
		this.result = result;
	}
}
