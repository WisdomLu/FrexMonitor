package com.ocean.persist.api.proxy;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月20日 
      @version 1.0 
 */
public abstract class AdPullerBase implements AdPuller{
	public  final Logger logger =MyLogManager.getLogger();
	public void invoke(AbstractInvokeParameter param){}
}
