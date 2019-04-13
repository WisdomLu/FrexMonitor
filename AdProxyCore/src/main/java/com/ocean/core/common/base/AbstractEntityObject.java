package com.ocean.core.common.base;

import java.io.Serializable;

/**
 * 描述：实体虚类
 * 
 */
public abstract class AbstractEntityObject  implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -3905401957230661196L;

	/**
	 * 要求子类必须实现hashCode方法
	 */
	@Override
	public abstract int hashCode();

	/**
	 * 要求子类必须实现equals方法
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * 要求子类必须实现hashCode方法
	 */
	@Override
	public abstract String toString();

}
