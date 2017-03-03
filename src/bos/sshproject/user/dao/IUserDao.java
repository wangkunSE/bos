package bos.sshproject.user.dao;

import bos.sshproject.base.dao.IBaseDao;
import bos.sshproject.user.domin.User;

public interface IUserDao extends IBaseDao<User> {

	User findByUsernameAndPassword(String username, String password);

}