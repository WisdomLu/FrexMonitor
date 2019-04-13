package com.ocean.persist.api.proxy.yuansheng;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengActivereport {
	private float time	;//浮点数	Y	客户端收到广告响应后，延时上报秒数，浮点数，一般为0.
	private String url	;//string	Y	上报地址
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
