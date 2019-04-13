package com.ocean.persist.api.proxy.onemob;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月1日 
      @version 1.0 
 */
public class OnemobDgfly    implements AdPullResponse{
	private static final long serialVersionUID = 1L;
	/* 
	1表示落地页为普通页面(href)采用浏览器打开；
	2表示落地页(href)为下载类app；
	3表示落地页是下载类，但是需要从href字段解析获得，详情请看附录C
	4表示有deeplink地址，需要优先处理详情请看附录D
	 * */

	private String hrefType; 
	private String adtype; //开屏：splash  banner：banner
	private String url;    //渲染好的广告html代码，采用UrlEncode编码，urldecode后可以直接在webview上展示
	private String href;   // String	若此字段不为空，则采用此字段作为广告点击后落地页
	private boolean rtp;   // 是否，1表示需要上报用户点击坐标，0表示不需要。详情见附录A
	private boolean rtp1;  // 是否，true表示需要格式化坐标到href和d_rpt,dc_rpt，false表示不需要。详情见附录B
	private String ratio;  //Banner和插屏广告的时候返回的广告图宽比高为：h_ratio/ratio
	private String h_ratio;
	private String dplnk;//Deeplink链接地址，hrefType=4时这个字段会出现，需要优先处理deeplink链接
	
	//inflow
	private String show_type;
	private String icon_img;
	private List<String> ad_img;
	private int w;
	private int h;
	private String name;
	private String desc;
	private String down_url;
	
	
	private List<String> s_rpt;// Array	展示上报
	private List<String> c_rpt;// array	点击上报
	private List<String> d_rpt;// array	开始下载上报，hrefType=2或3的时候才需要
	private List<String> dc_rpt;// Array	下载完成上报，hrefType=2或3的时候才需要
	private List<String> i_rpt;// array	安装完成上报，hrefType=2或3的时候才需要
	private List<String> a_rpt;// array	打开激活上报，hrefType=2或3的时候才需要
	public String getHrefType() {
		return hrefType;
	}
	public void setHrefType(String hrefType) {
		this.hrefType = hrefType;
	}
	public String getAdtype() {
		return adtype;
	}
	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public boolean isRtp() {
		return rtp;
	}
	public void setRtp(boolean rtp) {
		this.rtp = rtp;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getH_ratio() {
		return h_ratio;
	}
	public void setH_ratio(String h_ratio) {
		this.h_ratio = h_ratio;
	}
	public String getDplnk() {
		return dplnk;
	}
	public void setDplnk(String dplnk) {
		this.dplnk = dplnk;
	}
	public List<String> getS_rpt() {
		return s_rpt;
	}
	public void setS_rpt(List<String> s_rpt) {
		this.s_rpt = s_rpt;
	}
	public List<String> getC_rpt() {
		return c_rpt;
	}
	public void setC_rpt(List<String> c_rpt) {
		this.c_rpt = c_rpt;
	}
	public List<String> getD_rpt() {
		return d_rpt;
	}
	public void setD_rpt(List<String> d_rpt) {
		this.d_rpt = d_rpt;
	}
	public List<String> getDc_rpt() {
		return dc_rpt;
	}
	public void setDc_rpt(List<String> dc_rpt) {
		this.dc_rpt = dc_rpt;
	}
	public List<String> getI_rpt() {
		return i_rpt;
	}
	public void setI_rpt(List<String> i_rpt) {
		this.i_rpt = i_rpt;
	}
	public List<String> getA_rpt() {
		return a_rpt;
	}
	public void setA_rpt(List<String> a_rpt) {
		this.a_rpt = a_rpt;
	}
	public boolean isRtp1() {
		return rtp1;
	}
	public void setRtp1(boolean rtp1) {
		this.rtp1 = rtp1;
	}
	public String getShow_type() {
		return show_type;
	}
	public void setShow_type(String show_type) {
		this.show_type = show_type;
	}
	public String getIcon_img() {
		return icon_img;
	}
	public void setIcon_img(String icon_img) {
		this.icon_img = icon_img;
	}
	public List<String> getAd_img() {
		return ad_img;
	}
	public void setAd_img(List<String> ad_img) {
		this.ad_img = ad_img;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDown_url() {
		return down_url;
	}
	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}
}
