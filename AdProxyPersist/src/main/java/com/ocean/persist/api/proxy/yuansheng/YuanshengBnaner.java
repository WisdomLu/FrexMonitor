package com.ocean.persist.api.proxy.yuansheng;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengBnaner {
	private String iurl;//	string	Y	图片地址。
	private String link	;//string	Y	广告落地页。
	private int lnktyp;//	integer	Y	广告点击后续类型，见A5。
	private List<YuanshengClickreport> clickreport	;//array of object	Y	点击回调，见4.1.6。
	private List<YuanshengImpreport> impreport	;//array of object	Y	曝光回调，见4.1.5。
	private List<YuanshengActivereport> activereport;//	array of object	Y	激活回调，见4.1.7。
	private double price	;//double	Y	广告曝光/点击价格。
	private String crid;//	string	Y	广告物料id
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getLnktyp() {
		return lnktyp;
	}
	public void setLnktyp(int lnktyp) {
		this.lnktyp = lnktyp;
	}
	public List<YuanshengClickreport> getClickreport() {
		return clickreport;
	}
	public void setClickreport(List<YuanshengClickreport> clickreport) {
		this.clickreport = clickreport;
	}
	public List<YuanshengImpreport> getImpreport() {
		return impreport;
	}
	public void setImpreport(List<YuanshengImpreport> impreport) {
		this.impreport = impreport;
	}
	public List<YuanshengActivereport> getActivereport() {
		return activereport;
	}
	public void setActivereport(List<YuanshengActivereport> activereport) {
		this.activereport = activereport;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCrid() {
		return crid;
	}
	public void setCrid(String crid) {
		this.crid = crid;
	}

}
