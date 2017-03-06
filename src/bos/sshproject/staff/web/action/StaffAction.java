package bos.sshproject.staff.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.staff.domin.Staff;
import bos.sshproject.staff.service.IStaffService;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	
	@Autowired
	private IStaffService staffService;
	
	public String add(){
		
		staffService.save(model);
		return "list";
	}

}
