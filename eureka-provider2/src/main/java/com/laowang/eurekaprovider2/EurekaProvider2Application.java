package com.laowang.eurekaprovider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProvider2Application.class, args);
    }

}
