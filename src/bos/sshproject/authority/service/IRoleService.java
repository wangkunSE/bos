package bos.sshproject.authority.service;

import bos.sshproject.authority.domain.Role;
import bos.sshproject.base.page.PageBean;

public interface IRoleService {

	void add(Role model, String ids);

	void pageQuery(PageBean pageBean);

}
