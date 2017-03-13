package bos.sshproject.bussiness.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.bussiness.domin.Workordermanage;

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
}
