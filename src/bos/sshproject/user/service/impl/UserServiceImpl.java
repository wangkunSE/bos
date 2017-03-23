package bos.sshproject.user.service.impl;


import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.authority.dao.IRoleDao;
import bos.sshproject.authority.domain.Role;
import bos.sshproject.base.page.PageBean;
import bos.sshproject.user.dao.IUserDao;
import bos.sshproject.user.domain.User;
import bos.sshproject.user.service.IUserService;
import bos.sshproject.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IdentityService identityService;
	@Override
	public User login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		password = MD5Utils.md5(password);
		
		return userDao.findByUsernameAndPassword(username,password);
	}
	
	@Override
	public void editPassword(String password, String id) {
		
		userDao.executeUpdate("editPassword", password,id);
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		
		userDao.pageQuery(pageBean);
	}

	@Override
	public void save(User model, String[] roleIds) {
		String password = model.getPassword();
		password = MD5Utils.md5(password);
		model.setPassword(password);
		
		org.activiti.engine.identity.User actUser = new UserEntity(model.getId());
		identityService.saveUser(actUser);
		
		userDao.save(model);
		for (String roleId : roleIds) {
			Role role = roleDao.findById(roleId);
			//用户关联角色
			model.getRoles().add(role);
			identityService.createMembership(actUser.getId(), role.getName());
		}
	}

}
