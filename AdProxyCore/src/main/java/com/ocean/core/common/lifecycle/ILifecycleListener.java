package com.ocean.core.common.lifecycle;


public interface ILifecycleListener {
 
    /**
     * 对生命周期事件进行处理
     * 
     * @param event 生命周期事件
     */
    public void lifecycleEvent(LifecycleEvent event);

}