package bos.sshproject.staff.service;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.staff.domin.Staff;

public interface IStaffService {


	void save(Staff model);

	void pageQuery(PageBean pageBean);

}
