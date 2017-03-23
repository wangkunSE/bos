package bos.sshproject.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
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

	@Resource
	private IdentityService identityService;
	/**
	 * ����һ����ɫ,ͬʱͬ����activiti��
	 */
	@Override
	public void add(Role role, String ids) {
		
		roleDao.save(role);
		
		//ʹ�ý�ɫ������Ϊ���id
		Group group = new GroupEntity(role.getName());
		identityService.saveGroup(group);
		
		String[] functionIds = ids.split(",");
		
		for (String id : functionIds) {
			Function function = new Function(id);
			//��ɫ����Ȩ��
			role.getFunctions().add(function);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		
		roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.list();
	}
}
