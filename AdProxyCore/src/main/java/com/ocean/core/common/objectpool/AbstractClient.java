package com.ocean.core.common.objectpool;

import org.apache.thrift.transport.TTransportException;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月14日 
      @version 1.0 
 */
public abstract class AbstractClient <T>{
	public AbstractClient(T client){
		this.client=client;
	}
    protected T client;
    
	public  abstract T getClient() ;
	
	public  abstract void setClient(T client);
	
	public abstract void close();
	
	public abstract void open() throws TTransportException;
}
