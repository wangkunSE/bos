package bos.sshproject.staff.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.domin.Staff;
import bos.sshproject.staff.dao.IStaffDao;
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
}
