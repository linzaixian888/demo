package demo4;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultSecurityManager securityManager=new DefaultSecurityManager();
		/**
		 * SecurityManager接口继承了Authenticator，另外还有一个ModularRealmAuthenticator实现，其委托给多个Realm进行验证，验证规则通过AuthenticationStrategy接口指定，默认提供的实现：
				FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
				AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
				AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。
 		   ModularRealmAuthenticator默认使用AtLeastOneSuccessfulStrategy策略。
		 */
		ModularRealmAuthenticator authenticator=new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
//		authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
//		authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);
		List<Realm> realms=new ArrayList<Realm>();
		realms.add(new Demo4Realm1());
		realms.add(new Demo4Realm2());
		securityManager.setRealms(realms);
		
		SecurityUtils.setSecurityManager(securityManager);  
		
		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");  
	    try {  
	        //登录，即身份验证  
	        subject.login(token);  
	        System.out.println(subject.getPrincipals().asList());
	    } catch (AuthenticationException e) {  
	    	e.printStackTrace();
	        //身份验证失败  
	    }  
	    if(subject.isAuthenticated()){
	    	System.out.println("用户登陆成功");
	    }else{
	    	System.out.println("用户登陆失败");
	    }
	    //退出  
	    subject.logout();  
	}
}
