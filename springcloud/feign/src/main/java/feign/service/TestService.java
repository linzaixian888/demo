package feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.hystrix.TestServiceHystrix;

/**
 * @author linzaixian
 * @since 2017-07-27 22:14:44 
 */
@FeignClient(value="module2",fallback=TestServiceHystrix.class)
public interface TestService {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String test(@RequestParam(value = "name") String name);
}
