package com.ocean.lbs.client.pool;

import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.ocean.core.common.objectpool.AbstractClient;
import com.ocean.lbs.entity.LBSServer;
import com.ocean.lbs.entity.LBSServer.Client;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月14日 
      @version 1.0 
 */
public class LBSClient extends AbstractClient<LBSServer.Client>{
	private final String SEPERATOR=":";
	public LBSClient(String host,int port ,LBSServer.Client client,TTransport transport){
		super(client);
		this.host=host;
		this.port=port;
		this.transport=transport;
	}
    private String host;
    private int port;
    private TTransport transport;
    public  String getHostAndPort(){
    	StringBuilder build=new StringBuilder();
    	return build.append(this.getHost()).append(SEPERATOR).append(this.getPort()).toString();
    }
	@Override
	public Client getClient() {
		// TODO Auto-generated method stub
		return this.client;
	}

	@Override
	public void setClient(Client client) {
		// TODO Auto-generated method stub
		this.client=client;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		if(transport.isOpen()){
			this.transport.close();
		}
	}
	@Override
	public void open() throws TTransportException {
		// TODO Auto-generated method stub
		if(!transport.isOpen()){
			this.transport.open();
		}
		
	}
	


	
}