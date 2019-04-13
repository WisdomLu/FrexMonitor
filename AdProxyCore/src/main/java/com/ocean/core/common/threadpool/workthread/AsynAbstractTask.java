package com.ocean.core.common.threadpool.workthread;
import org.apache.logging.log4j.Logger;
import com.ocean.core.common.system.MyLogManager;



/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月26日 
      @version 1.0 
 */
public abstract class AsynAbstractTask<T,M> extends Task{
	private boolean isValid=true;
	//private CountDownLatch countDownLatch  =  new  CountDownLatch (1) ;
	public static final Logger LOGGER = MyLogManager.getLogger();
	protected T param;
	protected M response;
	public  T getParam(){
		return param;
	}
	public  void setParam(T param) {
		this.param=param;
	}
	public final Object validFlag=new Object();
	public abstract String reqFormat(); 
	public abstract byte[] reqByteFormat();
	public abstract  M respFormat(String result)throws Exception;
/*    public  M respFormat(String result)throws Exception{
		M resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=JsonUtils.toBean(result, (Class < M > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
			}
		}catch (Throwable e) {
			LOGGER.error("Task {} parse respose data error(Exception),msg:{}",this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}

		return this.getResponse();
    }*/
    public abstract M respFormat(byte[] result);
	public boolean isValid() {
		//synchronized(validFlag){//就算是少数由于同步，多余执行也没关系，可以节省额外很多资源，所以无需同步
			 return isValid;
		//}
		
	}
	public void setValid(boolean isValid) {
		//synchronized(validFlag){
		    this.isValid = isValid;
		//}
	}
	public M getResponse(){
		return response;
	}
	public M getResponseAsyn(int timeOut) throws InterruptedException {
		if(this.response==null){
			synchronized(this){
				this.wait(timeOut);
			}
		}

		return response;
	}
	public void setResponse(M response) {

		this.response = response;
		synchronized(this){
			this.notify();
		}
	}

}
