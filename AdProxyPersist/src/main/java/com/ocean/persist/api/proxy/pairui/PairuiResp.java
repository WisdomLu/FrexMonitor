package com.ocean.persist.api.proxy.pairui;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class PairuiResp extends AbstractBaseEntity  implements AdPullResponse {

	private static final long serialVersionUID = -6745637534759214388L;
	
	// required String 对应bidreqeust中的id
	private String id;
	
	// optional object array  买方席位，目前仅支持一个seatbid
	private List<PairuiRespSeat> seatbid;
	
	// optional  String 买方id，由KDG Ad Exchange分配
	private String buyerid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PairuiRespSeat> getSeatbid() {
		return seatbid;
	}

	public void setSeatbid(List<PairuiRespSeat> seatbid) {
		this.seatbid = seatbid;
	}

	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
}
