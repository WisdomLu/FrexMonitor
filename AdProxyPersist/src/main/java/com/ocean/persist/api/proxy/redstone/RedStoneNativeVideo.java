package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeVideo  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 471842987107042523L;
    private List<String> mimes;
    private Integer minduration;
    private Integer maxduration;
    private List<Integer> protocols;
    private RedStoneExt ext;
	public List<String> getMimes() {
		return mimes;
	}
	public void setMimes(List<String> mimes) {
		this.mimes = mimes;
	}
	public Integer getMinduration() {
		return minduration;
	}
	public void setMinduration(Integer minduration) {
		this.minduration = minduration;
	}
	public Integer getMaxduration() {
		return maxduration;
	}
	public void setMaxduration(Integer maxduration) {
		this.maxduration = maxduration;
	}
	public List<Integer> getProtocols() {
		return protocols;
	}
	public void setProtocols(List<Integer> protocols) {
		this.protocols = protocols;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
