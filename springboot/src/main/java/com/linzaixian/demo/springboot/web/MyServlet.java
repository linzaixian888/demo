package com.linzaixian.demo.springboot.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * @author linzaixian
 * @since 2017-06-30 15:08:07 
 */
@WebServlet(urlPatterns="/myServlet2",name="myServlet2")
public class MyServlet extends HttpServlet{

    @Override
    public void destroy() {
        System.out.println("Servlet destroy...");  
    }

    @Override
    public void init(ServletConfig arg0) throws ServletException {
        System.out.println("Servlet init...");  
        
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        System.out.println("service..."); 
        
    }

}
