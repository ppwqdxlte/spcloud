package com.laowang.eurekaconsumer2;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")
    public String timeOut() {
        String url = "http://provider2/testTimeout";
        return restTemplate.getForObject(url, String.class);
    }

    private String back(){
        return "熔断降级咯";
    }
}
