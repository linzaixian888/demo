package ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author linzaixian
 * @since 2017-07-27 17:15:05 
 */
@Service
public class TestService {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod="testError")
    public String test(String name) {
        return restTemplate.getForObject("http://client/test?name="+name,String.class);
    }
    public String testError(String name){
        return "sorry 报错了 "+name;
    }
}
