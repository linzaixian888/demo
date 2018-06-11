package feign.hystrix;

import org.springframework.stereotype.Component;

import feign.service.TestService;

/**
 * @author linzaixian
 * @since 2017-07-27 22:25:05 
 */
@Component
public class TestServiceHystrix implements TestService{

    @Override
    public String test(String name) {
        return "sorry 报错了"+name;
    }

}
