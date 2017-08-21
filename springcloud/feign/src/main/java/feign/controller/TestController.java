package feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import feign.service.TestService;

/**
 * @author linzaixian
 * @since 2017-07-27 16:44:36 
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService; 
    @RequestMapping("/test")
    public String test(@RequestParam String name) {
        return testService.test(name);
    }
}
