package com.ocean.persist.api.proxy.zhongcheng;

import java.util.List;
import com.ocean.core.common.base.AbstractBaseEntity;
public class ZhongchengNativeCreative {

	private static final long serialVersionUID = 1L;
	private String adtype;// :"redirect",
	private String title;// xxxxxxx",
	private String landing_url;// http://xxxxxxx",
	private String image;// http://xxxxxxx",
	private List<String> impr_url;
    private List<String> click_url;
    
	public String getAdtype() {
		return adtype;
	}

	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanding_url() {
		return landing_url;
	}

	public void setLanding_url(String landing_url) {
		this.landing_url = landing_url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getImpr_url() {
		return impr_url;
	}

	public void setImpr_url(List<String> impr_url) {
		this.impr_url = impr_url;
	}

	public List<String> getClick_url() {
		return click_url;
	}

	public void setClick_url(List<String> click_url) {
		this.click_url = click_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
