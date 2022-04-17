package com.laowang.eurekaconsumer2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/** feign调用api方式1：在本地写死mapping
 * 自动装配给feign client生成的代理类bean，底层还是RestTemplate，有区别吗？没有区别，feign依然采取拼接URL的方式调取远程接口
 * */
@RestController
public class FeignController1 {

    @Autowired
    FeignWithoutEureka1 feignWithoutEureka;
    @Autowired
    FeignWithEureka1 feignWithEureka;

    @GetMapping("/1")
    public String getHi(){
        return feignWithEureka.getHi();
    }
    /**
    * feign结合eureka实现负载均衡，feign自己单独调用的时候，没实现负载均衡
    * */
    @GetMapping("/2")
    public String loadBalenced(){
        return feignWithEureka.loadBalenced();
    }

    @GetMapping("/1.5")
    public String getHiWithoutEureka(){
        return feignWithoutEureka.getHi();
    }

    @GetMapping("/2.5")
    public String loadBalencedWithoutEureka(){
        return feignWithoutEureka.loadBalenced();
    }

}
