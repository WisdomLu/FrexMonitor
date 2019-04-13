package com.ocean.core.common.lifecycle;


public enum LifecycleState {
 
    NEW, //新生
 
    INITIALIZING, INITIALIZED, //初始化
 
    STARTING, STARTED, //启动
 
    SUSPENDING, SUSPENDED, //暂停
 
    RESUMING, RESUMED,//恢复
 
    DESTROYING, DESTROYED,//销毁
 
    FAILED;//失败
 
}