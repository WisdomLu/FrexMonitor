package com.ocean.persist.api.proxy.xunfa;

import java.util.List;

public class XunfaAd {
	private String title;//	String	标题
	private String sub_title;//		子标题
	private String adtype	;//String	广告类型：下载类 download，二次请求下载类 redownload 和
	//跳转类(redirect）
	private String pn	;//String	下载类广告的包名
	private List<String> impr	;//StringArray	展示曝光地址
	private List<String> click;//	StringArray	点击曝光地址,详见 3
	private List<String> inst_downstart;//	StringArray	下载类广告的下载开始曝光
	private List<String> inst_downsucc	;//StringArray	下载类广告的下载成功曝光
	private List<String> inst_installstart	;//StringArray	下载类广告的安装开始曝光
	private List<String> inst_installs	;//StringArray	下载类广告的安装成功曝光

	private String snum	;//String	交易 ID
	private String ud	;//String	广告更新时间
	private String token	;//String	交易 token
	private String sbt	;//String	广告描述（经 urlencode 编码）
	private String landing_url	;//String	着陆页。（经 urlencode 编码,格式说明详见 4.2.8）
	private String deep_link	;//String	deeplink 为空或者不存在或者无法处理，则处理 landing_url
	private String admaterial	;//String	资源类型：img(默认，没有的时候是这个，要显示的是一张图片)，video（视频，展示的是视频），audio（音频，语音播
//	放），page（页面，显示的是页面，一般是 html 格式）
	//private String video	;//String	当 admaterial 为 video 的时候有此项。见 4.2.5video 说明
	private String page	;//String	当 admaterial 为 page 的时候有此项。见 4.2.6page 说明
	private List<XunfaImg> imgs	;//StringArray	当 admaterial 为 img 的时候有此项。见 4.2.4 imgs 说明
	public String getTitle() {
		return title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public String getAdtype() {
		return adtype;
	}
	public String getPn() {
		return pn;
	}
	public List<String> getImpr() {
		return impr;
	}
	public List<String> getClick() {
		return click;
	}
	public List<String> getInst_downstart() {
		return inst_downstart;
	}
	public List<String> getInst_downsucc() {
		return inst_downsucc;
	}
	public List<String> getInst_installstart() {
		return inst_installstart;
	}
	public List<String> getInst_installs() {
		return inst_installs;
	}
	public String getSnum() {
		return snum;
	}
	public String getUd() {
		return ud;
	}
	public String getToken() {
		return token;
	}
	public String getSbt() {
		return sbt;
	}
	public String getLanding_url() {
		return landing_url;
	}
	public String getDeep_link() {
		return deep_link;
	}
	public String getAdmaterial() {
		return admaterial;
	}
	public String getPage() {
		return page;
	}
	public List<XunfaImg> getImgs() {
		return imgs;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public void setImpr(List<String> impr) {
		this.impr = impr;
	}
	public void setClick(List<String> click) {
		this.click = click;
	}
	public void setInst_downstart(List<String> inst_downstart) {
		this.inst_downstart = inst_downstart;
	}
	public void setInst_downsucc(List<String> inst_downsucc) {
		this.inst_downsucc = inst_downsucc;
	}
	public void setInst_installstart(List<String> inst_installstart) {
		this.inst_installstart = inst_installstart;
	}
	public void setInst_installs(List<String> inst_installs) {
		this.inst_installs = inst_installs;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	public void setUd(String ud) {
		this.ud = ud;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setSbt(String sbt) {
		this.sbt = sbt;
	}
	public void setLanding_url(String landing_url) {
		this.landing_url = landing_url;
	}
	public void setDeep_link(String deep_link) {
		this.deep_link = deep_link;
	}
	public void setAdmaterial(String admaterial) {
		this.admaterial = admaterial;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public void setImgs(List<XunfaImg> imgs) {
		this.imgs = imgs;
	}

}
