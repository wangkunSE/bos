package bos.sshproject.decidezone.web.action;

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
}
