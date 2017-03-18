package bos.sshproject.authority.realm;

import java.util.List;

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

import bos.sshproject.authority.dao.IFunctionDao;
import bos.sshproject.authority.domain.Function;
import bos.sshproject.user.dao.IUserDao;
import bos.sshproject.user.domain.User;

public class BOSrealm  extends AuthorizingRealm{

	@Resource
	private IUserDao userDao;
	@Resource
	private IFunctionDao functionDao;
	/**
	 * 认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername(); 
		User user = userDao.findByUsername(username);
		if(user == null){
			//用户名不存在
			return null;
		}else{
			//用户名存在
			String password = user.getPassword();
			//创建简单认证信息对象
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getClass().getSimpleName());
			return info;
		}
	}

	/**
	 * 授权方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//根据当前登录用户查询其对应的权限
		User user = (User) principals.getPrimaryPrincipal();
		List<Function> list = null;
		if( "admin".equals(user.getUsername())){
			//当前用户是超级管理员,获取所有权限
			list = functionDao.list();
		}else{
			//普通用户
			list = functionDao.findListByUserid(user.getId());
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}


}
