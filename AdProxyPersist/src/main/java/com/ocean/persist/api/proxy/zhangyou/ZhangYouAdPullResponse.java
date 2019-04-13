package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月9日 
      @version 1.0 
 */
public class ZhangYouAdPullResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5752743309637879802L;
    private int result;//返回结果， 0：成功，小于 0表
    private String msg;//失败的话，内有原因 ,例： “网络错误 ”
    private ZhangYouAdResp ad;//如果失败 ,或者无对应广告则此数据
    private List<ZhangYouAdResp> ads;//如果失败,或者无对应广告则无此数据
    private String cur;//广告价格货币类型，默认为"CNY"
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public ZhangYouAdResp getAd() {
		return ad;
	}
	public void setAd(ZhangYouAdResp ad) {
		this.ad = ad;
	}
	public List<ZhangYouAdResp> getAds() {
		return ads;
	}
	public void setAds(List<ZhangYouAdResp> ads) {
		this.ads = ads;
	}
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
}
