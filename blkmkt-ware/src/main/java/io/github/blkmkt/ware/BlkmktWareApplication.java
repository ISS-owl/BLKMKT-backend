package io.github.blkmkt.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BlkmktWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlkmktWareApplication.class, args);
    }

}
