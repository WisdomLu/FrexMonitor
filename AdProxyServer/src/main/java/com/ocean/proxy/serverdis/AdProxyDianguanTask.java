package com.ocean.proxy.serverdis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.dianguan.DianguanReq;
import com.ocean.persist.api.proxy.dianguan.DianguanResp;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月2日 
      @version 1.0 
 */
public class AdProxyDianguanTask  extends AsynAbstractTask<DianguanReq,DianguanResp>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub

		return JsonUtils.toJson(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		try {
			LOGGER.info("{} {} request param:{} ",this.getClass().getName(),this.getHashCode(),JsonUtils.toJson(param));
			return gzip(JsonUtils.toJson(param).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("{} {} request param compress error{} ",this.getClass().getName(),this.getHashCode(),e);
		}
		return null;
	}
	private byte[] gzip( byte[] data) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        GZIPOutputStream gzip = new GZIPOutputStream(bos);	
		gzip.write(data);  
		gzip.finish();  
		gzip.close();


        byte[] ret = bos.toByteArray();  
        return ret;
	}
	@Override
	public DianguanResp respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		DianguanResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, DianguanResp.class);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	@Override
	public DianguanResp respFormat(byte[] result) {
		// TODO Auto-generated method stub

		if(result==null){
			return null;
		}
		DianguanResp resp=null;
		try {
			String data= new String(result,"utf-8");
			LOGGER.info("{} {} return data:{}",this.getClass().getName(),this.getHashCode(),data);
			if(StringUtils.isNotEmpty(data)){
				resp= respFormat(data);
			}
		} catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return resp;
	}

}
