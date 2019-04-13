package com.ocean.persist.api.proxy.pairui;

public class PairuiBidReqPmpDeals {

	// required  String  deal id
	private String id;
	
	// required  float 交易价格
	private Float price;
	
	// required  integer 交易类型，0：PD，1：PDB，默认为0
	private Integer type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
