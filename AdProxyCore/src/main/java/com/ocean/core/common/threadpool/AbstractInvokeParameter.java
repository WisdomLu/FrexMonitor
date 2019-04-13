package com.ocean.core.common.threadpool;


import com.ocean.core.common.base.AbstractBaseEntity;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月21日 
      @version 1.0 
 */
public abstract class AbstractInvokeParameter      extends AbstractBaseEntity   implements Parameter{
	/**
	 * 
	 */

	private static final long serialVersionUID = -5257894584832821844L;

	public abstract boolean validate();
}
