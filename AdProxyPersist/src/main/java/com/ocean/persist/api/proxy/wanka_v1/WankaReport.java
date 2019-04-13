package com.ocean.persist.api.proxy.wanka_v1;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
public class WankaReport {
	private List<WankaTracker> imptrackers;

	private List<WankaTracker> clktrackers;

	private List<WankaTracker> dwnlsts;

	private List<WankaTracker> dwnltrackers;

	private List<WankaTracker> intltrackers;

	private List<WankaTracker> actvtrackers;
	
	private List<WankaTracker>  dplktrackers;

	public List<WankaTracker> getImptrackers() {
		return imptrackers;
	}

	public void setImptrackers(List<WankaTracker> imptrackers) {
		this.imptrackers = imptrackers;
	}

	public List<WankaTracker> getClktrackers() {
		return clktrackers;
	}

	public void setClktrackers(List<WankaTracker> clktrackers) {
		this.clktrackers = clktrackers;
	}

	public List<WankaTracker> getDwnlsts() {
		return dwnlsts;
	}

	public void setDwnlsts(List<WankaTracker> dwnlsts) {
		this.dwnlsts = dwnlsts;
	}

	public List<WankaTracker> getDwnltrackers() {
		return dwnltrackers;
	}

	public void setDwnltrackers(List<WankaTracker> dwnltrackers) {
		this.dwnltrackers = dwnltrackers;
	}

	public List<WankaTracker> getIntltrackers() {
		return intltrackers;
	}

	public void setIntltrackers(List<WankaTracker> intltrackers) {
		this.intltrackers = intltrackers;
	}

	public List<WankaTracker> getActvtrackers() {
		return actvtrackers;
	}

	public void setActvtrackers(List<WankaTracker> actvtrackers) {
		this.actvtrackers = actvtrackers;
	}

	public List<WankaTracker> getDplktrackers() {
		return dplktrackers;
	}

	public void setDplktrackers(List<WankaTracker> dplktrackers) {
		this.dplktrackers = dplktrackers;
	}


}
