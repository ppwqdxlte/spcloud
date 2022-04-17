package com.laowang.eurekaserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    @Value("${myconfig}")
    String myconfig;

    @GetMapping("/myconfig")
    public String getMyconfig(){
        return myconfig;
    }
}
