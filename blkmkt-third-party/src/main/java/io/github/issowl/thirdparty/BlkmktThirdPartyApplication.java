package io.github.issowl.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlkmktThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlkmktThirdPartyApplication.class, args);
    }

}
