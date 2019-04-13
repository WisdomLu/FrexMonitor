package com.ocean.persist.service.proxy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.base.AbstractBaseService;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.dao.proxy.ProxyDao;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.persist.model.proxy.PositionMapping;
import com.ocean.persist.model.proxy.PositionTarget;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
@Component(value="proxyService")
public class ProxyServiceImpl extends AbstractBaseService  implements ProxyService{
	public List<String> getPIdsByZPoseId(String zPoseId) {
		ProxyDao dao=(ProxyDao)SystemContext.getServiceHandler().getService(ProxyDao.class);
		Criteria mc=dao.getCurrentSession().createCriteria(PositionMapping.class);
		mc.add(Restrictions.eq("zpid", zPoseId));//zk ssp 广告位id
		mc.add(Restrictions.eq("state",1));
		mc.setProjection(Projections.property("dpid"));
		mc.setProjection(Projections.groupProperty("dpid"));
		List<Object> mList=dao.findList(mc,null, null);
		if(CollectionUtils.isEmpty(mList)){
			throw new BusinessException(ErrorCode.INTER_ERROR,"get proxy position mapping by zpid from db is empty!");
		}
		return (List)mList;
	}
	public DSPPosition getPoseByIds(List<String> ids,String joinDSP,String ppid) {//ppid,三方平台广告位id
		ProxyDao dao=(ProxyDao)SystemContext.getServiceHandler().getService(ProxyDao.class);
		Criteria c=dao.getCurrentSession().createCriteria(DSPPosition.class);
		c.add(Restrictions.in("id",ids));
		c.add(Restrictions.eq("dsp",joinDSP));
		if(StringUtils.isNotEmpty(ppid)){
			//c.add(Restrictions.eq("pos",ppid));
			c.add(Restrictions.eq("id",ppid));
		}
		
		c.add(Restrictions.eq("state",1));
		List<Object> list=dao.findList(c,null, null);
		if(CollectionUtils.isEmpty(list)){
			throw new BusinessException(ErrorCode.INTER_ERROR,"get proxy position from db is empty!");
		}
		
		return (DSPPosition)list.get(0);//目前是一个
	}
	public DSPPosition getPoseByZPoseId (String zPoseId,String joinDSP,String ppid) {//ppid,三方平台广告位id
		return getPoseByIds(getPIdsByZPoseId(zPoseId),joinDSP,ppid);
	}
	public PositionTarget getPositionTarget(String dspPoseId) {
		// TODO Auto-generated method stub
		ProxyDao dao=(ProxyDao)SystemContext.getServiceHandler().getService(ProxyDao.class);
		Criteria c=dao.getCurrentSession().createCriteria(PositionTarget.class);
		c.add(Restrictions.eq("dpid",dspPoseId));
		List<Object> list=dao.findList(c,null, null);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return (PositionTarget)list.get(0);
	}


}
