package com.ocean.persist.api.proxy.adview;

import java.util.List;
import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class AdviewAdContent  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String posId;// 广告位 id 	对应的唯一 ，一个广告位 id 
	private String adi;// 广告交易 ID 	客户端无需处理该字段 
	// 广告动作类型 【目前支持 1 和 2】 	1	=>广告页  2	=>下载  4 =>打开地图 
	// 8 =>发送短信 16 =>发送邮件 32 =>拨打电话 64 =>播放视频 
	private Integer act;
	private String apn;// 广告电话号码【暂不支持】 	18210273929
	private Integer at;// 返回的广告素材类型 	0=>图片横幅广告  1=>混合文字链  2=>图形文字链（带行为图标）  3=>图片插屏广告  5=>开屏纯图片  8=>原生广告  100=>富媒体插屏 
	private String xs;// html 广告代码段 	返回 html 广告时填充该字段 
	private String as;// 广告尺寸 	320x50 
	private String aic;// 广告图标 URL 	图文广告时，图标的 URL 地址 
	private String ate;// 广告文字内容 	图文或文字链广告时，会返回这三个字段。不保证三个字段全部得到填充。 
	private String ati;// 广告标题 	
	private String ast;// 广告副标题 	
	private List<String> api;// 图片广告时，图片的 URL 地址 	[“url1”, “url2”] 
	private String abi;// 广告行为图标 URL 	 
	private String adLogo;// 广告来源 Logo 	logo 图片 URL 
	private String adIcon;// 	“广告”字样，不一定返回，媒体可使用自己更原生的设计来表达 “广告”字样。 	 
	private String dl;// 包含 deeplink 的点击跳转地址，无法打开则使用 al 	搜索终端是否安装对应 App，有则跳转至 App 内容对应界面;无则跳转到普通落地页。 
	private String al;// 点击跳转地址 	跳转落地页，通常是网页或者 APP 下载链接。 
	private int altype;
	private String gdt_conversion_link;
	private String fallback;// 	跳转替换地址。如果 al 链接不支持，使用本地址 	原生广告可能有 
	private List<String> surl;// 追踪下载效果，下载开始 	act=2 且是 Android 应用需要处理该字段。 
	private List<String> furl;// 追踪下载效果，下载完成 	
	private List<String> iurl;// 追踪下载效果，安装完成  	
	private List<String> ourl;// 追踪下载效果，打开应用 	
	private AdviewNative nat;// 信息流广告素材
	private String dan;// 被下载的应用名称 	这四个字段为 Android 视频广告时可能返回的字段 
	private String dai;// 被下载的应用图标 	
	private String dpn;// 被下载应用的包名 	
	private String das;// 被下载应用的大小 	
	private List<String> ec;// 点击监控 URL，需要客户端逐条访问 	["url1","url2", ...] 
	private Map<String, List<String>> es;// 展示监控 URL，需要客户端按延迟逐条访问。 key 为发送曝光监测延迟时间(秒)，value 为曝光监测的 URL 数组。 	{ 
	private Integer adWinPrice;// 广告 CPM 价格。编码格式的 CPM 价格*10000，如价格为 CPM 价格 0.6 元
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public Integer getAct() {
		return act;
	}
	public void setAct(Integer act) {
		this.act = act;
	}
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn = apn;
	}
	
	public AdviewNative getNat() {
		return nat;
	}
	public void setNat(AdviewNative nat) {
		this.nat = nat;
	}
	public Integer getAt() {
		return at;
	}
	public void setAt(Integer at) {
		this.at = at;
	}
	public String getXs() {
		return xs;
	}
	public void setXs(String xs) {
		this.xs = xs;
	}
	public String getAs() {
		return as;
	}
	public void setAs(String as) {
		this.as = as;
	}
	public String getAic() {
		return aic;
	}
	public void setAic(String aic) {
		this.aic = aic;
	}
	public String getAte() {
		return ate;
	}
	public void setAte(String ate) {
		this.ate = ate;
	}
	public String getAti() {
		return ati;
	}
	public void setAti(String ati) {
		this.ati = ati;
	}
	public String getAst() {
		return ast;
	}
	public void setAst(String ast) {
		this.ast = ast;
	}
	public List<String> getApi() {
		return api;
	}
	public void setApi(List<String> api) {
		this.api = api;
	}
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getAdLogo() {
		return adLogo;
	}
	public void setAdLogo(String adLogo) {
		this.adLogo = adLogo;
	}
	public String getAdIcon() {
		return adIcon;
	}
	public void setAdIcon(String adIcon) {
		this.adIcon = adIcon;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public String getAl() {
		return al;
	}
	public void setAl(String al) {
		this.al = al;
	}
	public String getFallback() {
		return fallback;
	}
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	public List<String> getSurl() {
		return surl;
	}
	public void setSurl(List<String> surl) {
		this.surl = surl;
	}
	public List<String> getFurl() {
		return furl;
	}
	public void setFurl(List<String> furl) {
		this.furl = furl;
	}
	public List<String> getIurl() {
		return iurl;
	}
	public void setIurl(List<String> iurl) {
		this.iurl = iurl;
	}
	public List<String> getOurl() {
		return ourl;
	}
	public void setOurl(List<String> ourl) {
		this.ourl = ourl;
	}
	public String getDan() {
		return dan;
	}
	public void setDan(String dan) {
		this.dan = dan;
	}
	public String getDai() {
		return dai;
	}
	public void setDai(String dai) {
		this.dai = dai;
	}
	public String getDpn() {
		return dpn;
	}
	public void setDpn(String dpn) {
		this.dpn = dpn;
	}
	public String getDas() {
		return das;
	}
	public void setDas(String das) {
		this.das = das;
	}
	public List<String> getEc() {
		return ec;
	}
	public void setEc(List<String> ec) {
		this.ec = ec;
	}
	public Map<String, List<String>> getEs() {
		return es;
	}
	public void setEs(Map<String, List<String>> es) {
		this.es = es;
	}
	public Integer getAdWinPrice() {
		return adWinPrice;
	}
	public void setAdWinPrice(Integer adWinPrice) {
		this.adWinPrice = adWinPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getAltype() {
		return altype;
	}
	public void setAltype(int altype) {
		this.altype = altype;
	}
	public String getGdt_conversion_link() {
		return gdt_conversion_link;
	}
	public void setGdt_conversion_link(String gdt_conversion_link) {
		this.gdt_conversion_link = gdt_conversion_link;
	}
}
