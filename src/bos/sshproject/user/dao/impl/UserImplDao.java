package bos.sshproject.user.dao.impl;

import org.springframework.stereotype.Repository;

import bos.sshproject.base.dao.impl.BaseDaoImpl;
import bos.sshproject.user.dao.IUserDao;
import bos.sshproject.user.domin.User;

@Repository
public class UserImplDao extends BaseDaoImpl<User> implements IUserDao {

}
