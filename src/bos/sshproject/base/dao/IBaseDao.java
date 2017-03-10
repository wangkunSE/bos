package bos.sshproject.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import bos.sshproject.base.page.PageBean;

public interface IBaseDao<T> {
	
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> list();
	
	public void saveOrUpdate(T entity);
	public void executeUpdate(String queryName, Object... objects);
	
	/**
	 * 通用分页查询方法
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
