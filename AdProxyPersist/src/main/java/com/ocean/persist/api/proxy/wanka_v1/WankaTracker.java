package com.ocean.persist.api.proxy.wanka_v1;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月1日 
      @version 1.0 
 */
public class WankaTracker {
	private int template_type;
	private int http_method;
	private String url;
	private String content;
	public int getTemplate_type() {
		return template_type;
	}
	public void setTemplate_type(int template_type) {
		this.template_type = template_type;
	}
	public int getHttp_method() {
		return http_method;
	}
	public void setHttp_method(int http_method) {
		this.http_method = http_method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
