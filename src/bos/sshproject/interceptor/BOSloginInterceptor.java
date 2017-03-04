package bos.sshproject.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import bos.sshproject.user.domin.User;

/**
 * �Զ���struts������,ʵ��δ��¼�Զ���ת����¼ҳ��
 * @author Administrator
 *
 */
public class BOSloginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if( user== null){
			
			return "login";
		}
		return invocation.invoke();
	}

}
