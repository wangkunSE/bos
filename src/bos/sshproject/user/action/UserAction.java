package bos.sshproject.user.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.crm.Customer;
import bos.sshproject.crm.CustomerService;
import bos.sshproject.user.domain.User;
import bos.sshproject.user.service.IUserService;
import bos.sshproject.utils.MD5Utils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Resource
	private IUserService userService;
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String login(){
		
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		if(StringUtils.isNotBlank(key) && key.equals(checkcode) ){
			User user = userService.login(model);
			if(user != null){
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			}else{
				this.addActionError(this.getText("loginError"));
				return "login";
			}
		}else{
			this.addActionError(this.getText("validateCodeError"));
			return "login";
		}
	
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		
		return "login";
	}
	
	public String editPassword() throws IOException{
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String password = model.getPassword();
		password = MD5Utils.md5(password);
		String flag = "1";
		
		try {
			userService.editPassword(password,user.getId());
		} catch (Exception e) {
			flag = "0";
		}
		
		
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
	}
}
