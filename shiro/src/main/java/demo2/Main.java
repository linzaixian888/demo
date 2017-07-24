package demo2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;



public class Main {
	public static void main(String[] args) {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();  
		securityManager.setRealm(new Demo2Ream());
	    SecurityUtils.setSecurityManager(securityManager);  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");  
	    try {  
	        //登录，即身份验证  
	        subject.login(token);  
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
