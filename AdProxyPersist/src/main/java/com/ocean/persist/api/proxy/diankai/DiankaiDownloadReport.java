package com.ocean.persist.api.proxy.diankai;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月20日 
      @version 1.0 
 */
public class DiankaiDownloadReport    implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3324127661741271470L;
	private String download_start;
	private String download_complete;
	private String install;
	public String getDownload_start() {
		return download_start;
	}
	public void setDownload_start(String download_start) {
		this.download_start = download_start;
	}
	public String getDownload_complete() {
		return download_complete;
	}
	public void setDownload_complete(String download_complete) {
		this.download_complete = download_complete;
	}
	public String getInstall() {
		return install;
	}
	public void setInstall(String install) {
		this.install = install;
	}
}
