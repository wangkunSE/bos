package bos.sshproject.staff.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.base.page.PageBean;
import bos.sshproject.staff.domin.Staff;
import bos.sshproject.staff.service.IStaffService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	
	
	
	//ɾ����
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}

	//���ȡ��Ա
	public String add(){
		
		staffService.save(model);
		return "list";
	}

	//��ҳ��ѯȡ��Ա
	public String pageQuery() throws IOException{
		
		staffService.pageQuery(pageBean);
		
		//��pageBeanת��Ϊjson����
		this.writePageBean2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	
	/**
	 * ����ɾ������(�߼�ɾ��)
	 * @param ids
	 * @return
	 */
	public String delete(){
		
		staffService.deleteBatch(ids);
		
		return "list";
	}
	
	public String edit(){
		
		//�Ȳ�ѯ 
		Staff dbStaff = staffService.findById(model.getId());
		//�ٰ����ύ�Ĳ������и���
		dbStaff.setName(model.getName());
		dbStaff.setTelephone(model.getTelephone());
		dbStaff.setStation(model.getStation());
		dbStaff.setHaspda(model.getHaspda());
		dbStaff.setStandard(model.getStandard());
		
		staffService.update(dbStaff);
		return "list";
	}
	
	public String listAjax() throws IOException{
		
		List<Staff> list = staffService.findListNotDelete();
		String[] excludes = new String[]{"decidedzones"};
		this.writeList2Json(list, excludes);
		return NONE;
	}
}
