package com.ocean.task;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.zs.pkgsearch.ZsPkgSearchReply;
import com.ocean.persist.app.dis.zs.pkgsearch.ZsPkgSearchRequest;

public class ZsPkgSearchTask extends
		AsynAbstractTask<ZsPkgSearchRequest, ZsPkgSearchReply> {

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
		// LOGGER.info("{} {} befor parse to http params data:{}",this.getClass().getName(),this.getHashCode(),JsonUtils.toJson(param));

		return Bean2Utils.toHttpParams(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZsPkgSearchReply respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		ZsPkgSearchReply resp = null;
		try {
			if (StringUtils.isNotEmpty(result)) {
				result="{"+"\""+"list"+"\""+":"+result+"}";
				resp = JsonUtils.toBean(result, ZsPkgSearchReply.class);
			}
		} catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",
					this.getClass().getName(), this.getHashCode(),
					e.getMessage(), e);
		} finally {
			this.setResponse(resp);
		}
		return this.getResponse();
	}

	@Override
	public ZsPkgSearchReply respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
