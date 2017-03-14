package bos.sshproject.decidezone.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.crm.Customer;
import bos.sshproject.decidezone.domain.Decidezone;

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
	
	public String findnoassociationCustomers() throws IOException{
		List<Customer> list = customerService.findnoassociationCustomers();
		String[] excludes = new String[]{};
		this.writeList2Json(list, excludes);
		
		return NONE;
	}
	
	public String findassociationCustomers() throws IOException{
		List<Customer> list = customerService.findhasassociationCustomers(model.getId());
		String[] excludes = new String[]{};
		this.writeList2Json(list, excludes);
		return NONE;
	}
	
	private Integer[] customerIds;
	public void setCustomerIds(Integer[] customerIds) {
		this.customerIds = customerIds;
	}
	public String assigncustomer(){
		
		customerService.assignCustomersToDecidedZone(customerIds, model.getId());
		return "list";
		
	}
}
