package io.github.blkmkt.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlkmktOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlkmktOrderApplication.class, args);
    }

}
