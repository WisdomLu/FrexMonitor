package com.ocean.proxy.serverdis;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.inveno.util.StringUtil;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullParams;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月2日 
      @version 1.0 
 */
public class AdProxyDiankaiTask  extends AsynAbstractTask<DiankaiAdPullParams,DiankaiAdPullResponse>{
	public  final Logger logger = MyLogManager.getLogger();
	@Override
	public DiankaiAdPullParams getParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParam(DiankaiAdPullParams param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		return  JsonUtils.toJson(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiankaiAdPullResponse respFormat(String result) throws Exception{
		// TODO Auto-generated method stub
		DiankaiAdPullResponse resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
		
				resp=JsonUtils.toBean(result, DiankaiAdPullResponse.class);
			}
		}catch (Throwable e) {
			LOGGER.error("diankai parse response data error(Exception),msg:{}",e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
		}

	@Override
	public DiankaiAdPullResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}


}
