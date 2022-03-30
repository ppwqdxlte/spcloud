package com.laowang.eurekaconsumer2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**预知而写死的接口，跟restTemplate毫无区别
* 通过Eureka解析地址
* */
@FeignClient(name = "provider1")
public interface FeignWithEureka1 {

    @GetMapping("/getHi")
    public String getHi();

    @GetMapping("/loadBalenced")
    public String loadBalenced();

}
