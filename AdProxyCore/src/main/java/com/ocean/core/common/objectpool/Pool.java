package com.ocean.core.common.objectpool;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月11日 
      @version 1.0 
 */
public interface Pool <T> {
   //return object from pool
   T get() throws Exception;
   
   //put a object to pool
   void put(T obj) throws Exception;
   
   //close the pool
   void close();
}
