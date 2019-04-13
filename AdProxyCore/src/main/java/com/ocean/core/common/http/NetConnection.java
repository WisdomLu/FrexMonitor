package com.ocean.core.common.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetConnection {
	private NetConnection(){}
	private static class Singleton{
		private static final NetConnection netConnection=new NetConnection();
	}
	public static NetConnection buildNetConnction(){
		return NetConnection.Singleton.netConnection;
	}
	private  final int TIME_OUT=5*1000;
    /** 
     * 从网络Url中下载文件 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */  
    public   byte[]  downLoad(String urlStr) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
        //设置超时间为5秒  
        conn.setConnectTimeout(TIME_OUT);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] data = readInputStream(inputStream);       
        if(inputStream!=null){  
            inputStream.close();  
        }  
  
        return data;
  
    }  
  
  
  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public   byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
  
}
