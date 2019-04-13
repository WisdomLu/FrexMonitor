package com.ocean.persist.api.proxy.ljpc;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class LJPCAdPullParams    extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;
	
	// 请求来源ip，目前我们取一个ip，来自http headers，
	// 优先级为X-Forwarded-For > X-Real-IP > RemoteAddress，
	// 取第一个合法的ip，需要发送请求的server先获取ip	是	127.0.0.1
	private String ip;
	private String ua;// 用户请求的User-Agent header
	private String uid;// 用户id，如xtid等，需要urlencode	是	zzzzzzzzzzzz
	private String l;// 广告位id(灵集投放平台唯一合作标识符)	是	1
	// 返回值类型，目前支持三种情况： 1）v=0为空或者0表示是RTB协议的Json返回类型；
	// 2）v=1表示是Vast 3.0格式的返回类型	可选	1
	private Integer v;
	// 用户请求的来源页面url，用于转发给dsp，需要urlencode	可选	http://ref.example.com
	private String f;
	// 广告所在页面url，用于进行域名验证和转发给dsp，需要urlencode	
	// 对banner类型的广告且配置了验证的网站是必须的	http://current.example.com
	private String u;
	private Integer min;// 视频广告最小时长，单位秒	可选，广告位也可以在网站上设置时长最小值	3
	private Integer max;// 视频广告最大时长，单位秒	可选，广告位也可以在网站上设置时长最小值	5
	private String r;// 版本号	是	目前都是1
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public Integer getV() {
		return v;
	}

	public void setV(Integer v) {
		this.v = v;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
