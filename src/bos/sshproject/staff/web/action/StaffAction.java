package bos.sshproject.staff.web.action;

import java.io.IOException;

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
	
	@Autowired
	private IStaffService staffService;
	
	//分页用
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	//添加取派员
	public String add(){
		
		staffService.save(model);
		return "list";
	}

	//分页查询取派员
	public String pageQuery() throws IOException{
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		staffService.pageQuery(pageBean);
		
		//将pageBean转化为json数据
		
		JsonConfig jsonConfig = new JsonConfig();
		//注意死循环 decidezone
		jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
		JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		
		return NONE;
	}
}
