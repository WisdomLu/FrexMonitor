package com.ocean.bid.service;

import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.base.BaseAdSupplier;
import com.ocean.proxy.thrift.entity.AdRecomReq;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月13日 
      @version 1.0 
 */
public interface AdBidService {
	public JoinDSPEmu bid(AdRecomReq adreq);
	public AbstractAdSupplier getAdSupplier(AdRecomReq adreq);
}
