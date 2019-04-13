package com.ocean.persist.api.proxy.youxiao;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月16日 
      @version 1.0 
 */
public class YouxiaoTrack {
	private List<String> starturl;//
	private List<String> doneurl;//
	private List<String> installurl;//
	private List<String> 	installdoneurl;//
	public List<String> getStarturl() {
		return starturl;
	}
	public void setStarturl(List<String> starturl) {
		this.starturl = starturl;
	}
	public List<String> getDoneurl() {
		return doneurl;
	}
	public void setDoneurl(List<String> doneurl) {
		this.doneurl = doneurl;
	}
	public List<String> getInstallurl() {
		return installurl;
	}
	public void setInstallurl(List<String> installurl) {
		this.installurl = installurl;
	}
	public List<String> getInstalldoneurl() {
		return installdoneurl;
	}
	public void setInstalldoneurl(List<String> installdoneurl) {
		this.installdoneurl = installdoneurl;
	}
}
