package com.linzaixian.demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.linzaixian.demo.springboot.web.MyServlet;

/**
 * @author linzaixian
 * @since 2017-06-30 11:34:02 
 */
@Configuration
@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
@ComponentScan("com.linzaixian.demo.springboot.controller")
@ServletComponentScan(basePackages="com.linzaixian.demo.springboot.web")
@ImportResource({"classpath:spring.xml"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean=new ServletRegistrationBean(new MyServlet());
        bean.setEnabled(true);
        bean.addUrlMappings("/myServlet");;
        bean.setLoadOnStartup(1);
        return bean;
    }
}
