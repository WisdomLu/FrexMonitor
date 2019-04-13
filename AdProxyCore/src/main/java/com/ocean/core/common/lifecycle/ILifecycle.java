package com.ocean.core.common.lifecycle;

public interface ILifecycle {
	 
    /**
     * 初始化
     * 
     * @throws LifecycleException
     */
    public void init() throws LifecycleException;
 
    /**
     * 启动
     * 
     * @throws LifecycleException
     */
    public void start() throws LifecycleException;
 
    /**
     * 暂停
     * 
     * @throws LifecycleException
     */
    public void suspend() throws LifecycleException;
 
    /**
     * 恢复
     * 
     * @throws LifecycleException
     */
    public void resume() throws LifecycleException;
 
    /**
     * 销毁
     * 
     * @throws LifecycleException
     */
    public void destroy() throws LifecycleException;
 
    /**
     * 添加生命周期监听器
     * 
     * @param listener
     */
    public void addLifecycleListener(ILifecycleListener listener);
 
    /**
     * 删除生命周期监听器
     * 
     * @param listener
     */
    public void removeLifecycleListener(ILifecycleListener listener);
 
}