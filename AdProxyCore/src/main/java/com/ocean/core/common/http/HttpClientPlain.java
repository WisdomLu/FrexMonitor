package com.ocean.core.common.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
public class HttpClientPlain {
	//private  final Logger logger = LoggerFactory.getLogger(HttpClient.class);
	//public  final Logger logger = MyLogManager.getDisLoggerV2();
	//http
	private final String  HTTP_TIME_OUT="http.time.out";
	private final String  HTTP_MAX_CONNECTION="http.max.connection";
	private final String  HTTP_PER_ROUTE_CONNECTION="http.per.route.connection";
	// 编码方式
    private  final String CONTENT_CHARSET = "UTF-8";
	// 超时时间配置
    private  RequestConfig requestConfig;
    private  CloseableHttpClient httpClient;
    private static final int BUFFER_SIZE=2*1024*1024;
    static class Singleton{
    	private static final HttpClientPlain instance=new HttpClientPlain();
    }
	public static HttpClientPlain getInstance(){
		return Singleton.instance;
	}
	private    HttpClientPlain(){
		try {
			ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
				public boolean verify(String paramString, SSLSession paramSSLSession) {
					return true;
				}
			});
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", plainsf).register("https", sslsf).build();
			PoolingHttpClientConnectionManager connectionPoolMgr = new PoolingHttpClientConnectionManager(registry);

			// 最大连接数设置
			int maxTotal=1000;
			connectionPoolMgr.setMaxTotal(maxTotal);
			int perRoute =200;//到每个路由/主机的最大连接数
			connectionPoolMgr.setDefaultMaxPerRoute(perRoute);
			
			//设置buffer大小,默认是8k
			 ConnectionConfig connectionConfig = ConnectionConfig.custom()
		                .setBufferSize(BUFFER_SIZE).setFragmentSizeHint(BUFFER_SIZE)
		                .build();
			 
			// 生成客户端
			httpClient = HttpClients.custom().setDefaultConnectionConfig(connectionConfig).setConnectionManager(connectionPoolMgr).build();
			int timeOut=1000;
			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut)
						.setSocketTimeout(timeOut).setDecompressionEnabled(true).build();
		} catch (Throwable e) {
			//logger.error("init http client error!!!", e);
			e.printStackTrace();
		}
	}

	public String get(String url) throws HttpInvokeException {
		
		return get(url, null);
	}

	public String get(String url, Map<String, String> headers) 
			throws HttpInvokeException{
		HttpGet get = new HttpGet(url);
		return httpInvoke(get, headers);
	}
	
	public Map<String, String> getAllResponse(
			String url, Map<String, String> headers) throws HttpInvokeException{
		
		HttpGet get = new HttpGet(url);
		setHeader(get, headers);
		
		CloseableHttpResponse httpResp = null;
		try {
			httpResp = httpClient.execute(get);
			int status = httpResp.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != status) {
				////logger.error("http request return unexpect status:{} ,url:{}",status,url);
				throw new HttpInvokeException(status,"http request failed,return unexpect status:"+status);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("Content", EntityUtils.toString(httpResp.getEntity(), "UTF-8"));
			Header[] resHeaders = httpResp.getAllHeaders();
			if(resHeaders != null){
				for (Header header : resHeaders) {
					map.put(header.getName(), header.getValue());
				}
			}
			return map;
		} catch (ClientProtocolException e) {
			//logger.error("http request error(ClientProtocolException),url:{}",url,e);
			throw new HttpInvokeException(ErrorCode.INTER_ERROR, e.getMessage());
		} catch (IOException e) {
			//logger.error("http request error(IOException),url{}",url,e);
			throw new HttpInvokeException(ErrorCode.INTER_ERROR, e.getMessage());
		} finally {
			try {
				if (httpResp != null)
					httpResp.close();
			} catch (Throwable e) {
				//logger.error("http request error(httpResp close throw Throwable),url:{}",url,e);
			}
		}
//		return null;
	}
	
	public String post(String url, String body) throws HttpInvokeException{
		
		return post(url, body, null);
	}

	public String post(String url, String body, 
			Map<String, String> headers) throws HttpInvokeException{
		
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(body, CONTENT_CHARSET));
		return httpInvoke(post, headers);
	}
	
	public byte[] postBytes(String url, byte[] body, 
			Map<String, String> headers) throws HttpInvokeException{
		
		HttpPost method = new HttpPost(url);
		method.setEntity(new ByteArrayEntity(body));
		
		setHeader(method, headers);
		CloseableHttpResponse httpResp = null;
		try {
			httpResp = httpClient.execute(method);
			int status = httpResp.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != status) {
				//logger.error("http request return unexpect status:{} ,url:{}",status,url);
				throw new HttpInvokeException(status,"http request failed,return unexpect status:"+status);
			}
			
			return EntityUtils.toByteArray(httpResp.getEntity());
		} catch (ClientProtocolException e) {
			//logger.error("http request error(ClientProtocolException),url:{},error msg:{}",url,e.getMessage(),e);
			throw new HttpInvokeException(ErrorCode.INTER_ERROR, e.getMessage());
		} catch (IOException e) {
			//logger.error("http request error(IOException),url:{},error msg:{}",url,e.getMessage(),e);
			throw new HttpInvokeException(ErrorCode.INTER_ERROR, e.getMessage());
		} finally {
			try {
				if (httpResp != null)
					httpResp.close();
			} catch (Throwable e) {
				//logger.error("http request error(httpResp close throw IOException),url:{}",url,e);
			}
		}
//		return httpInvoke(post, headers);
	}
	
	public String post(String url, List<NameValuePair> nvps) throws HttpInvokeException{
		
		return post(url, nvps, null);
	}
	
	public String post(String url, List<NameValuePair> nvps, 
			Map<String, String> headers) throws HttpInvokeException{
		
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps, CONTENT_CHARSET));
		} 
		catch (UnsupportedEncodingException e) {
			//logger.error("http request error(UnsupportedEncodingException),url:{}",url,e);
			throw new HttpInvokeException(-1, "参数编码失败");
		}
		return httpInvoke(post, headers);
	}
	
	private String httpInvoke(HttpRequestBase method, Map<String, String> headers) 
		throws HttpInvokeException{
		
		setHeader(method, headers);
		
		CloseableHttpResponse httpResp = null;
		try {
			httpResp = httpClient.execute(method);
			int status = httpResp.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != status) {
				
				try{
					//logger.error("http request failed, return unexpect status:{},url:{},and response info:{}",status,method.getURI(),UniversalUtils.replaceBlank(EntityUtils.toString(httpResp.getEntity(), "UTF-8")));
					
				}catch(Exception e){
					////logger.error("http request failed,return unexpect status:{},url:{}",status,method.getURI());
				}
				throw new HttpInvokeException(status,"http request failed,return unexpect status:"+status);
			}
			////logger.info("entity str:{}",EntityUtils.toString(httpResp.getEntity(), "UTF-8"));
			return EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			//logger.error("http request Exception(ClientProtocolException),url:{},msg:{}",method.getURI(),e.getMessage(),e);
			throw new HttpInvokeException(ErrorCode.INTER_ERROR, e.getMessage());
		} catch (IOException e) {
			//logger.error("http request Exception(IOException),url:{},msg:{}",method.getURI(),e.getMessage(),e);
			throw new HttpInvokeException(ErrorCode.INTER_ERROR, e.getMessage());
		} catch (Throwable e) {
			//logger.error("http request error/Exception(Exception),url:{},msg:{}",method.getURI(),e.getMessage(),e);
			e.printStackTrace();
			throw new HttpInvokeException(ErrorCode.INTER_ERROR, e.getMessage());
			
		} finally {
			try {
				if (httpResp != null)
					
					httpResp.close();
			} catch (Throwable e) {
				//logger.error("http request error(httpResp close throw Throwable),url:{}",method.getURI(),e);
			}
		}
	}

	private void setHeader(HttpRequestBase method, Map<String, String> headers){
		method.setConfig(requestConfig);
		if(headers != null){
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				method.addHeader(key, headers.get(key));
			}
		}
	}
	private void destroyed(){
		try {
			this.httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//logger.error("try to close http client failed",e);
		}
	}
//	private Map<String, String> convertHeaders(Header[] headers) {
//		Map<String, String> headerMap = new HashMap<String, String>();
//		if (headers != null) {
//			for (Header header : headers) {
//				headerMap.put(header.getName(), header.getValue());
//			}
//		}
//		return headerMap;
//	}


}
