package com.ocean.persist.api.proxy.woso;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年11月29日 
      @version 1.0 
 */
public class WosoAd extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9215787014102279763L;
	private int adType;//广告类型 0或1：html，2或3：下载类型。详情说明见附录
	private int crt_type;//广告素材类型可能取值 1：文字类型，2：图片类型，3或7或11或13：图文类型
	private String adfrom;//广告来源，非空必须展示，为空则不展示。注：当返回为“GDT”时，联盟要求渲染时必须带有腾讯广点通广告联盟的logo和“广告”标识
	private String icon_src;//小图标的url
	private List<String> image_src;//图片的url（只有一张图片时icon_src没有值image_src有一个值，对于组图广告，一般有多个值）
	private String click_url;//点击链接 ，见附录
	private String deeplink;//点击deeplink地址链接。见附录
	private List<String> clicker_url;//当点击广告时存在此字段时需按列表顺序进行发送
	private List<String> expose_url;//当广告展现时按照这个 url 列表顺序发送日志
	private String desc;//文字描述
	private String text;//主题文字
	private String targerid;//当api_name=0或5时有数据返回(不作考虑)
	private int api_name;//返回的API类型
	private List<String> skipUrls;//广告跳过时的监控地址，URL数组，有多个时，每个都要上报
	private List<String> startDownloadUrls;//追踪下载广告的下载安装链接，（注：如果存在，必须进行上报，否则会影响转换收入）
	private List<String> finishDownloadUrls;
	
	private List<String> startInstallUrls;
	
	private List<String> finishInstallUrls;
	
	private List<String> activeUrls;

	public List<String> getActiveUrls() {
		return activeUrls;
	}

	public void setActiveUrls(List<String> activeUrls) {
		this.activeUrls = activeUrls;
	}

	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}

	public int getCrt_type() {
		return crt_type;
	}

	public void setCrt_type(int crt_type) {
		this.crt_type = crt_type;
	}

	public String getAdfrom() {
		return adfrom;
	}

	public void setAdfrom(String adfrom) {
		this.adfrom = adfrom;
	}

	public String getIcon_src() {
		return icon_src;
	}

	public void setIcon_src(String icon_src) {
		this.icon_src = icon_src;
	}

	public List<String> getImage_src() {
		return image_src;
	}

	public void setImage_src(List<String> image_src) {
		this.image_src = image_src;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public String getDeeplink() {
		return deeplink;
	}

	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}

	public List<String> getClicker_url() {
		return clicker_url;
	}

	public void setClicker_url(List<String> clicker_url) {
		this.clicker_url = clicker_url;
	}

	public List<String> getExpose_url() {
		return expose_url;
	}

	public void setExpose_url(List<String> expose_url) {
		this.expose_url = expose_url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTargerid() {
		return targerid;
	}

	public void setTargerid(String targerid) {
		this.targerid = targerid;
	}

	public int getApi_name() {
		return api_name;
	}

	public void setApi_name(int api_name) {
		this.api_name = api_name;
	}

	public List<String> getSkipUrls() {
		return skipUrls;
	}

	public void setSkipUrls(List<String> skipUrls) {
		this.skipUrls = skipUrls;
	}

	public List<String> getStartDownloadUrls() {
		return startDownloadUrls;
	}

	public void setStartDownloadUrls(List<String> startDownloadUrls) {
		this.startDownloadUrls = startDownloadUrls;
	}

	public List<String> getFinishDownloadUrls() {
		return finishDownloadUrls;
	}

	public void setFinishDownloadUrls(List<String> finishDownloadUrls) {
		this.finishDownloadUrls = finishDownloadUrls;
	}

	public List<String> getStartInstallUrls() {
		return startInstallUrls;
	}

	public void setStartInstallUrls(List<String> startInstallUrls) {
		this.startInstallUrls = startInstallUrls;
	}

	public List<String> getFinishInstallUrls() {
		return finishInstallUrls;
	}

	public void setFinishInstallUrls(List<String> finishInstallUrls) {
		this.finishInstallUrls = finishInstallUrls;
	}

}
