package bos.sshproject.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bos.sshproject.base.dao.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * ��̬���ʵ������
	 */
	public BaseDaoImpl() {
		//��ø���
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		//��ø����ϵķ�������
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
		
		//��ȡ�Ự����
		Session session = this.getSession();
		//ʹ��������ѯ�����һ����ѯ����
		Query query = session.getNamedQuery(queryName);
		
		//ΪHQL����е��ʺŸ�ֵ
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		query.executeUpdate();
	}


}
