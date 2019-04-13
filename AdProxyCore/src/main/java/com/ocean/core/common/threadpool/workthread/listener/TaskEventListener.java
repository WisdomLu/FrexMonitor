package com.ocean.core.common.threadpool.workthread.listener;

import java.util.EventListener;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月2日 
      @version 1.0 
 */
public interface TaskEventListener extends EventListener{
    public void dealEvent(TaskEventObject event);
}
