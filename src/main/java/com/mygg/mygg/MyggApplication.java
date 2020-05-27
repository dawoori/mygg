package com.mygg.mygg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("application-key.properties")
@SpringBootApplication
public class MyggApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyggApplication.class, args);
    }
}
