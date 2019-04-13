package com.ocean.lbs.client.pool;

import org.apache.logging.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.ocean.core.common.objectpool.ObjectFactory;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.lbs.entity.LBSServer;
import com.ocean.persist.common.ProxyConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月11日 
      @version 1.0 
 */
public class LBSClientCtreator implements ObjectFactory<LBSClient>{
	private  final Logger logger = MyLogManager.getLbsLogger();
	public LBSClient create(String host,int port) {

		// TODO Auto-generated method stub
		  try {
			    LBSClient dc=null;
				int timeOut=SystemContext.getStaticPropertyHandler().getInt(ProxyConstants.LBS_THRIFT_CONNECT_TIME_OUT,1000);
				TTransport transport = new TFramedTransport(new TSocket(host,port, timeOut));
				TProtocol protocol = new TBinaryProtocol(transport);
				LBSServer.Client client = new LBSServer.Client(protocol);
		        transport.open();
		        
		        dc=new LBSClient(host,port,client,transport);
		        dc.setClient(client);
		        
		        return dc;
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			logger.error("create lbs client {}:{} error TTransportException",host,port, e);
		}catch (Exception e){
			logger.error("create lbs client error Exception", e);
		}
		  return null;
	}

}
