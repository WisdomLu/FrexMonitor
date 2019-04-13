package com.ocean.proxy.serverdis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.wanka_v1.WankaAdPullParams;
import com.ocean.persist.api.proxy.wanka_v1.WankaResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class AdProxyWankaV1Task extends AsynAbstractTask<WankaAdPullParams,WankaResponse>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
/*		try {
			return new String(gzip(JsonUtils.toJson(param).getBytes()),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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

	@Override
	public WankaResponse respFormat(String result) throws Exception {
		// TODO Auto-generated method stub
		WankaResponse resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, WankaResponse.class);
				LOGGER.info("{} {} respose:{} ",this.getClass().getName(),this.getHashCode(),JsonUtils.toJson(result));
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	@Override
	public WankaResponse respFormat(byte[] result) {
		// TODO Auto-generated method stub
		if(result==null){
			return null;
		}
		WankaResponse resp=null;
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
	private byte[] unzip(byte[] data) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream(data);  
        GZIPInputStream gzip = new GZIPInputStream(bis);  
        byte[] buf = new byte[1024];  
        int num = -1;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while ((num = gzip.read(buf, 0, buf.length)) != -1) {  
            bos.write(buf, 0, num);  
        }  
        gzip.close();  
        bis.close();  
        byte[] ret = bos.toByteArray();  
        bos.flush();  
        bos.close();  
        return ret; 
	}
}
