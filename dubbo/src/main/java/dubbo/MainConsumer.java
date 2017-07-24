package dubbo;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import dubbo.service.TestService;

/**
 * @author linzaixian
 * @since 2017-07-01 01:37:40 
 */
public class MainConsumer {
    public static void main(String[] args) {
        AbstractApplicationContext  act=new ClassPathXmlApplicationContext("spring-consumer.xml");
        TestService testService=act.getBean(TestService.class);
        System.out.println(testService.test("测试"));
        ProtocolConfig.destroyAll();
        act.registerShutdownHook();
    }
}
