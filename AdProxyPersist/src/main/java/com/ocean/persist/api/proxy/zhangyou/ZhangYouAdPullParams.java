package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouAdPullParams   extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4091337120982048636L;
    private String ver;//协议版本,当前版本号『 1.1』
    private String ssp_token;//ssp token,从zplay adx获得
    private int is_test;//是否为测试标识，如果则只发给dsp dsp， 0表示不是测试， 1表示测试， 默认 不
    private int is_tail;//是否为 尾量的标志，默认是否为 尾量的标志，默认0，0表示正常量， 1表示为尾量， 2标识为正常量但不走 api(api(api(api(针对联想 ), 3), 3), 3), 3标 识为尾量但也不走 api(api( api(针对联想 )
    private int need_https;//是否需要 https链接的标 识，默认为 0，0标识不需要， 1标识需要。当为 标识需要。当为 1时，需要返回的所有素材及追 踪链接必须是 https 链接
    private String sdk_ver;//zplay 自有广告 sdk 版本号
    private ZhangYouApp app;//
    private ZhangYouSite site;
    private ZhangYouDevice device;
    private ZhangYouUser user;
    private ZhangYouAdReq ad;//广告信息， 保留此元素是为了持向后兼容，只存在于协议版本 1.0（包括）之前。从 1.1（包括）以后， 使用 ads
    private List<ZhangYouAdReq> ads;
    private ZhangYouZPlayReq zplay;//自主系统信息，zplay内部使用
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getSsp_token() {
		return ssp_token;
	}
	public void setSsp_token(String ssp_token) {
		this.ssp_token = ssp_token;
	}
	public int getIs_test() {
		return is_test;
	}
	public void setIs_test(int is_test) {
		this.is_test = is_test;
	}
	public int getIs_tail() {
		return is_tail;
	}
	public void setIs_tail(int is_tail) {
		this.is_tail = is_tail;
	}
	public int getNeed_https() {
		return need_https;
	}
	public void setNeed_https(int need_https) {
		this.need_https = need_https;
	}
	public String getSdk_ver() {
		return sdk_ver;
	}
	public void setSdk_ver(String sdk_ver) {
		this.sdk_ver = sdk_ver;
	}
	public ZhangYouApp getApp() {
		return app;
	}
	public void setApp(ZhangYouApp app) {
		this.app = app;
	}
	public ZhangYouSite getSite() {
		return site;
	}
	public void setSite(ZhangYouSite site) {
		this.site = site;
	}
	public ZhangYouDevice getDevice() {
		return device;
	}
	public void setDevice(ZhangYouDevice device) {
		this.device = device;
	}
	public ZhangYouUser getUser() {
		return user;
	}
	public void setUser(ZhangYouUser user) {
		this.user = user;
	}
	public ZhangYouAdReq getAd() {
		return ad;
	}
	public void setAd(ZhangYouAdReq ad) {
		this.ad = ad;
	}
	public List<ZhangYouAdReq> getAds() {
		return ads;
	}
	public void setAds(List<ZhangYouAdReq> ads) {
		this.ads = ads;
	}
	public ZhangYouZPlayReq getZplay() {
		return zplay;
	}
	public void setZplay(ZhangYouZPlayReq zplay) {
		this.zplay = zplay;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
    
    
}
