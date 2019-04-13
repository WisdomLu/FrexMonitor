package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3975719902324908363L;
	private String id;
	private String bidid;
	private List<HuixuanAdSeatbid> seatbid;
	private int nbr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBidid() {
		return bidid;
	}
	public void setBidid(String bidid) {
		this.bidid = bidid;
	}
	public List<HuixuanAdSeatbid> getSeatbid() {
		return seatbid;
	}
	public void setSeatbid(List<HuixuanAdSeatbid> seatbid) {
		this.seatbid = seatbid;
	}
	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

}
