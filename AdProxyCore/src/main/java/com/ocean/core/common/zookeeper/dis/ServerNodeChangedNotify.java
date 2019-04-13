package com.ocean.core.common.zookeeper.dis;
public interface ServerNodeChangedNotify {
	public abstract void notify(String path) throws Exception ;
}
