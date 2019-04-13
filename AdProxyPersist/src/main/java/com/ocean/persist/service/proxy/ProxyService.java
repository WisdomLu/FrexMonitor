package com.ocean.persist.service.proxy;

import java.util.List;

import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.persist.model.proxy.PositionMapping;
import com.ocean.persist.model.proxy.PositionTarget;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
public interface ProxyService {
	public List<String> getPIdsByZPoseId(String zPoseId);
	public DSPPosition getPoseByIds(List<String> ids,String joinDSP,String ppid);
	public DSPPosition getPoseByZPoseId (String zPoseId,String joinDSP,String ppid);
	public PositionTarget getPositionTarget(String dspPoseId);
}
