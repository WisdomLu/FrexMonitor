package com.ocean.persist.api.proxy.ruishi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class RuishiAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3947260842311849073L;
	private int status;//	int	是	返回状态码	参见《4 状态码定义》
	private int adt	;//	int	是	广告类型	对应请求中的adt
	private String cid	;//	string	是	物料ID	由vlion分配的创意id
	private int w	;//	int	否	物料宽	物料为原生创意或内容联盟时不填写
	private int h	;//	Int	否	物料高	物料为原生创意或内容联盟时不填写
	private int ctype	;//	int	否	返回创意类型	adt=0/1/2时填写。1-html，2-图片url，3-文字链。不返回该字段可默认为1-html（兼容旧版本）
	private String imgurl	;//	string	否	图片地址	banner/开屏/插屏的图片地址，当 adt=0/1/2 且 ctype=2/3 时填写
	private String ldp	;//	string	否	落地页	banner/开屏/插屏的落地页地址，当 adt=0/1/2 且 ctype=2/3 时填写
	//有些点击监测链接中有点击监测宏，需要媒体替换，不替换将没有收益！详情见下。
	private String adtitle	;//	string	否	banner文字标题	当 adt=0 且 ctype=3 时填写，文字链广告标题
	private String adsubtitle	;//	string	否	banner文字副标题	当 adt=0 且 ctype=3 时填写，文字链广告附标题，与标题以换行分隔
	private String deeplink	;//	string	否	deeplink链接	deeplink链接，如果有deeplink则优先调用，调用时需要上报 conv_tracking 中相应的监测链接。具体逻辑见下方 ”Deeplink调用逻辑”
	private String adm	;//	string	否	广告物料	adt=0/1/2 且 ctype=1（或ctype不返回） 时填写，广告的 html 内容会经由此字段返回。需要使用webview 渲染
	private RuishiNativeAd nativead;//	object;//		否	原生创意物料	adt=3时填写，原生创意物料详情见下
	private int vtype	;//	int	否	贴片创意类型	adt=11/12/13时填写，1-vast；2-object
	private String vast;//		string	否	vast类型贴片物料	vtype=1时必填
	private RuishiVideo video	;//object	;//	否	object类型贴片物料	vtype=2时必填，贴片物料详情见下
	private RuisihiContent content	;//object	;//	否	内容联盟返回	adt=20时填写，详情见下
	private List<RuishiApp> app_down_list;//	object[]	;//	否	APP下载列表返回	adt=30时填写，详情见下。APP下载列表数组。
	private String app_down_nextcur	;//	string	否	APP下载列表的下一页索引	adt=30时填写，下一页的索引。媒体收到该索引后，在请求下一页的时候，需要将次参数填入请求参数 app_down_nextcur
	private List<String> imp_tracking	;//	string[]	否	曝光监测地址，多个	 adt=30时该字段不填写，媒体无需处理此字段。
	private List<String> clk_tracking	;//	string[]	否	点击监测地址，多个	adt=30时该字段不填写，媒体无需处理此字段。
/*	ps：该字段不是必填，有些点击监测拼接在了落地页前，有些放在该字段中。该字段不返回则无需处理。
	有些点击监测链接中有点击监测宏，需要媒体替换，不替换将没有收益！详情见下。*/
	private int interact_type	;//	int	否	点击行为	0-打开网页，1-下载；不填写默认为打开网页
	private List<RuishiTracking> conv_tracking	;//object[]	否	转化监测地址	interact_type=1时有效，下载类广告详情见下
	private List<String> dp_tracking	;//	string[]	否	调用deeplink监测	当 deeplink 或 native.deeplink 有值时，调用deeplink后发起监测。可能为空。
	private String traffic	;//	string	是	流量类型	内部使用字段
	private String pkgname	;//	string	否	包名	interact_type=1时可能返回，下载的apk的包名
	private int size	;//i	nt	否	下载apk的大小	interact_type=1时可能返回，下载的apk的大小
	private int expiration_time	;//	int	否	广告过期时间戳	
	private String app_name	;//	string	否	下载的应用名称	interact_type=1时可能返回
	private String version_code	;//	string	否	下载的应用版本号	interact_type=1时可能返回
	private String version_name	;//	string	否	下载的应用版本名	interact_type=1时可能返回
	private String md5	;//	string	否	下载的apk文件md5	interact_type=1时可能返回
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAdt() {
		return adt;
	}
	public void setAdt(int adt) {
		this.adt = adt;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getLdp() {
		return ldp;
	}
	public void setLdp(String ldp) {
		this.ldp = ldp;
	}
	public String getAdtitle() {
		return adtitle;
	}
	public void setAdtitle(String adtitle) {
		this.adtitle = adtitle;
	}
	public String getAdsubtitle() {
		return adsubtitle;
	}
	public void setAdsubtitle(String adsubtitle) {
		this.adsubtitle = adsubtitle;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public String getAdm() {
		return adm;
	}
	public void setAdm(String adm) {
		this.adm = adm;
	}
	public RuishiNativeAd getNativead() {
		return nativead;
	}
	public void setNativead(RuishiNativeAd nativead) {
		this.nativead = nativead;
	}
	public int getVtype() {
		return vtype;
	}
	public void setVtype(int vtype) {
		this.vtype = vtype;
	}
	public String getVast() {
		return vast;
	}
	public void setVast(String vast) {
		this.vast = vast;
	}
	public RuishiVideo getVideo() {
		return video;
	}
	public void setVideo(RuishiVideo video) {
		this.video = video;
	}
	public RuisihiContent getContent() {
		return content;
	}
	public void setContent(RuisihiContent content) {
		this.content = content;
	}
	public List<RuishiApp> getApp_down_list() {
		return app_down_list;
	}
	public void setApp_down_list(List<RuishiApp> app_down_list) {
		this.app_down_list = app_down_list;
	}
	public String getApp_down_nextcur() {
		return app_down_nextcur;
	}
	public void setApp_down_nextcur(String app_down_nextcur) {
		this.app_down_nextcur = app_down_nextcur;
	}
	public List<String> getImp_tracking() {
		return imp_tracking;
	}
	public void setImp_tracking(List<String> imp_tracking) {
		this.imp_tracking = imp_tracking;
	}
	public List<String> getClk_tracking() {
		return clk_tracking;
	}
	public void setClk_tracking(List<String> clk_tracking) {
		this.clk_tracking = clk_tracking;
	}
	public int getInteract_type() {
		return interact_type;
	}
	public void setInteract_type(int interact_type) {
		this.interact_type = interact_type;
	}
	public List<RuishiTracking> getConv_tracking() {
		return conv_tracking;
	}
	public void setConv_tracking(List<RuishiTracking> conv_tracking) {
		this.conv_tracking = conv_tracking;
	}
	public List<String> getDp_tracking() {
		return dp_tracking;
	}
	public void setDp_tracking(List<String> dp_tracking) {
		this.dp_tracking = dp_tracking;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getExpiration_time() {
		return expiration_time;
	}
	public void setExpiration_time(int expiration_time) {
		this.expiration_time = expiration_time;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
