package bos.sshproject.staff.service;

import java.util.List;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.staff.domin.Staff;

public interface IStaffService {


	void save(Staff model);

	void pageQuery(PageBean pageBean);

	/**
	 * ÅúÁ¿É¾³ý
	 * @param ids
	 */
	void deleteBatch(String ids);

	Staff findById(String id);

	void update(Staff dbStaff);

	List<Staff> findListNotDelete();

}
