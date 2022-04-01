package com.laowang.eurekaconsumer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * http://eureka-consumer2.com:7922/hystrix 访问hystrix的看板
 * */
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients
@SpringBootApplication
public class EurekaConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumer2Application.class, args);
    }

}
