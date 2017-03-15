package bos.sshproject.authority.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import bos.sshproject.user.dao.IUserDao;
import bos.sshproject.user.domain.User;

public class BOSrealm  extends AuthorizingRealm{

	@Resource
	private IUserDao userDao;
	/**
	 * ��֤����
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername(); 
		User user = userDao.findByUsername(username);
		if(user == null){
			//�û���������
			return null;
		}else{
			//�û�������
			String password = user.getPassword();
			//��������֤��Ϣ����
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getClass().getSimpleName());
			return info;
		}
	}

	/**
	 * ��Ȩ����
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//
		info.addStringPermission("staff");
		
		info.addRole("role");
		return info;
	}


}
