package com.ocean.persist.api.proxy.helian;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月15日 
      @version 1.0 
 */
public class HelianAd  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1460018007371009849L;
	private int admt;
	private String adm;
	private String adhtml;
	private int adct;
	private String url;
	private String displaytitle;
	private String displaytext;
	private List<String> imgtracking;
	private List<String> thclkurl;
	private List<String> dsurl;
	private List<String> dfurl;
	private List<String> isurl;
	private List<String> ifurl;
	private List<String> ourl;
	private String dan;
	private String dpn;
	public int getAdmt() {
		return admt;
	}
	public void setAdmt(int admt) {
		this.admt = admt;
	}
	public String getAdm() {
		return adm;
	}
	public void setAdm(String adm) {
		this.adm = adm;
	}
	public String getAdhtml() {
		return adhtml;
	}
	public void setAdhtml(String adhtml) {
		this.adhtml = adhtml;
	}
	public int getAdct() {
		return adct;
	}
	public void setAdct(int adct) {
		this.adct = adct;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDisplaytitle() {
		return displaytitle;
	}
	public void setDisplaytitle(String displaytitle) {
		this.displaytitle = displaytitle;
	}
	public String getDisplaytext() {
		return displaytext;
	}
	public void setDisplaytext(String displaytext) {
		this.displaytext = displaytext;
	}
	public List<String> getImgtracking() {
		return imgtracking;
	}
	public void setImgtracking(List<String> imgtracking) {
		this.imgtracking = imgtracking;
	}
	public List<String> getThclkurl() {
		return thclkurl;
	}
	public void setThclkurl(List<String> thclkurl) {
		this.thclkurl = thclkurl;
	}
	public List<String> getDsurl() {
		return dsurl;
	}
	public void setDsurl(List<String> dsurl) {
		this.dsurl = dsurl;
	}
	public List<String> getDfurl() {
		return dfurl;
	}
	public void setDfurl(List<String> dfurl) {
		this.dfurl = dfurl;
	}
	public List<String> getIsurl() {
		return isurl;
	}
	public void setIsurl(List<String> isurl) {
		this.isurl = isurl;
	}
	public List<String> getIfurl() {
		return ifurl;
	}
	public void setIfurl(List<String> ifurl) {
		this.ifurl = ifurl;
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
	public String getDpn() {
		return dpn;
	}
	public void setDpn(String dpn) {
		this.dpn = dpn;
	}

}
