package com.ocean.core.common.threadpool.workthread.listener;

import java.util.EventObject;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月2日 
      @version 1.0 
 */
public class TaskEventObject extends EventObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6340533354022726495L;
	private String hashCode;
	private String state;
    public TaskEventObject(Object source,String hashCode) {
		super(source);
		this.hashCode=hashCode;
		// TODO Auto-generated constructor stub
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
