package com.ocean.persist.app.dis.wanka;

import java.util.List;

public class WankaReportData {
	private String from_update ;/*自动更新下载请加上此参数并赋值为1
	 请求方式：POST
	 请求参数说明（发送的数据为json 数据）：
	参数格式示例*/
	private List<String> reportData;//
	private String sign ;//具体见数据传输加密部分
	private String requestId ;//搜索结果返回的requestId(可选),如果从搜索结果变现,此参数必须)
	private String urlParams;
	public String getFrom_update() {
		return from_update;
	}
	public List<String> getReportData() {
		return reportData;
	}
	public String getSign() {
		return sign;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setFrom_update(String from_update) {
		this.from_update = from_update;
	}
	public void setReportData(List<String> reportData) {
		this.reportData = reportData;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUrlParams() {
		return urlParams;
	}
	public void setUrlParams(String urlParams) {
		this.urlParams = urlParams;
	}
}
