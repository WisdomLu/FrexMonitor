package com.ocean.core.common.objectpool;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月11日 
      @version 1.0 
 */
public abstract class AbstractPool<T> implements Pool<T>{
    public void put(T obj) throws Exception{
    	if(this.validate(obj)){
    		this.putToPool(obj);
    	}else{
    		this.handlerInvalidObj(obj);
    	}
    }
    public  abstract void    handlerInvalidObj(T obj) throws Exception;
    public  abstract void    putToPool(T obj) throws Exception;
    public  abstract boolean validate(T obj);

}
