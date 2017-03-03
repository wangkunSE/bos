package bos.sshproject.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import bos.sshproject.base.dao.impl.BaseDaoImpl;
import bos.sshproject.user.dao.IUserDao;
import bos.sshproject.user.domin.User;

@Repository
public class UserImplDao extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		
		String hql = "FROM User where username=? and password=?";
		List<User> list = this.getHibernateTemplate().find(hql,username,password);
		if( list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
