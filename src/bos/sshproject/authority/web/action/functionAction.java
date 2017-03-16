package bos.sshproject.authority.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.authority.domain.Function;
import bos.sshproject.base.action.BaseAction;

@Controller
@Scope("prototype")
public class functionAction extends BaseAction<Function> {

	public String pageQuery() throws IOException{
		pageBean.setCurrentPage(Integer.parseInt(model.getPage()));
		functionService.pageQuery(pageBean);
		
		//将pageBean转化为json数据
		this.writePageBean2Json(pageBean, new String[]{"function","functions","roles","currentPage","detachedCriteria","pageSize"});
		return NONE;
		
	}
	
	public String listajax() throws IOException{
		
		List<Function> list = functionService.findAll();
		String[] excludes = new String[]{"function","functions","roles","currentPage","detachedCriteria","pageSize"};
		this.writeList2Json(list, excludes);
		return NONE;
	}
	
	public String add(){
		functionService.add(model);
		return "list";
	}
}
