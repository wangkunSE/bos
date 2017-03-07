package bos.sshproject.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bos.sshproject.base.dao.IBaseDao;
import bos.sshproject.base.page.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 动态获得实体类型
	 */
	public BaseDaoImpl() {
		//获得父类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类上的泛型数组
		Type[] actualTypeArguments = type.getActualTypeArguments();
		
		entityClass = (Class<T>) actualTypeArguments[0];
		
	}
	@Override
	public void save(T entity) {
		
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	@Override
	public T findById(Serializable id) {
		
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		String hql = "FROM " + entityClass.getSimpleName();
		return this.getHibernateTemplate().find(hql);
	}
	@Override
	public void executeUpdate(String queryName, Object... objects) {
		
		//获取会话对象
		Session session = this.getSession();
		//使用命名查询语句获得一个查询对象
		Query query = session.getNamedQuery(queryName);
		
		//为HQL语句中的问号赋值
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		query.executeUpdate();
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		//查总行数
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		pageBean.setTotal(list.get(0).intValue());
		
		//查当前展示的数据集合
		detachedCriteria.setProjection(null);
		//重置表和类的映射关系
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		//当前页展示的数据集合
		int firstResult = (currentPage - 1)* pageSize;
		int maxResults = pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResult,maxResults);
		pageBean.setRows(rows);
	}
	@Override
	public void saveOrUpdate(T entity) {
		
		this.getHibernateTemplate().saveOrUpdate(entity);
	}


}
