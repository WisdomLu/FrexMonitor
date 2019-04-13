package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdBid     implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5147835086013324363L;
	private String id;
	private String impid;
	private String iurl;
	private String crid;
	private int w;
	private int h;
	private String adm;
	private String clkurl;
	private String clkurl302;
	private List<String> imptrackers;
	private List<String> clktrackers;
	private int action_type;
	private int duration;
	private String bundle;
	private int nbr;
	private  HuixuanAdRespNative _native;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImpid() {
		return impid;
	}
	public void setImpid(String impid) {
		this.impid = impid;
	}
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
	}
	public String getCrid() {
		return crid;
	}
	public void setCrid(String crid) {
		this.crid = crid;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getAdm() {
		return adm;
	}
	public void setAdm(String adm) {
		this.adm = adm;
	}
	public String getClkurl() {
		return clkurl;
	}
	public void setClkurl(String clkurl) {
		this.clkurl = clkurl;
	}
	public String getClkurl302() {
		return clkurl302;
	}
	public void setClkurl302(String clkurl302) {
		this.clkurl302 = clkurl302;
	}
	public List<String> getImptrackers() {
		return imptrackers;
	}
	public void setImptrackers(List<String> imptrackers) {
		this.imptrackers = imptrackers;
	}
	public List<String> getClktrackers() {
		return clktrackers;
	}
	public void setClktrackers(List<String> clktrackers) {
		this.clktrackers = clktrackers;
	}
	public int getAction_type() {
		return action_type;
	}
	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getBundle() {
		return bundle;
	}
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	public HuixuanAdRespNative get_native() {
		return _native;
	}
	public void set_native(HuixuanAdRespNative _native) {
		this._native = _native;
	}

}
