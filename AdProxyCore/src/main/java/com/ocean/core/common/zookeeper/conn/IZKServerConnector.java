package com.ocean.core.common.zookeeper.conn;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月14日 
      @version 1.0 
 */
public interface IZKServerConnector <T>{
   List<T> getServers() throws Exception;
}