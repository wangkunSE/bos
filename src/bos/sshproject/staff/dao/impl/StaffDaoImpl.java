package bos.sshproject.staff.dao.impl;

import org.springframework.stereotype.Repository;

import bos.sshproject.base.dao.impl.BaseDaoImpl;
import bos.sshproject.staff.dao.IStaffDao;
import bos.sshproject.staff.domin.Staff;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao<Staff> {

}
