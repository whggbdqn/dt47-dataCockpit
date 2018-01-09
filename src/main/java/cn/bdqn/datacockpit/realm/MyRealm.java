package cn.bdqn.datacockpit.realm;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bdqn.datacockpit.entity.Userinfo1;
import cn.bdqn.datacockpit.service.UserinfoService;
import cn.bdqn.datacockpit.service.impl.UserinfoServiceImpl;



public class MyRealm extends AuthorizingRealm{
	 @Autowired
	 UserinfoService userService ;
	
	/**
	 * 为当前登录的用户授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始授权");
		String phone=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.getRoles(phone));
		authorizationInfo.setStringPermissions(userService.getPermissions(phone));
		return authorizationInfo;
	}

	/**
	 * shiro的login方法，将UsernamePasswordToken对象传递给
	 * AuthenticationInfo方法中的AuthenticationToken，所以
	 * AuthenticationToken中封装的参数就是UsernamePasswordToken
	 * 中的phone和password
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//将AuthenticationTokenz转化为UsernamePasswordToken对象，以便拿到phone和password
		UsernamePasswordToken currentToken=(UsernamePasswordToken)token;
		//取出封装的username即为phone
		String phone=currentToken.getUsername();
		Userinfo1 user=userService.getByPhone(phone);
		//在数据库中，通过phone查询出角色信息
		if(user!=null) {
        AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),user.getRealname());
        SecurityUtils.getSubject().getSession().setAttribute("infos", user);
        return authcInfo;
		}
		return null;
	}

}
