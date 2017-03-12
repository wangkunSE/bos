package bos.sshproject.bussiness.web.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.bussiness.domin.Noticebill;
import bos.sshproject.crm.Customer;
import bos.sshproject.user.domin.User;
import bos.sshproject.utils.BOScontext;

@Controller
@Scope("prototype")
public class noticebillAction extends BaseAction<Noticebill> {

	public String findCustomerByTelephone() throws IOException{
		Customer customer = customerService.findCustomerByPhoneNumber(model.getTelephone());
		
		String[] excludes = new String[]{};
		this.writeObject2Json(customer, excludes);
		return NONE;
	}
	
	/**
	 * 添加业务工单 尝试自动分单
	 * @return
	 */
	public String add(){
		User user = BOScontext.getLoginUser();
		model.setUser(user);
		noticebillService.save(model);
		return "addUI";
	}
}
