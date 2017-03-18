package bos.sshproject.authority.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.authority.domain.Function;
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
	
	public String listajax() throws IOException{
		List<Role> list = roleService.findAll();
		String[] excludes = new String[]{"users","functions"};
		this.writeList2Json(list, excludes);
		return NONE;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIds() {
		return ids;
	}
}
