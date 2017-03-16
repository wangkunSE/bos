package bos.sshproject.authority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.authority.dao.IRoleDao;
import bos.sshproject.authority.domain.Function;
import bos.sshproject.authority.domain.Role;
import bos.sshproject.authority.service.IRoleService;
import bos.sshproject.base.page.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;

	@Override
	public void add(Role role, String ids) {
		
		roleDao.save(role);
		String[] functionIds = ids.split(",");
		
		for (String id : functionIds) {
			Function function = new Function(id);
			//角色关联权限
			role.getFunctions().add(function);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		
		roleDao.pageQuery(pageBean);
	}
}
