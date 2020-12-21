package io.github.blkmkt.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class BlkmktWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlkmktWareApplication.class, args);
    }

}
