package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneDeal   {
  /**
	 * 
	 */
	private static final long serialVersionUID = 3904175641707412086L;
	private String id;
	  private int bidfloor;
	  private String  bidfloorcur;
	  private int at;
	  private List<String>   wseat;
	  private List<String>   wadomain;
	  private RedStoneExt ext;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBidfloor() {
		return bidfloor;
	}
	public void setBidfloor(int bidfloor) {
		this.bidfloor = bidfloor;
	}
	public String getBidfloorcur() {
		return bidfloorcur;
	}
	public void setBidfloorcur(String bidfloorcur) {
		this.bidfloorcur = bidfloorcur;
	}
	public int getAt() {
		return at;
	}
	public void setAt(int at) {
		this.at = at;
	}
	public List<String> getWseat() {
		return wseat;
	}
	public void setWseat(List<String> wseat) {
		this.wseat = wseat;
	}
	public List<String> getWadomain() {
		return wadomain;
	}
	public void setWadomain(List<String> wadomain) {
		this.wadomain = wadomain;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
