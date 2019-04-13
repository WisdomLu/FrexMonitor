package com.ocean.proxy.server;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.proxy.thrift.entity.Position;
import com.ocean.proxy.thrift.entity.ApiProxy.Iface;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月17日 
      @version 1.0 
 */
public abstract class AbstractProxyInvoker  implements Iface{
	Logger logger=LoggerFactory.getLogger(this.getClass());
	// 默认值
	static final List<Position> defpos = new ArrayList<Position>();
	static{
		Position pos = new Position();
		pos.setPositionType(1);
		pos.setPutType(1);
		defpos.add(pos);
	}
}
