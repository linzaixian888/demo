package demo2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Main2 {
	public static void main(String[] args) {
		 //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<org.apache.shiro.mgt.SecurityManager> factory =  
	            new IniSecurityManagerFactory("classpath:demo2/shiro.ini");  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1234");  
	    try {  
	        //4、登录，即身份验证  
	        subject.login(token);  
	    } catch (AuthenticationException e) {  
	    	e.printStackTrace();
	        //5、身份验证失败  
	    }  
	    if(subject.isAuthenticated()){
	    	System.out.println("用户登陆成功");
	    }else{
	    	System.out.println("用户登陆失败");
	    }
	    //6、退出  
	    subject.logout();  
	}
}
