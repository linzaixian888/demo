package hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author linzaixian
 * @since 2017-07-28 00:04:48 
 */
@SpringBootApplication
@EnableHystrixDashboard
public class hystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(hystrixApplication.class, args);
    }
}
