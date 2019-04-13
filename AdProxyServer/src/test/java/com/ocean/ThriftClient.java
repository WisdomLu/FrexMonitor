package com.ocean;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.ocean.proxy.thrift.entity.ApiProxy;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月5日 
      @version 1.0 
 */
public class ThriftClient {
	private TTransport transport = null;
	ApiProxy.Client client =null;
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9091;
	public static final int TIMEOUT = 10000;
	private static final int zkpos = 457; 
	public ThriftClient(){
		init();
	}
    public  ApiProxy.Client getClient(){
    	return this.client;
    }
    public  ApiProxy.Client init(){
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);
			client = new ApiProxy.Client(protocol);
			transport.open();
			return client;
		} catch (TTransportException e) {
			//e.printStackTrace();
			 closeTransport();
		} catch (TException e) {
			//e.printStackTrace();
			 closeTransport();
		} 
		return null;
    
    }
    public void closeTransport(){
    	if(transport!=null&&transport.isOpen()){
    		this.transport.close();
    	}
    	
    }
    public void destroyClient(){
    	this.client=null;
    }
    public void destroy(){
      this.closeTransport();
      this.destroyClient();
    }
    public TTransport getTransport(){
    	return transport;
    }
}
