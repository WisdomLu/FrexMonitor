package com.ocean.core.common.threadpool.workthread;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月21日 
      @version 1.0 
 */
public abstract class AbstractTaskDisServer {
	public static class Args extends AbstractServerArgs<Args>{
		
	}
	public static abstract class AbstractServerArgs <T extends AbstractServerArgs<T>>{
		
	}
	public AbstractTaskDisServer(AbstractServerArgs args){
		
	}
	protected boolean closed=false;
	protected volatile boolean stopped_ = false;

	public boolean isStopped_() {
		return stopped_;
	}
	public void setStopped_(boolean stopped_) {
		this.stopped_ = stopped_;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}
