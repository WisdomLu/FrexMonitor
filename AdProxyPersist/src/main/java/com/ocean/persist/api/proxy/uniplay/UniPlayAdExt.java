package com.ocean.persist.api.proxy.uniplay;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年9月8日 
      @version 1.0 
 */
public class UniPlayAdExt  extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4201878584103028359L;
	private List<String> downsucc;
	private List<String> installsucc;
	private List<String> appactive;
	public List<String> getDownsucc() {
		return downsucc;
	}
	public void setDownsucc(List<String> downsucc) {
		this.downsucc = downsucc;
	}
	public List<String> getInstallsucc() {
		return installsucc;
	}
	public void setInstallsucc(List<String> installsucc) {
		this.installsucc = installsucc;
	}
	public List<String> getAppactive() {
		return appactive;
	}
	public void setAppactive(List<String> appactive) {
		this.appactive = appactive;
	}
}
