package com.ocean.bid.service;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.JoinSource;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月13日 
      @version 1.0 
 */
public interface AppDisBidService {
	public JoinSource bid(AppDisRecomReq req);
	public AbstractAppDisSupplier getAdSupplier(AppDisRecomReq req)throws AppDisException ;
}
