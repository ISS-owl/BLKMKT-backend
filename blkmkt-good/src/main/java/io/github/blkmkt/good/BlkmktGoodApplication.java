package io.github.blkmkt.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlkmktGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlkmktGoodApplication.class, args);
    }

}
