package bos.sshproject.authority.web.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.authority.domain.Role;
import bos.sshproject.base.action.BaseAction;

@Controller
@Scope("prototype")
public class roleAction extends BaseAction<Role> {

	private String ids;
	
	public String add(){
		
		roleService.add(model,ids);
		return "list";
	}
	
	public String pageQuery() throws IOException{
		
		roleService.pageQuery(pageBean);
		
		//将pageBean转化为json数据
		this.writePageBean2Json(pageBean, new String[]{"functions","users","currentPage","detachedCriteria","pageSize"});
		return NONE;
		
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIds() {
		return ids;
	}
}
