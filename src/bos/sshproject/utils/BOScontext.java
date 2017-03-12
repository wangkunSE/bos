package bos.sshproject.utils;

import org.apache.struts2.ServletActionContext;

import bos.sshproject.user.domin.User;

public class BOScontext {

	public static User getLoginUser(){
		
		return (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
}
