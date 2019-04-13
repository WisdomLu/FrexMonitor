package com.ocean.core.common.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.inveno.base.BaseModel;
import com.inveno.base.Pagin;
import com.inveno.exception.BusinessException;
import com.inveno.hibernate.resulttranformer.ColumnToBean;
import com.inveno.util.CollectionUtils;
import com.inveno.util.DetachedCriteriaUtil;
import com.inveno.util.PainUtils;
import com.inveno.util.StringUtil;

@SuppressWarnings("unchecked")
public abstract class AbstractBaseDAO extends HibernateDaoSupport implements IBaseDAO {
	protected final Logger log = LoggerFactory.getLogger(AbstractBaseDAO.class);
	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	public <T extends BaseModel> T save(Object object) {
		this.getHibernateTemplate().save(object);
		return (T) object;
	}

	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}

	public <T extends BaseModel> T update(Object object) {
		this.getHibernateTemplate().update(object);
		return (T) object;
	}

	public <T extends BaseModel> T saveOrUpdate(Object object) {
		this.getHibernateTemplate().saveOrUpdate(object);
		return (T) object;
	}

/*	public void saveAndUpdate(final Collection entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}*/

	public <T extends BaseModel> T findById(Object id) {
		return (T) findById(id, getPojoClass());
	}


	public <T extends BaseModel> T findById(Object id,Class<? extends BaseModel> pojoClass) {
		try {
			return (T) getHibernateTemplate().get(pojoClass, (Serializable) id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessException("findById出异常.",e);
		}
		
	}

	public Object merge(Object object) {
		return getHibernateTemplate().merge(object);
	}


	public void flush() {
		getHibernateSession().flush();
	}

	public void clearSession() {
		getHibernateSession().clear();
	}


	public <T> List<T> findByDetachedCriteria(DetachedCriteria criteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}


	public <T> List<T> findByDetachedCriteria(DetachedCriteria criteria,int begin, int pageSize) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria,begin, pageSize);
	}


	public int getRowCount(Pagin pagin) {
		if (pagin.getDetachedCriteria() == null) {
			throw new BusinessException("detachedCriteria对象为空");
		}
		String idName = pagin.getColumForCount();
		if (StringUtil.isEmpty(idName)) {
			idName = "id";
		}
		if (pagin.isCountWithDisctinct()) {// 是否使用distinct
			pagin.getDetachedCriteria().setProjection(
					Projections.projectionList().add(
							Projections.countDistinct(idName)));
		} else {
			pagin.getDetachedCriteria()
					.setProjection(
							Projections.projectionList().add(
									Projections.count(idName)));
		}

		List list = findByDetachedCriteria(pagin.getDetachedCriteria(), 0, 1);
		if (list == null || list.size() == 0){
			return 0;
		}
		return Integer.valueOf(list.get(0).toString());
	}
	

	private void appendPermissionCondition(Pagin pagin){
		this.selectColumn(pagin.getDetachedCriteria(), pagin.getSelectedColumns(),pagin.getPojoClass(), pagin.getAlias(),pagin);
	}

	protected Class<? extends BaseModel> getPojoClass() {
		return null;
	}

	public <T extends BaseModel> List<T> findObjectByHql(String queryString,
			List<Object> conditions, Class<T> pojoClass, Pagin pagin) {

		Query query = null;
		int i = 0, j = 0;
		if (conditions != null) {
			i = conditions.size();
		}
		// 查询总行数
		if (pagin.isRecountTotalRow() || pagin.getTotalRows() <= 0) {
			int fromIndex = queryString.toLowerCase().indexOf("from");
			StringBuilder hql = new StringBuilder("select count(");
			if (StringUtil.isNotEmpty(pagin.getAlias())) {
				hql.append(pagin.getAlias()).append(".");
			}
			hql.append(pagin.getColumForCount()).append(") ").append(queryString.substring(fromIndex));
			query = getSessionFactory().getCurrentSession().createQuery(hql.toString());

			if (i > 0) {
				// 绑定查询条件
				for (Object object : conditions) {
					query.setParameter(j++, object);
				}
				j = 0;
			}
			List result = query.list();
			if (!result.isEmpty()) {
				pagin.setTotalRows(Integer.valueOf(result.get(0).toString()));
			}

		}

		// 查询结果集
		query = getSessionFactory().getCurrentSession().createQuery(queryString);
		if (i > 0) {
			// 绑定查询条件
			for (Object object : conditions) {
				query.setParameter(j++, object);
			}
		}
		query.setFirstResult(pagin.getFromRow());
		query.setMaxResults(pagin.getPageSize());
		query.setResultTransformer(Transformers.aliasToBean(pojoClass));
		pagin.setObjectList(query.list());

		return (List<T>) pagin.getObjectList();
	}

	public <T extends BaseModel> List<T> findObjectBySql(String queryString,
			List<?> conditions, Class<T> pojoClass) {
		Query query = null;
		int i = 0, j = 0;
		if (conditions != null) {
			i = conditions.size();
		}
		// 查询结果集
		query = getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		if (i > 0) {
			// 绑定查询条件
			j = 0;
			for (Object object : conditions) {
				query.setParameter(j++, object);
			}
		}
		query.setResultTransformer(new ColumnToBean(pojoClass));
		return (List<T>) query.list();
	}

	public void  findObjectBySql(String queryString, List<?> conditions, Pagin pagin) {
		
		Query query = null;
		int i = 0, j = 0;
		if (conditions != null) {
			i = conditions.size();
		}
		 //查询总行数
		if(pagin.isRecountTotalRow() || pagin.getTotalRows()<=0){
			StringBuilder sql = new StringBuilder(50);
			if(!pagin.isReport()){//不是报表查询
				int fromIndex = queryString.toLowerCase().indexOf("from");
				sql = new StringBuilder("select count(");
				if(StringUtil.isNotEmpty(pagin.getAlias())){
					sql.append(pagin.getAlias()).append(".");
				}
				sql.append(pagin.getColumForCount()).append(")").append(queryString.substring(fromIndex));
			}else{//是报表查询
				sql.append("select count( * ) from ( "+queryString+" ) as v");
			}
			
			query = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if(i>0){
				 //绑定查询条件
				for (Object object : conditions) {
					 query.setParameter(j++, object);
				}
			}
			pagin.setTotalRows(Integer.valueOf(query.list().get(0).toString()));
		 }
		 

		// 查询结果集
		query = getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		if (i > 0) {
			// 绑定查询条件
			j = 0;
			for (Object object : conditions) {
				query.setParameter(j++, object);
			}
		}
		query.setFirstResult(pagin.getFromRow());
		query.setMaxResults(pagin.getPageSize());
		query.setResultTransformer(new ColumnToBean(pagin.getPojoClass()));
		pagin.setObjectList(query.list());
	}

	public List<Object[]> findListObjectsBySql(String queryString,Pagin pagin){
		//查询SQL数据的总行数
		Query query = null;
		if(pagin.isRecountTotalRow() || pagin.getTotalRows()<=0){
			StringBuilder sql = new StringBuilder(50);
			
			sql.append("select count( * ) from ( "+queryString+" ) as v");
			
			
			query = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			pagin.setTotalRows(Integer.valueOf(query.list().get(0).toString()));
		 }
		//为SQL后面添加查询从多少行开始，查询多少行的条件
		queryString = queryString + " LIMIT " + pagin.getFromRow() + " , " + pagin.getPageSize();
		List<Object[]> list =  findBySql(queryString,null);//返回查询结果
		return list;
	}

	public <T> List<T> findByPaginN(Pagin pagin) {
		if (pagin.getDetachedCriteria() == null) {
			throw new RuntimeException("查询时pagin中的detachedCriteria对象为空");
		}
		
		try {
			PainUtils.initDefaultOrders(pagin, null);
			//组装分字段、权限查询功能
			appendPermissionCondition(pagin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> list = findByDetachedCriteria(pagin.getDetachedCriteria(), pagin
				.getFromRow(), pagin.getPageSize());
		pagin.setObjectList(list);
		
		if (pagin.isRecountTotalRow() || pagin.getTotalRows() <= 0) {
			pagin.setTotalRows(getRowCount(pagin));
		}
		
		return (List<T>) pagin.getObjectList();
	}

	private void selectColumn(DetachedCriteria criteria, String[] columName,
			Class<? extends BaseModel> pojoClass, String alias, Pagin pagin) {
		if (pagin.getPojoClass() == null) {
			pagin.setPojoClass(pojoClass);
		}
		if (pagin.getAlias() == null) {
			pagin.setAlias(alias);
		}

		DetachedCriteriaUtil.selectColumn(criteria, columName, pojoClass, pagin
				.isJoinTable());
	}

/*	public <T> List<T> findBySql(String sql, List<?> parameters) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(sql);
			if (parameters != null && parameters.size() > 0) {
				stmt = getConnection().prepareStatement(sql);
				int size = parameters.size();
				for (int i = 0; i < size; i++) {
					stmt.setObject(i + 1, parameters.get(i));
				}
			}
			rs = stmt.executeQuery();
			if (rs != null) {
				List<Object[]> list = new ArrayList<Object[]>();
				int columnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					Object[] obj = new Object[columnCount];
					for (int j = 0; j < columnCount; j++) {
						obj[j] = rs.getObject(j + 1);
					}
					list.add(obj);
				}
				return (List<T>) list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return new ArrayList<T>(0);
	}*/
	public <T> List<T> findBySql(String sql, List<?> parameters) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (null != parameters) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();

	}

	public <T> List<T> findByHql(String hql, List<?> parameters) {
		if (parameters != null) {
			return (List<T>) getHibernateTemplate().find(hql,
					parameters.toArray());
		} else {
			return (List<T>) getHibernateTemplate().find(hql, null);
		}

	}

	public void excuteSql(String sql, List<?> parameters) {
		SQLQuery query =getSessionFactory().getCurrentSession().createSQLQuery(sql);
		// 创建Query查询对象
		if (parameters != null) {
			// 绑定查询条件
			int size = parameters.size();
			for (int i = 0; i < size; i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		query.executeUpdate();
	}

	public void excuteHql(String hql, List<?> parameter) {
		if (parameter == null) {
			this.getHibernateTemplate().bulkUpdate(hql);
		} else {
			this.getHibernateTemplate().bulkUpdate(hql, parameter.toArray());
		}
	}

	public void deleteAll(Collection<? extends BaseModel> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	public int delete(Class<? extends BaseModel> pojoClass, String[] ids) {
		return delete(pojoClass, "id", ids);
	}

	public int delete(Class<? extends BaseModel> pojoClass,
			String propertyName, String[] propertyValues) {
		if (CollectionUtils.isNotEmpty(propertyValues)) {
			String hql = "DELETE " + pojoClass.getName()
					+ " as THIS_0 WHERE THIS_0." + propertyName + " in ("
					+ StringUtil.getPlaceHoldersForIn(propertyValues.length)
					+ ")";
			return getHibernateTemplate().bulkUpdate(hql, propertyValues);
		}
		return 0;
	}

	private Session getHibernateSession() {
		return super.getSessionFactory().getCurrentSession();
	}
/*	@SuppressWarnings("deprecation")
	public Connection getConnection() {
		return getHibernateSession().connection();
	}*/

	public <T extends BaseModel> List<T> findObjectBySql(String queryString, List<?> conditions, Pagin pagin, Class<T> pojoClass) {
		
		Query query = null;
		int i = 0, j = 0;
		if (conditions != null) {
			i = conditions.size();
		}
		 //查询总行数
		if(pagin.isRecountTotalRow() || pagin.getTotalRows()<=0){
			StringBuilder sql = new StringBuilder();
			if(!pagin.isReport()){//不是报表查询
				int fromIndex = queryString.toLowerCase().indexOf("from");
				sql = new StringBuilder("select count(");
				if(StringUtil.isNotEmpty(pagin.getAlias())){
					sql.append(pagin.getAlias()).append(".");
				}
				//sql.append(pagin.getColumForCount()).append(")").append(queryString.substring(fromIndex));
				sql.append("*").append(")").append(queryString.substring(fromIndex));
			}else{//是报表查询
				sql.append("select count( * ) from ( "+queryString+" ) as v");
			}
			
			query = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
			if(i>0){
				 //绑定查询条件
				for (Object object : conditions) {
					 query.setParameter(j++, object);
				}
			}
			pagin.setTotalRows(Integer.valueOf(query.list().get(0).toString()));
		 }
		 

		// 查询结果集
		query = getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		if (i > 0) {
			// 绑定查询条件
			j = 0;
			for (Object object : conditions) {
				query.setParameter(j++, object);
			}
		}
		query.setFirstResult(pagin.getFromRow());
		query.setMaxResults(pagin.getPageSize());
		query.setResultTransformer(new ColumnToBean(pojoClass));
		List<T> list = (List<T>) query.list();
		pagin.setObjectList(list);
		return (List<T>) list;
	}
	

}
