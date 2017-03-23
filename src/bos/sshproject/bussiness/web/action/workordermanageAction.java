package bos.sshproject.bussiness.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.bussiness.domain.Workordermanage;

@Controller
@Scope("prototype")
public class workordermanageAction extends BaseAction<Workordermanage> {

	public String add() throws IOException{
		
		workordermanageService.save(model);
		Workordermanage workordermanage = workordermanageService.findByID(model.getId());
		
		if(workordermanage!=null){
			ServletActionContext.getResponse().getWriter().print("1");
		}else{
			ServletActionContext.getResponse().getWriter().print("0");
		}
		return NONE;
	}
	
	/**
	 * 查询start为0的工作单
	 * @return
	 */
	public String list(){
		List<Workordermanage> list = workordermanageService.findListNotStart();
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
}
