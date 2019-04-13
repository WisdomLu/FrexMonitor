package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiAdm {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;
	private InmobiAdImage icon;
	private InmobiAdImage screenshots;
	private String cta;
	private String landingURL;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InmobiAdImage getIcon() {
		return icon;
	}

	public void setIcon(InmobiAdImage icon) {
		this.icon = icon;
	}

	public InmobiAdImage getScreenshots() {
		return screenshots;
	}

	public void setScreenshots(InmobiAdImage screenshots) {
		this.screenshots = screenshots;
	}

	public String getCta() {
		return cta;
	}

	public void setCta(String cta) {
		this.cta = cta;
	}

	public String getLandingURL() {
		return landingURL;
	}

	public void setLandingURL(String landingURL) {
		this.landingURL = landingURL;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
