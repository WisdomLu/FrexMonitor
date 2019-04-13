package com.ocean.core.common.zookeeper.dis;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月14日 
      @version 1.0 
 */
public abstract class AbstractServerDistrBalancer<T> {
    public abstract T getServer();
}
