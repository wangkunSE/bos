package bos.sshproject.staff.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.staff.dao.IStaffDao;
import bos.sshproject.staff.domain.Staff;
import bos.sshproject.staff.service.IStaffService;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;

	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
		
	}

	@Override
	public void deleteBatch(String ids) {
		
		String[] staffIds = ids.split(",");
		for (String id : staffIds) {
			staffDao.executeUpdate("staff.delete", id);
		}
	}

	@Override
	public Staff findById(String id) {
		
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff dbStaff) {
		
		staffDao.update(dbStaff);
	}

	@Override
	public List<Staff> findListNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.ne("deltag", "1"));
		return staffDao.findByCriteria(detachedCriteria);
	}
}
