package io.github.issowl.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication(exclude = { RedisAutoConfiguration.class })
public class BlkmktThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlkmktThirdPartyApplication.class, args);
    }

}
