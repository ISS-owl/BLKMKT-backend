package io.github.issowl.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BlkmktSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlkmktSearchApplication.class, args);
    }

}
