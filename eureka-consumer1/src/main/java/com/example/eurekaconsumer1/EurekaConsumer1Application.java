package com.example.eurekaconsumer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class EurekaConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumer1Application.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    /*
    * 自定义ribbon负载均衡算法【注意】2.6.5 SringCloud里没有ribbon啦！！！
    * */
/*    @Bean
    public IRule myRule(){
//        return new RetryRule();重试
//        return new RoundRobinRule();轮询
//        .....略
        return BestAvailableRule();
    }*/
}
