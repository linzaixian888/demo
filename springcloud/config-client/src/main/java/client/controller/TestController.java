package client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linzaixian
 * @since 2017-07-27 16:44:36 
 */
@RestController
public class TestController {
    @Value("${foo}")
    String foo;
    @RequestMapping("/test")
    public String test() {
        return foo;
    }
}
