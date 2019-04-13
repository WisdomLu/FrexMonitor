package com.ocean.lbs.client.pool;

import org.apache.commons.pool.KeyedPoolableObjectFactory;

import com.ocean.core.common.objectpool.ObjectFactory;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月29日 
      @version 1.0 
 */
public class LBSClientPoolableObjectFactory implements KeyedPoolableObjectFactory{
	private  ObjectFactory<LBSClient> creator=new LBSClientCtreator();
	public void activateObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void destroyObject(Object arg0, Object client) throws Exception {
		// TODO Auto-generated method stub
		if(client instanceof LBSClient){
			((LBSClient) client).close();
			client=null;
		}
	}

	public Object makeObject(Object str) throws Exception {
		// TODO Auto-generated method stub
		
	    if(str!=null &&str instanceof String){
	    	String node=str.toString();
	    	String[] nodeArr=node.split(":");
	    	return creator.create(nodeArr[0],Integer.parseInt(nodeArr[1])) ;
	    }
		return null;
	}

	public void passivateObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean validateObject(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
