package com.ocean.proxy.serverdis;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.abaidu.BaiduMobadsApi550.MobadsRequest;
import com.ocean.persist.api.proxy.abaidu.BaiduMobadsApi550.MobadsResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月6日 
      @version 1.0 
 */
public class AdProxyABaiduTask extends AsynAbstractTask<MobadsRequest,MobadsResponse> {

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
	public MobadsResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MobadsResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		MobadsResponse resp=null;
		try {
			if(result!=null){
				resp=(MobadsResponse)MobadsResponse.parseFrom(result);
			}
			
			if(resp!=null){
				LOGGER.info("{} abaidu ad return data:{}",this.getHashCode(),UniversalUtils.replaceBlank(response.toString()));
			}
		} catch (InvalidProtocolBufferException e) {
			LOGGER.error("{} abaidu parse response data error(InvalidProtocolBufferException),msg:{}",this.getHashCode(),e.getMessage(),e);
		}catch (Throwable e) {
			LOGGER.error("{} abaidu parse response data error(Exception),msg:{}",this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

}
