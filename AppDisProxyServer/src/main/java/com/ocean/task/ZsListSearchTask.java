package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.zs.listsearch.ZsListSearchReply;
import com.ocean.persist.app.dis.zs.listsearch.ZsListSearchRequest;

public class ZsListSearchTask  extends AsynAbstractTask<ZsListSearchRequest,ZsListSearchReply>{

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
	public ZsListSearchReply respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub   
		ZsListSearchReply resp=null;
		try{
			if(StringUtils.isNotEmpty(result)){
				result="{"+"\""+"list"+"\""+":"+result+"}";
				resp=JsonUtils.toBean(result, ZsListSearchReply.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}

	@Override
	public ZsListSearchReply respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
