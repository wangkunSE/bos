package bos.sshproject.user.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
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
	
	/**
	 * 使用shiro认证
	 * @return
	 */
	public String login(){
		
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		if(StringUtils.isNotBlank(key) && key.equals(checkcode) ){
				//验证码正确
				//获得当前用户对象
				 Subject subject = SecurityUtils.getSubject();//未认证
				 String password = model.getPassword();
				 password = MD5Utils.md5(password);
				 
				 AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), password);
			try {
				 subject.login(token);
			} catch (UnknownAccountException e) {
				e.printStackTrace();
				this.addActionError(this.getText("usernamenotfound"));
				return "login";
			}catch (Exception e) {
				e.printStackTrace();
				this.addActionError(this.getText("loginError"));
				return "login";
			}
			//获取认证信息对象中存储的user
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return "home";
			 
		}else{
			this.addActionError(this.getText("validateCodeError"));
			return "login";
		}
	
	}
	public String login_back(){
		
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
