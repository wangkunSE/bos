package bos.sshproject.authority.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import bos.sshproject.authority.dao.IFunctionDao;
import bos.sshproject.authority.domain.Function;
import bos.sshproject.base.dao.impl.BaseDaoImpl;
import bos.sshproject.base.page.PageBean;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findListByUserid(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		return this.getHibernateTemplate().find(hql,id);
	}


}
