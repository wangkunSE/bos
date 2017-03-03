package bos.sshproject.user.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.user.domin.User;
import bos.sshproject.user.service.IUserService;

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
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
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
}
