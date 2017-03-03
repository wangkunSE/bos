package bos.sshproject.user.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.user.dao.IUserDao;
import bos.sshproject.user.domin.User;
import bos.sshproject.user.service.IUserService;
import bos.sshproject.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Override
	public User login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		password = MD5Utils.md5(password);
		
		return userDao.findByUsernameAndPassword(username,password);
	}

}
