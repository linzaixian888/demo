package com.linzaixian.demo.springboot.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

/**
 * @author linzaixian
 * @since 2017-06-30 15:11:53 
 */
@WebFilter(urlPatterns="/*")
public class MyFilter implements Filter{

    @Override
    public void destroy() {
        System.out.println("filter destroy...");  
        
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        System.out.println("doFilter...");  
        arg2.doFilter(arg0, arg1);
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("filter init...");  
        
    }

}
