package com.example.eurekaconsumer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumer1Application.class, args);
    }

}
