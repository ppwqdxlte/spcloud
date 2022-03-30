package com.laowang.eurekaconsumer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EurekaConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumer2Application.class, args);
    }

}
