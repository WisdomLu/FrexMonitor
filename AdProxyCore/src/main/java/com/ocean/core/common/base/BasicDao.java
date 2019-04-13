package com.ocean.core.common.base;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inveno.base.BaseModel;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.search.CriteriaUtil;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;

@Repository
public class BasicDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public T getById(Class clazz, String id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	public <T extends BaseModel> T save(Object object) {
		getCurrentSession().save(object);
		return (T) object;
	}

	public void saveOrUpdate(Object o) {
		getCurrentSession().saveOrUpdate(o);
	}

	public void delete(Object object) {
		getCurrentSession().delete(object);
	}

	public <T extends BaseModel> T findById(Object id) {
		return (T) findById(id, getPojoClass());
	}

	public <T extends BaseModel> T findById(Object id,
			Class<? extends BaseModel> pojoClass) {
		return (T) getCurrentSession().get(pojoClass, (Serializable) id);
	}

	public <T> List<T> findByDetachedCriteria(DetachedCriteria criteria) {
		return (List<T>) criteria.getExecutableCriteria(getCurrentSession())
				.list();
	}
	public <T> List<T> findByDetachedCriteria(DetachedCriteria criteria,int start,int pageSize) {
		return (List<T>) criteria.getExecutableCriteria(getCurrentSession())
				.setFirstResult(start).setMaxResults(pageSize).list();
	}
	protected Class<? extends BaseModel> getPojoClass() {
		return null;
	}

	public <T> List<T> findByHql(String hql, List<?> parameters) {

		Query query = getCurrentSession().createQuery(hql);
		if (null != parameters) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	public <T> List<T> findBySql(String sql, List<?> parameters) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (null != parameters) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();

	}
	/**  the universal method to query insurance list with page
	@author Alex & E-mail:569246607@qq.com
	@date   2016-12-30
	@version 1.0 
	*/ 
	public <M extends Object> List<M > findList(Criteria c,Class<T> clazz,M pojo,int from,int pageSize) {
		if(c==null){
			c = getCurrentSession().createCriteria(clazz);
		}
		if(pojo!=null){//setting criterial
			c=this.setCriterial(pojo, c);
		}
		c.setFirstResult(from).setMaxResults(pageSize);
		List<Object> list=c.list();
		if(CollectionUtils.isEmpty(list)){
			return Collections.emptyList();
		}
		return (List)list;
	}
	/**  the universal method to query insurance list
	@author Alex & E-mail:569246607@qq.com
	@date   2016-12-30
	@version 1.0 
	*/ 
	public <M extends Object> List<M > findList(Criteria c,Class<T> clazz,M pojo) {
		if(c==null){
			c = getCurrentSession().createCriteria(clazz);
		}
		if(pojo!=null){//setting criterial
			c=this.setCriterial(pojo, c);
		}
		List<Object> list=c.list();
		if(CollectionUtils.isEmpty(list)){
			return Collections.emptyList();
		}
		return (List)list;
	}
	public <M extends Object> Long count(Criteria c,Class<T> clazz,M pojo){
		if(c==null){
			c = getCurrentSession().createCriteria(clazz);
		}
		if(pojo!=null){//setting criterial
			c=this.setCriterial(pojo, c);
		}
		c.setProjection(Projections.rowCount());
		Object obj=c.uniqueResult();
		return obj==null?0:(Long)c.uniqueResult();
	}
	/* 通用查询条件注入方法
	 * @author Alex 2015-10-28
	 * @return DetachedCriteria
	 * */
   public <M> Criteria  setCriterial(M obj,Criteria criteria){
	   if(obj==null){
		  return criteria; 
	   }
		CriteriaUtil<M> criteriaUtil=new CriteriaUtil<M>();
		try {
			criteria=criteriaUtil.setCriteria(obj, criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException(ErrorCode.INTER_ERROR,"set search condition fired exception...");
		}
		return criteria;
   }
}
