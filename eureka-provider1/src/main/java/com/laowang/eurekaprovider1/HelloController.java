package com.laowang.eurekaprovider1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/p1/hello")
public class HelloController {

    @RequestMapping("/getHi")
    public String getHi(){
        return "provider1 says : Hi!";
    }
}
