package com.ocean.persist.app.dis.qqDownloader.getRecommendADList;

import com.ocean.persist.app.dis.qqDownloader.BaseAppADListItem;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
public class ADAppInfo extends BaseAppADListItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2302604727529605038L;
	
	private String totalDownloadTimes;
	private int targetSdkVersion;
	public String getTotalDownloadTimes() {
		return totalDownloadTimes;
	}
	public void setTotalDownloadTimes(String totalDownloadTimes) {
		this.totalDownloadTimes = totalDownloadTimes;
	}
	public int getTargetSdkVersion() {
		return targetSdkVersion;
	}
	public void setTargetSdkVersion(int targetSdkVersion) {
		this.targetSdkVersion = targetSdkVersion;
	}
}
