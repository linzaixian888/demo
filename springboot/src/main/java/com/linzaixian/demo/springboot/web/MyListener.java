package com.linzaixian.demo.springboot.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;

import org.springframework.stereotype.Component;

/**
 * @author linzaixian
 * @since 2017-06-30 15:15:13 
 */
@WebListener
public class MyListener implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Listener Destroyed...");  
        
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Listener Initialized...");  
        
    }

}
