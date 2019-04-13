package com.ocean.core.common.objectpool;

import java.util.concurrent.TimeUnit;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月11日 
      @version 1.0 
 */
public abstract class AbstractBlokingPool<T> extends AbstractPool<T>{
   public abstract T get(long time,TimeUnit timeUnit);
}
