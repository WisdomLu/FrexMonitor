package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360Request;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360Respose;

public class QihooKeywordTask  extends AsynAbstractTask<KeywordSearch360Request,KeywordSearch360Respose>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		return JsonUtils.toJson(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeywordSearch360Respose respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		KeywordSearch360Respose resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, KeywordSearch360Respose.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public KeywordSearch360Respose respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
