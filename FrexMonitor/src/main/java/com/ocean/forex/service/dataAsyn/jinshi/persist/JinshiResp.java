package com.ocean.forex.service.dataAsyn.jinshi.persist;
import com.ocean.forex.entity.analyzedata.AnalyticalData;
import com.ocean.forex.service.dataAsyn.IDataAsynResponse;

public class JinshiResp implements IDataAsynResponse{
	private AnalyticalData ad;

	public AnalyticalData getAd() {
		return ad;
	}

	public void setAd(AnalyticalData ad) {
		this.ad = ad;
	}
}
