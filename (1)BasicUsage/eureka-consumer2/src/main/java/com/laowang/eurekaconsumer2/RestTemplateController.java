package com.laowang.eurekaconsumer2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTemplateController {

    @Autowired
    RestTemplateService restTemplateService;

    @RequestMapping("/timeout")
    public String timeOut(){
        return restTemplateService.timeOut();
    }
}
