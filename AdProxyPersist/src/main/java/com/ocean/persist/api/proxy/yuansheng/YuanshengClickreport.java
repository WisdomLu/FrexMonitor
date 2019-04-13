package com.ocean.persist.api.proxy.yuansheng;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengClickreport {
	private float time;//	float	Y	客户端收到广告响应后，延时上报秒数，一般为0。
	private String url	;//string	Y	上报地址
	private int hasckp;//	integer	N	url是否包含clickpixels宏，0 = 否，1 = 是。
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
	public int getHasckp() {
		return hasckp;
	}
	public void setHasckp(int hasckp) {
		this.hasckp = hasckp;
	}

}
