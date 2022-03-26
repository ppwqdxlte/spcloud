package com.laowang.eurekaprovider2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/p2/hello")
public class HelloController {

    @RequestMapping("/getHi")
    public String getHi(){
        return "provider2 says : Hi!";
    }

    @RequestMapping("/sayHi")
    public String sayHi(){
        return "provider2 says: Hi ~~~";
    }
}
