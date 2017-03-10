package bos.sshproject.decidezone.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.decidezone.domin.Decidezone;

@Controller
@Scope("prototype")
public class decidedzoneAction extends BaseAction<Decidezone> {

	private String[] subareaid;
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	public String add(){
		
		decidedzoneService.add(model,subareaid);
		return "list";
	}
	
	public String pageQuery() throws IOException{
		
		decidedzoneService.pageQuery(pageBean);
		String[] excludes = new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidezones"};
		this.writePageBean2Json(pageBean, excludes);
		return NONE;
	}
}
