package com.ocean.proxy.serverdis;
import com.google.protobuf.InvalidProtocolBufferException;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.adhub.AdRequest;
import com.ocean.persist.api.proxy.adhub.AdRequest.SdkRequest;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass.ServerResponse;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class AdProxyAdhubTask extends AsynAbstractTask<AdRequest.SdkRequest,ServerResponse>{



	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
      return		this.getParam().toByteArray();

	}

	@Override
	public ServerResponse respFormat(String result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub

		ServerResponse resp=null;
		try {
			if(result!=null){
				resp=(ServerResponse)ServerResponse.parseFrom(result);
				this.setResponse(resp);
			}
			if(resp!=null){
				LOGGER.info("adhub ad {} return data:{}",this.getHashCode(),UniversalUtils.replaceBlank(response.toString()));
				//LOGGER.info("adhub ad {} return data:{}",this.getHashCode(),JsonUtils.toJson(response.toString()));
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			LOGGER.error("adhub parse response data error(InvalidProtocolBufferException),msg:{}",e.getMessage(),e);
		}catch (Throwable e) {
			LOGGER.error("adhub parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();

	}

	@Override
	public SdkRequest getParam() {
		// TODO Auto-generated method stub
		return this.param;
	}

	@Override
	public void setParam(SdkRequest param) {
		// TODO Auto-generated method stub
		this.param=param;
	}
}
