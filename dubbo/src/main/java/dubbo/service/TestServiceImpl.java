package dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author linzaixian
 * @since 2017-07-01 01:09:22 
 */
@Service
public class TestServiceImpl implements TestService{

    @Override
    public String test(String str) {
        System.out.println(str);
        return str;
    }

}
